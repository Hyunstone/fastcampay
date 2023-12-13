package com.fastcampus.work_taste.fastcampay.service;

import com.fastcampus.work_taste.fastcampay.config.BaseException;
import com.fastcampus.work_taste.fastcampay.config.BaseResponseStatus;
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
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_EXIST_USER));
        return PaymentRequestConverter.toPaymentResponseDto(payRequestRepository.save(PaymentRequestConverter.toPaymentRequest(request)));
    }

    @Override
    public List<ResponseDto.GetPaymentResponseDto> getPaymentRequest(Long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_EXIST_USER));
        List<PaymentRequest> responseDtos = payRequestRepository.findAllByMemberId(memberId);
        if (responseDtos.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_PAY_REQUEST);
        }
        return responseDtos.stream()
                .map(PaymentRequestConverter::toGetPaymentResponseDto)
                .toList();
    }

    @Transactional
    @Override
    public void processPay(RequestDto.ProcessPayDto request) {
        PaymentRequest paymentRequest = payRequestRepository.findById(request.getPaymentRequestId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_EXIST_PAY_REQUEST));
        if (paymentRequest.getIsSuccess()) {
            throw new BaseException(BaseResponseStatus.IS_ALREADY_PROCESS);
        }
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_EXIST_USER));
        if (!paymentRequest.process(member.getPayableAmount())) {
            throw new BaseException(BaseResponseStatus.PAYMENT_INSUFFICIENT);
        }
        member.processPay(10000L);
    }
}
