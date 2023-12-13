package com.fastcampus.work_taste.fastcampay.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Table(name = "SHOP_TB")
@Entity
@AllArgsConstructor(access= AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;
    private String name;
}
