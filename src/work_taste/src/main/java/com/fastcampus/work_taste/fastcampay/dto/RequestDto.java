package com.fastcampus.work_taste.fastcampay.dto;

import lombok.Getter;

public class RequestDto {
    @Getter
    public static class createPaymentRequestDto {
        private String shopName;
    }

}
