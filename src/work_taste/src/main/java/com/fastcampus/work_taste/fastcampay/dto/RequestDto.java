package com.fastcampus.work_taste.fastcampay.dto;

import lombok.Getter;

public class RequestDto {
    @Getter
    public static class CreatePaymentRequestDto {
        private String shopName;
        private Long memberId;
    }

    @Getter
    public static class processPayDto {
        private Long paymentRequestId;
        private Long memberId;
    }
}
