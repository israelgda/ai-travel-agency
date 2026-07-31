package org.israelgda.interfaces;

import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface TravelAgentAssistant {

    String chat(String userInputMessage);
}
