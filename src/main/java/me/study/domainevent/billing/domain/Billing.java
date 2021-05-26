package me.study.domainevent.billing.domain;

import lombok.NoArgsConstructor;
import me.study.domainevent.common.domain.BaseEntity;
import me.study.domainevent.common.vo.Price;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
public class Billing extends BaseEntity<Billing> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reservationId;

    @Embedded
    private Price amount;

    public Billing(Long reservationId, Price amount) {
        this.reservationId = reservationId;
        this.amount = amount;
    }
}
