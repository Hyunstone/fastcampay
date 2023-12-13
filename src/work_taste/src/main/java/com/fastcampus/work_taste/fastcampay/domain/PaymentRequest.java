package com.fastcampus.work_taste.fastcampay.domain;

import com.fastcampus.work_taste.fastcampay.dto.RequestDto;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Table(name = "PAYMENT_REQUEST")
@Entity
@AllArgsConstructor(access= AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_request_id")
    private Long id;
    private String shopName;
    private Long paymentAmount;
    private Boolean isSuccess;
}
