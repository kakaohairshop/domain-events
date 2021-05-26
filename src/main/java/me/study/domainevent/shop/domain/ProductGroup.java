package me.study.domainevent.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductGroup {

    @OneToMany(mappedBy = "shop", cascade = CascadeType.PERSIST)
    private List<Product> products = new ArrayList<>();

    public ProductGroup(List<Product> products) {
        this.products = products;
    }

    public void validProduct(Long productId) {
        products.stream()
                .filter(product -> product.getId().equals(productId))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public void syncShop(Shop shop) {
        products.forEach(product -> product.changeShop(shop));
    }
}
