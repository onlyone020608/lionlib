package com.likelion.lionlib.service;

import com.likelion.lionlib.domain.Book;
import com.likelion.lionlib.domain.Loan;
import com.likelion.lionlib.domain.LoanStatus;
import com.likelion.lionlib.domain.Member;
import com.likelion.lionlib.domain.Reservation;
import com.likelion.lionlib.dto.*;
import com.likelion.lionlib.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    private final GlobalService globalService;

    public ReservationResponse addReservation(ReservationRequest reservationRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Member loggedInMember = userDetails.getMember();
        Book book = globalService.findBookById(reservationRequest.getBookId());
        Reservation savedReservation = Reservation.builder()
                .member(loggedInMember)
                .book(book)
                .build();
        reservationRepository.save(savedReservation);
        return ReservationResponse.fromEntity(savedReservation);
    }

    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(ReservationResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ReservationResponse> getReservationsByMemberId(Long memberId) {
        List<Reservation> reservations = findReservationsByMemberId(memberId);
        return reservations.stream()
                .map(ReservationResponse::fromEntity)
                .collect(Collectors.toList());

    }

    public List<ReservationResponse> getReservationsByBookId(Long bookId) {
        List<Reservation> reservations = findReservationsByBookId(bookId);
        return reservations.stream()
                .map(ReservationResponse::fromEntity)
                .collect(Collectors.toList());

    }

    private List<Reservation> findReservationsByBookId(Long bookId) {
        Book book = globalService.findBookById(bookId);
        return reservationRepository.findAllByBook(book);
    }

    private List<Reservation> findReservationsByMemberId(Long memberId) {
        Member member = globalService.findMemberById(memberId);
        return reservationRepository.findAllByMember(member);
    }
    public void deleteReservation(Long id ){
        reservationRepository.deleteById(id);
    }

    public ReservationCountResponse countReservations(Long bookId) {
        Long count = reservationRepository.countByBookId(bookId);
        return new ReservationCountResponse(count);
    }
}
