package me.study.domainevent.reservation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.study.domainevent.reservation.domain.ReservationRepository;
import me.study.domainevent.reservation.domain.ReservationValidator;
import me.study.domainevent.reservation.dto.ReservationCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationValidator reservationValidator;

    @Transactional
    public void reserve(ReservationCreateRequest reservationCreateRequest) {
        reservationCreateRequest.toReady()
                                .validate(reservationValidator)
                                .apply(reservationRepository::save);
    }

    @Transactional
    public void payed(Long reservationId) {
        reservationRepository.findById(reservationId)
                             .orElseThrow(IllegalArgumentException::new)
                             .pay()
                             .publish(reservationRepository::save); // Domain event 발행을 위해.
        log.info("end payevent publish");
    }
}
