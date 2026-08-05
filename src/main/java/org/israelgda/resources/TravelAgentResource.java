package org.israelgda.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.israelgda.interfaces.PackageExpert;

@Path("/travel-agent")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TravelAgentResource {

    @Inject
    private PackageExpert travelAgentAssistant;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String chat(String userInputMessage) {
        return travelAgentAssistant.chat(
                "session-id-123-example",
                userInputMessage
        );
    }
}
