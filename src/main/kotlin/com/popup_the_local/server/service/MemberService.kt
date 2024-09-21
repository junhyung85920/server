package com.popup_the_local.server.service

import com.popup_the_local.server.dto.GetMemberDetailResponse
import com.popup_the_local.server.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    fun getMemberDetail(memberId: String): GetMemberDetailResponse {
        val member = memberRepository.findById(memberId).orElseThrow()

        return GetMemberDetailResponse(
            email = member.email,
            role = member.role,
            name = member.name,
            image = member.image,
        )
    }
}