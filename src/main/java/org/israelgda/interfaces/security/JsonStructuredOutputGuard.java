package org.israelgda.interfaces.security;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.guardrail.OutputGuardrail;
import dev.langchain4j.guardrail.OutputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonReader;

import java.io.StringReader;

@ApplicationScoped
public class JsonStructuredOutputGuard implements OutputGuardrail {

    @Override
    public OutputGuardrailResult validate(AiMessage responseFromLLM) {
        String response = responseFromLLM.text();

        try (JsonReader reader = Json.createReader(new StringReader(response))) {
            //Try to parse.  If parsed with success, the JSON is valid, returning success.
            reader.readObject();
            return OutputGuardrailResult.success();
        } catch (Exception e) {
            //If parsing fails, the JSON is invalid, ask for a reprompt.
            //This step doesn't event get back to the user, it calls the LLM itself trying to fix the prompt.
            // CAUTION: Avoid token high usage with infinity loop cofiguring retry strategy with app properties.
            return reprompt(
                    responseFromLLM.text(), """
                           The response from the LLM is not a valid JSON structure.
                           Problem found:\s""" + e.getMessage() + """
                           .
                          
                           Please regenerate only the JSON, without markdown code blocks or additional texts.
                           """
            );
        }

    }
}
