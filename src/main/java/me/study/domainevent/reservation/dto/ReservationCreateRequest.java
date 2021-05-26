package me.study.domainevent.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.study.domainevent.reservation.domain.Reservation;
import me.study.domainevent.common.vo.Price;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservationCreateRequest {
    private Long shopId;

    private Long productId;

    private LocalDate treatmentDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private int amount;

    public Reservation toReady() {
        return Reservation.ready(shopId, productId, treatmentDate, startTime, endTime, new Price(amount));
    }
}
