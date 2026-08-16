package org.israelgda.interfaces.security;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.guardrail.InputGuardrail;
import dev.langchain4j.guardrail.InputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class InjectionGuard implements InputGuardrail {

    @Inject
    PromptSecurityExpert securityExpert;

    @Override
    public InputGuardrailResult validate(UserMessage userMessage) {
        if (securityExpert.isAttackAttempt(userMessage.singleText())) {;
            return failure("Potential security threat detected in the user prompt. Please revise your input.");
        } else {
            return success();
        }
    }
}
