package me.study.domainevent.reservation.domain;

import lombok.RequiredArgsConstructor;
import me.study.domainevent.common.annotation.DomainService;
import me.study.domainevent.common.domain.DomainValidator;
import me.study.domainevent.shop.domain.Shop;
import me.study.domainevent.shop.domain.ShopRepository;

@RequiredArgsConstructor
@DomainService
public class ReservationValidator implements DomainValidator<Reservation> {
    private final ShopRepository shopRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public void valid(Reservation reservation) {
        Long shopId = reservation.getShopId();
        validateShop(reservation, shopId);

        Reservations reservations = Reservations.of(reservationRepository.findByShopIdAndTreatmentDate(shopId, reservation.getTreatmentDate()));
        reservations.isValidReservationTime(reservation);
    }

    private void validateShop(Reservation reservation, Long shopId) {
        Shop shop = shopRepository.findById(shopId)
                                  .orElseThrow(IllegalArgumentException::new);
        if (!shop.isOpened()) {
            throw new IllegalStateException();
        }

        shop.hasProduct(reservation.getProductId());
        shop.isValidReservationTime(reservation.getStartTime(), reservation.getEndTime());
    }

}
