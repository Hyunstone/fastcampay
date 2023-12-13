package com.fastcampus.work_taste.fastcampay.repository;

import com.fastcampus.work_taste.fastcampay.domain.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayRequestRepository extends JpaRepository<PaymentRequest, Long> {

    List<PaymentRequest> findAllByMemberId(Long memberId);
}
