package org.israelgda.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.israelgda.interfaces.PackageExpertWithPromtpTemplate;

@Path("/travel-agent")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TravelAgentResource {

    @Inject
    private PackageExpertWithPromtpTemplate travelAgentAssistant;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String chat(
            String userInputMessage,
            @HeaderParam("X-User-Name") String username
    ) {
        return travelAgentAssistant.chat(
                "session-id-123-example",
                userInputMessage,
                username
        );
    }
}
