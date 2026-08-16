package org.israelgda.interfaces.security;

import dev.langchain4j.service.SystemMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface ToneJudge {

    @SystemMessage(
            """
            You are a tone judge that specializes in identifying the tone of a given text.
            Analyse if the given text is adequate.
           \s
            BAD examples:
                        - "This is not my problem" -> Rude.
                        - "Do it yourself" -> Also rude.
                        - "Dude this is boring" -> Inadequate.
                                   \s
            GOOD examples:
                        - "I'm sorry, but this is outside my jurisdiction." -> Polite.
                        - "I apologize, but I cannot assist you with that request." -> Polite.
                        - "Please, try to verity the information on the terms and rules" -> Polite and informal.
                                   \s
            Respond 'true' if the given text is adequate or 'false' if not.
           \s"""
    )
    boolean isAdequate(String text);
}
