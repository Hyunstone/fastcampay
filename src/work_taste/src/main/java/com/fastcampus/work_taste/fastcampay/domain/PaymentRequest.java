package com.fastcampus.work_taste.fastcampay.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Table(name = "PAYMENT_REQUEST_TB")
@Entity
@AllArgsConstructor(access= AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_request_id")
    private Long id;
    private Long memberId;
    private String shopName;
    private Long paymentAmount;
    private Boolean isSuccess;

    public boolean process(Long memberPayableAmount) {
        if (paymentAmount > memberPayableAmount) {
            return false;
        }
        isSuccess = true;
        return true;
    }
}
