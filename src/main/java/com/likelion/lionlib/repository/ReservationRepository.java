package com.likelion.lionlib.repository;

import com.likelion.lionlib.domain.Reservation;
import com.likelion.lionlib.domain.Member;
import com.likelion.lionlib.dto.ReservationResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByMember(Member member);
}