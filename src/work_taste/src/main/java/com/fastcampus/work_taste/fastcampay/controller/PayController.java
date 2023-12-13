package com.fastcampus.work_taste.fastcampay.controller;

import com.fastcampus.work_taste.fastcampay.dto.RequestDto;
import com.fastcampus.work_taste.fastcampay.dto.ResponseDto;
import com.fastcampus.work_taste.fastcampay.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/pay")
@RestController
public class PayController {
    private final PayService payService;

    // 가맹점 결제 요청
    @PostMapping("/requests")
    public ResponseEntity<ResponseDto.CreatePaymentResponseDto> createRequestPay(@RequestBody RequestDto.CreatePaymentDto request) {
        return ResponseEntity.ok().body(payService.createPaymentRequest(request));
    }

    // 사용자 결제 요청 정보 확인
    @GetMapping("/requests/members/{memberId}")
    public ResponseEntity<List<ResponseDto.GetPaymentResponseDto>> getPaymentRequest(@PathVariable Long memberId) {
        return ResponseEntity.ok().body(payService.getPaymentRequest(memberId));
    }

    // 사용장 결제 처리
    @PostMapping("/process")
    public ResponseEntity<Object> processPay(@RequestBody RequestDto.ProcessPayDto request) {
        payService.processPay(request);
        return ResponseEntity.ok().build();
    }
}
