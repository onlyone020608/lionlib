package com.likelion.lionlib.repository;

import com.likelion.lionlib.domain.Profile;
import com.likelion.lionlib.domain.Reservation;
import com.likelion.lionlib.domain.Member;
import com.likelion.lionlib.domain.Book;
import com.likelion.lionlib.dto.ReservationCountResponse;
import com.likelion.lionlib.dto.ReservationResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByMember(Member member);
    List<Reservation> findAllByBook(Book book);
    long countByBookId(Long bookId);
}