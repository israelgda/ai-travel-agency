package org.israelgda.resources.rag.services;

import jakarta.enterprise.context.ApplicationScoped;
import org.israelgda.resources.rag.domain.Booking;
import org.israelgda.resources.rag.domain.BookingStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class BookingService {

    private final Map<Long, Booking> bookings = new HashMap<>();

    public BookingService() {
        bookings.put(12345L, new Booking(
                1L,
                "John Doe",
                "Paris",
                java.time.LocalDate.of(2024, 6, 1),
                java.time.LocalDate.of(2024, 6, 10),
                BookingStatus.CONFIRMED
        ));

        bookings.put(67890L, new Booking(
                2L,
                "Jane Smith",
                "New York",
                java.time.LocalDate.of(2024, 7, 15),
                java.time.LocalDate.of(2024, 7, 20),
                BookingStatus.PENDING
        ));
    }

    public Optional<Booking> getBookingDetails(long bookingId){
        return Optional.ofNullable(bookings.get(bookingId));
    }

    public Optional<Booking> cancelBooking(long bookingId, String customerName) {
        if (bookings.containsKey(bookingId)) {
            Booking booking = bookings.get(bookingId);
            if (booking.customerName().equalsIgnoreCase(customerName)) {
                Booking cancelledBooking = new Booking(
                        booking.id(),
                        booking.customerName(),
                        booking.destination(),
                        booking.startDate(),
                        booking.endDate(),
                        BookingStatus.CANCELLED
                );
                bookings.put(bookingId, cancelledBooking);
                return Optional.of(cancelledBooking);
            }
        }
        return Optional.empty();
    }

}
