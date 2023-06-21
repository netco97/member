package com.example.member.repository;

import com.example.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //첫번째 인자 = MemberEntity 두번째 인자 = Entity클래스의 pk가 어떤 타입인지
}
