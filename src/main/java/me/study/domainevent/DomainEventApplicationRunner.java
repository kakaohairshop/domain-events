package me.study.domainevent;

import lombok.RequiredArgsConstructor;
import me.study.domainevent.common.vo.Price;
import me.study.domainevent.shop.domain.Product;
import me.study.domainevent.shop.domain.ProductGroup;
import me.study.domainevent.shop.domain.Shop;
import me.study.domainevent.shop.domain.ShopRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DomainEventApplicationRunner implements ApplicationRunner {

    private final ShopRepository shopRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Shop shop = Shop.builder()
                      .name("dean's hair")
                      .opened(true)
                      .startTime(LocalTime.of(10, 00))
                      .endTime(LocalTime.of(20, 00))
                      .build();
        shop.addProducts(new ProductGroup(List.of(Product.builder()
                                                         .name("커트")
                                                         .price(new Price(10000))
                                                         .requiredMinutes(30)
                                                         .build())));
        shopRepository.save(shop);
    }
}
