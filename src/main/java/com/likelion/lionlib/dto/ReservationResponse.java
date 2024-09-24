package com.likelion.lionlib.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.likelion.lionlib.domain.Reservation;
import com.likelion.lionlib.domain.LoanStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReservationResponse {
    private Long reservationId;
    private Long memberId;
    private Long bookId;

    public static ReservationResponse fromEntity(Reservation reservation) {
        return ReservationResponse.builder()
                .reservationId(reservation.getId())
                .memberId(reservation.getMember().getId())
                .bookId(reservation.getBook().getId())
                .build();
    }
}
