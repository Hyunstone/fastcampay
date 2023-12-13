package com.fastcampus.work_taste.fastcampay.service;

import com.fastcampus.work_taste.fastcampay.converter.PaymentRequestConverter;
import com.fastcampus.work_taste.fastcampay.domain.Member;
import com.fastcampus.work_taste.fastcampay.domain.PaymentRequest;
import com.fastcampus.work_taste.fastcampay.dto.RequestDto;
import com.fastcampus.work_taste.fastcampay.dto.ResponseDto;
import com.fastcampus.work_taste.fastcampay.repository.MemberRepository;
import com.fastcampus.work_taste.fastcampay.repository.PayRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PayServiceImpl implements PayService {

    private final PayRequestRepository payRequestRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public ResponseDto.CreatePaymentResponseDto createPaymentRequest(RequestDto.CreatePaymentDto request) {
        memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다"));
        return PaymentRequestConverter.toPaymentResponseDto(payRequestRepository.save(PaymentRequestConverter.toPaymentRequest(request)));
    }

    @Override
    public List<ResponseDto.GetPaymentResponseDto> getPaymentRequest(RequestDto.GetPaymentDto request) {
        return payRequestRepository.findAllByMemberId(request.getMemberId()).stream()
                .map(PaymentRequestConverter::toGetPaymentResponseDto)
                .toList();
    }

    @Transactional
    @Override
    public void processPay(RequestDto.processPayDto request) {
        PaymentRequest paymentRequest = payRequestRepository.findByIdAndIsSuccess(request.getPaymentRequestId(), false)
                .orElseThrow(() -> new IllegalArgumentException("요청된 결제를 찾을 수 없습니다"));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다"));
        if (!paymentRequest.process(member.getPayableAmount())) {
            throw new IllegalStateException("결제 잔액이 부족합니다");
        }
        member.processPay(10000L);
    }
}
