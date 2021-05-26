package me.study.domainevent.billing.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.study.domainevent.common.annotation.DomainService;
import me.study.domainevent.reservation.domain.Reservation;
import me.study.domainevent.reservation.domain.ReservationPayedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@DomainService
public class ReservationPayedEventHandler {

    private final BillingRepository billingRepository;

    @Async
    @EventListener
    @Transactional
    public void handle(ReservationPayedEvent reservationPayedEvent) {
        log.info("billed reservation event start");
        Reservation reservation = reservationPayedEvent.getReservation();
        Billing billing = new Billing(reservation.getId(), reservation.getAmount());
        billingRepository.save(billing);
        log.info("billed reservation {}", reservation.getId());
    }
}
