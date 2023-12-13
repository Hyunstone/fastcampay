package com.fastcampus.work_taste.fastcampay.converter;

import com.fastcampus.work_taste.fastcampay.domain.PaymentRequest;
import com.fastcampus.work_taste.fastcampay.dto.RequestDto;
import com.fastcampus.work_taste.fastcampay.dto.ResponseDto;

public class PaymentRequestConverter {
    // 결제 금액은 10000원으로 고정
    public static PaymentRequest toPaymentRequest(RequestDto.CreatePaymentRequestDto dto) {
        return PaymentRequest.builder()
                .shopName(dto.getShopName())
                .memberId(dto.getMemberId())
                .isSuccess(false)
                .paymentAmount(10000L)
                .build();
    }

    public static ResponseDto.CreatePaymentResponseDto toPaymentResponseDto(PaymentRequest paymentRequest) {
        return ResponseDto.CreatePaymentResponseDto.builder()
                .id(paymentRequest.getId())
                .shopName(paymentRequest.getShopName())
                .paymentAmount(paymentRequest.getPaymentAmount())
                .isSuccess(paymentRequest.getIsSuccess())
                .build();
    }

    public static ResponseDto.GetPaymentResponseDto toGetPaymentResponseDto(PaymentRequest paymentRequest) {
        return ResponseDto.GetPaymentResponseDto.builder()
                .shopName(paymentRequest.getShopName())
                .paymentAmount(paymentRequest.getPaymentAmount())
                .build();
    }
}
