package com.example.member.service;

import com.example.member.dto.MemberDTO;
import com.example.member.entity.MemberEntity;
import com.example.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity
        // 2. repository의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity); //JPA가 제공하는 save라는 메서드가 이미 있음

        // repository의 save메서드 호출 JPA를 사용하므로 (조건. entity객체를 넘겨줘야 함)
    }

    public MemberDTO login(MemberDTO memberDTO) {
        /*
            1. 회원이 입력한 이메일로 DB에서 조회를 함
            2. DB에서 조회한 비밀번호 사용자가 입력한 비밀번호가 일치하는지 판단
         */
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if(byMemberEmail.isPresent()){
            // 조회 결과가 있다(해당 이메일을 가진 회원정보가 있다)
            MemberEntity memberEntity = byMemberEmail.get(); //Optional.get메서드를 통해 Entity객체를 가져올수 있다.
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                // 비밀번호 일치
                // entity -> DTO 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            }
            else{
                //비밀번호 불일치
                return null;
            }
        }
        else{
            // 조회 결과가 없다
            return null;
        }
    }
}