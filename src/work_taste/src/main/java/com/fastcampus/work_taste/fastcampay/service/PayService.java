package com.fastcampus.work_taste.fastcampay.service;

import com.fastcampus.work_taste.fastcampay.dto.RequestDto;
import com.fastcampus.work_taste.fastcampay.dto.ResponseDto;

import java.util.List;

public interface PayService {
    ResponseDto.createPaymentResponseDto createPaymentRequest(RequestDto.createPaymentRequestDto request);

    List<ResponseDto.getPaymentResponseDto> getPaymentRequest(Long userId);
}
