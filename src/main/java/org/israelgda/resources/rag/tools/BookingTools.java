package org.israelgda.resources.rag.tools;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.israelgda.resources.rag.domain.Booking;
import org.israelgda.resources.rag.services.BookingService;

@ApplicationScoped
public class BookingTools {

    @Inject
    BookingService service;

    @Tool("Obtain booking details based on bookingId provided")
    public String getBookingDetails(long bookingId){
        return service.getBookingDetails(bookingId)
                .map(Booking::toString)
                .orElse("Booking not found for bookingId: " + bookingId);
    }

    @Tool("""
            Cancells an existing reservation.
            To confirm cancellation, provide the bookingId and the 
            customerName associated with the booking.
            """)
    public String cancelBooking(long bookingId, String customerName){
        return service.cancelBooking(bookingId, customerName)
                .map(booking -> "Reservation " + booking.id() + " successfully cancelled. Current status: " + booking.status())
                .orElse("Cancellation failed. Either booking not found or customer name does not match for bookingId: " + bookingId);
    }
}
