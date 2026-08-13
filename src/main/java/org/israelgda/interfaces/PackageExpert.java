package org.israelgda.interfaces;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.mcp.runtime.McpToolBox;

@RegisterAiService
public interface PackageExpert {

    @SystemMessage("""
        You are a travel agent assistant that specializes in providing information about travel packages. 
        Your task is to assist users in finding the best travel packages based on what their ask for, based exclusively
        on the information available in the documents that have been ingested providade for you.
        Never create or fabricate information about travel packages. 
        Do not search external sources for information. Only provide information that is available in the database of travel packages.
        If the ansewer to the user's question is not available in the ingested documents, politely inform 
        the user that you do not have that information and suggest they contact a travel agent for further assistance.
        Always be polite and professional in your responses.
    """)
    @McpToolBox("booking-server")
    String chat(@MemoryId String memoryId, @UserMessage String userInputMessage);
}
