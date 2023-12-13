package com.fastcampus.work_taste.fastcampay.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Table(name = "MEMBER_TB")
@Entity
@AllArgsConstructor(access= AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String name;
    private Long payableAmount;
}
