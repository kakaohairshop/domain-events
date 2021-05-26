package me.study.domainevent.reservation.domain;

public enum ReservationStatus {
    READY,
    OK,
    COMPLETED,
    NO_SHOW,
    CANCELED,
    WAIT_CANCEL,
    TIME_OVER;
}
