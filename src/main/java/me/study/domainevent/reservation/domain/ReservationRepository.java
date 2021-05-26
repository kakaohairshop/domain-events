package me.study.domainevent.reservation.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByShopId(Long shopId);

    List<Reservation> findByShopIdAndTreatmentDate(Long shopId, LocalDate treatmentDate);
}
