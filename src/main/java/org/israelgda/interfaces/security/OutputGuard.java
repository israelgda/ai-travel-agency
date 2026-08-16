package org.israelgda.interfaces.security;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.guardrail.OutputGuardrail;
import dev.langchain4j.guardrail.OutputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OutputGuard implements OutputGuardrail {

    @Inject
    ToneJudge toneJudge;

    @Override
    public OutputGuardrailResult validate(AiMessage responseFromLLM) {
        if (!toneJudge.isAdequate(responseFromLLM.text())) {
            return reprompt(
                    responseFromLLM.text(), """
                           The response from the LLM contains inappropriate content.
                           Please regenerate the response with a more appropriate tone and content.
                           """
            );
        }

        return OutputGuardrailResult.success();
    }
}
