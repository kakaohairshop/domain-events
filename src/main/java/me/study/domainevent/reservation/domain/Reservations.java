package me.study.domainevent.reservation.domain;

import java.time.Duration;
import java.util.List;

public class Reservations {
    private final List<Reservation> reservations;

    public Reservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public static Reservations of(List<Reservation> reservations) {
        return new Reservations(reservations);
    }

    public void isValidReservationTime(Reservation reservation) {
        boolean matched = reservations.stream()
                                .anyMatch(reservedReservation -> reservedReservation.getStartTime().isBefore(reservation.getStartTime())
                                                                && reservedReservation.getEndTime().isAfter(reservation.getStartTime()));
        if (matched) {
            throw new IllegalArgumentException();
        }
    }
}
