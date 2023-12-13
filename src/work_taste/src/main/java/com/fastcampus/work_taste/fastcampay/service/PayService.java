package com.fastcampus.work_taste.fastcampay.service;

import com.fastcampus.work_taste.fastcampay.dto.RequestDto;
import com.fastcampus.work_taste.fastcampay.dto.ResponseDto;

import java.util.List;

public interface PayService {
    ResponseDto.CreatePaymentResponseDto createPaymentRequest(RequestDto.CreatePaymentDto request);

    List<ResponseDto.GetPaymentResponseDto> getPaymentRequest(Long memberId);

    void processPay(RequestDto.processPayDto request);
}
