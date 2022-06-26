package com.metacrew.pr2s.repository;

import com.metacrew.pr2s.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
}
