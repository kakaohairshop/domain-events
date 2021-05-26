package me.study.domainevent.shop.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.study.domainevent.common.domain.AggregateRootBaseEntity;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop extends AggregateRootBaseEntity<Shop> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private ProductGroup products;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean opened;

    @Builder
    public Shop(String name, LocalTime startTime, LocalTime endTime, boolean opened) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.opened = opened;
    }

    public void hasProduct(Long productId) {
        products.validProduct(productId);
    }

    public void isValidReservationTime(LocalTime startTime, LocalTime endTime) {
        if (this.startTime.isAfter(startTime)) {
            throw new IllegalArgumentException("영업시간이 아닙니다.");
        }

        if (this.endTime.isBefore(endTime)) {
            throw new IllegalArgumentException("영업시간이 아닙니다.");
        }
    }

    public void addProducts(ProductGroup productGroup) {
        this.products = productGroup;
        productGroup.syncShop(this);
    }
}
