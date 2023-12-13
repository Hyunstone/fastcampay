package com.fastcampus.work_taste.fastcampay.repository;

import com.fastcampus.work_taste.fastcampay.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
