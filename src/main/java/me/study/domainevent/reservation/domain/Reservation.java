package me.study.domainevent.reservation.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.study.domainevent.common.domain.AggregateRootBaseEntity;
import me.study.domainevent.common.domain.DomainValidator;
import me.study.domainevent.common.vo.Price;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends AggregateRootBaseEntity<Reservation> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long shopId;

    private Long productId;

    private LocalDate treatmentDate;

    private LocalTime startTime;

    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @Embedded
    private Price amount;

    public Reservation(Long shopId, Long productId, LocalDate treatmentDate, LocalTime startTime, LocalTime endTime, ReservationStatus reservationStatus, Price amount) {
        this.shopId = shopId;
        this.productId = productId;
        this.treatmentDate = treatmentDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reservationStatus = reservationStatus;
        this.amount = amount;
    }

    public static Reservation ready(Long shopId, Long productId, LocalDate treatmentDate, LocalTime startTime, LocalTime endTime, Price amount) {
        return new Reservation(shopId, productId, treatmentDate, startTime, endTime, ReservationStatus.READY, amount);
    }

    public Reservation validate(DomainValidator reservationValidator) {
        reservationValidator.valid(this);
        return this;
    }

    public Reservation pay() {
        validatePaymentAvailableStatus();
        this.reservationStatus = ReservationStatus.OK;
        registerEvent(new ReservationPayedEvent(this));
        log.info("published payed event");
        return this;
    }

    private void validatePaymentAvailableStatus() {
        if (reservationStatus != ReservationStatus.READY) {
            throw new IllegalStateException("결제 가능한 예약 상태가 아닙니다.");
        }
    }
}
