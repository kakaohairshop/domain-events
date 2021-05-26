package me.study.domainevent.shop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.study.domainevent.common.domain.BaseEntity;
import me.study.domainevent.common.vo.Price;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Entity
@NoArgsConstructor
public class Product extends BaseEntity<Product> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    private int requiredMinutes;

    @Embedded
    private Price price;

    @Builder
    public Product(String name, Shop shop, int requiredMinutes, Price price) {
        this.name = name;
        this.shop = shop;
        this.requiredMinutes = requiredMinutes;
        this.price = price;
    }

    public void changeShop(Shop shop) {
        this.shop = shop;
    }
}
