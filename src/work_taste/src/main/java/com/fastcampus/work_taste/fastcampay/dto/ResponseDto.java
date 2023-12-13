package com.fastcampus.work_taste.fastcampay.dto;

import lombok.Builder;
import lombok.Getter;

public class ResponseDto {
    @Getter
    @Builder
    public static class createPaymentResponseDto {
        private Long id;
        private String shopName;
        private Long paymentAmount;
        private Boolean isSuccess;
    }

    @Getter
    @Builder
    public static class getPaymentResponseDto {
        private String shopName;
        private Long paymentAmount;
    }
}
