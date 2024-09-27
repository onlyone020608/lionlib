package com.likelion.lionlib.service;

import com.likelion.lionlib.domain.Book;
import com.likelion.lionlib.domain.Member;
import com.likelion.lionlib.domain.Reservation;
import com.likelion.lionlib.exception.MemberNotFoundException;
import com.likelion.lionlib.repository.BookRepository;
import com.likelion.lionlib.repository.MemberRepository;
import com.likelion.lionlib.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GlobalService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final ReservationRepository reservationRepository;

    public Book findBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(email));
    }

}