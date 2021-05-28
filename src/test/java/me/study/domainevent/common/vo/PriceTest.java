package me.study.domainevent.common.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static me.study.domainevent.common.vo.Price.INVALID_PRICE_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @DisplayName("가격은 0보다 커야한다.")
    @ValueSource(ints = {0, 1000})
    @ParameterizedTest
    void validPrice(int price) {
        assertAll(
                () -> assertThatCode(() -> new Price(price))
                        .doesNotThrowAnyException(),
                () -> assertThat(new Price(price).getPrice()).isEqualTo(BigDecimal.valueOf(price))
        );
    }

    @DisplayName("가격이 0미만인 경우 Exception을 던진다.")
    @ValueSource(ints = {-1, -1000})
    @ParameterizedTest
    void negativeValue(int price) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Price(price))
                .withMessage(INVALID_PRICE_MESSAGE);
    }
}
