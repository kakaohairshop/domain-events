package me.study.domainevent.common.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@EqualsAndHashCode(of = "price")
@Getter
@Embeddable
public class Price {
    public static final String INVALID_PRICE_MESSAGE = "가격은 0보다 크거나 같아야 합니다.";

    private final BigDecimal price;

    public Price(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(INVALID_PRICE_MESSAGE);
        }
        this.price = price;
    }

    public Price(int price) {
        this(BigDecimal.valueOf(price));
    }

    public Price() {
        this(BigDecimal.ZERO);
    }
}
