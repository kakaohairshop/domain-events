package me.study.domainevent.reservation.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.study.domainevent.reservation.dto.ReservationCreateRequest;
import me.study.domainevent.reservation.service.ReservationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reserve")
    public void reserve(@RequestBody ReservationCreateRequest reservationCreateRequest) {
        reservationService.reserve(reservationCreateRequest);
    }

    @PostMapping("/pay/{reservationId}")
    public void pay(@PathVariable Long reservationId) {
        reservationService.payed(reservationId);
        log.info("complete");
    }
}
