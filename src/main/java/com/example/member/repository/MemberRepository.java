package com.example.member.repository;

import com.example.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //첫번째 인자 = MemberEntity 두번째 인자 = Entity클래스의 pk가 어떤 타입인지

    // 이메일로 회원 정보 조회(Select * from member_table where member=email=?)
    Optional<MemberEntity> findByMemberEmail(String memberEmail); //Optional -> null 방지
}
