package com.fastcampus.work_taste.fastcampay.service;

import com.fastcampus.work_taste.fastcampay.converter.PaymentRequestConverter;
import com.fastcampus.work_taste.fastcampay.dto.RequestDto;
import com.fastcampus.work_taste.fastcampay.dto.ResponseDto;
import com.fastcampus.work_taste.fastcampay.repository.PayRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PayServiceImpl implements PayService {

    private final PayRequestRepository payRequestRepository;

    @Override
    public ResponseDto.CreatePaymentResponseDto createPaymentRequest(RequestDto.CreatePaymentRequestDto request) {
        return PaymentRequestConverter.toPaymentResponseDto(payRequestRepository.save(PaymentRequestConverter.toPaymentRequest(request)));
    }

    @Override
    public List<ResponseDto.GetPaymentResponseDto> getPaymentRequest(Long memberId) {
        return payRequestRepository.findAllByMemberId(memberId).stream()
                .map(PaymentRequestConverter::toGetPaymentResponseDto)
                .collect(Collectors.toList());
    }
}
