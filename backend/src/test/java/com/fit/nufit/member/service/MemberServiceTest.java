package com.fit.nufit.member.service;

import com.fit.nufit.member.domain.Member;
import com.fit.nufit.member.domain.MemberRepository;
import com.fit.nufit.member.domain.Role;
import com.fit.nufit.member.domain.Sex;
import com.fit.nufit.member.dto.request.MemberDetailRequest;
import com.fit.nufit.member.dto.response.MemberDetailResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    void 회원의_상세정보를_조회한다() {
        // given
        Member member = memberRepository.save(
                new Member("이근우", "geunwoo@gmail", "1", Role.USER));

        // when
        MemberDetailResponse result = memberService.findDetailBySocialId("1");

        // then
        assertThat(result.getAge()).isEqualTo(0);
    }

    @Test
    void 회원의_상세정보를_수정한다() {
        // given
        memberRepository.save(new Member("이근우", "geunwoo@gmail", "2", Role.USER));
        MemberDetailRequest request = new MemberDetailRequest(Sex.MAN, 26, 181, 65);

        // when
        memberService.updateDetailBySocialId("2", request);
        MemberDetailResponse result = memberService.findDetailBySocialId("2");

        // then
        assertThat(result.getAge()).isEqualTo(request.getAge());
    }
}
