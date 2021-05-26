package me.study.domainevent.reservation.domain;

import lombok.Getter;

@Getter
public class ReservationPayedEvent { // event 는 과거형으로 명시한다.
    private final Reservation reservation;

    public ReservationPayedEvent(Reservation reservation) {
        this.reservation = reservation;
    }
}
