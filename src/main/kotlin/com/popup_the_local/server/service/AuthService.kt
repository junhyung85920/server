package com.popup_the_local.server.service

import com.popup_the_local.server.common.authority.JwtTokenProvider
import com.popup_the_local.server.dto.*
import com.popup_the_local.server.entity.Member
import com.popup_the_local.server.repository.MemberRepository
import org.example.springmongodb.entity.Role
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AuthService(
    private val memberRepository: MemberRepository,
    private val jwtTokenProvider: JwtTokenProvider,
) {
    fun googleLogin(request: LoginRequest): UserInfoResponse {
        val member: Member? = memberRepository.findBySocialId(request.id)
        if (member == null) {
            return UserInfoResponse(
                name = request.displayName,
                picture = request.photoUrl,
                email = request.email,
                role = Role.NONE,
                memberId = null,
                accessToken = null,
                isMember = false
            )
        }else{
            return UserInfoResponse(
                name = member.name,
                picture = member.image,
                email = member.email,
                role = member.role,
                memberId = member.id,
                accessToken = jwtTokenProvider.createToken(member.id),
                isMember = true
            )
        }
    }

    @Transactional
    fun customerRegister(request: CustomerRegisterRequest): ResisterResponse {
        val member = memberRepository.save(
            Member.createMember(
                name = request.name,
                email = request.email,
                role = Role.CUSTOMER,
                image = request.image,
                socialId = request.socialId
            )
        )
        return ResisterResponse(memberId = member.id)
    }

    @Transactional
    fun sellerRegister(request: SellerRegisterRequest): ResisterResponse{
        val member = memberRepository.save(
            Member.createMember(
                name = request.name,
                email = request.email,
                role = Role.SELLER,
                image = request.image,
                socialId = request.socialId,
                address = request.address,
            )
        )
        return ResisterResponse(memberId = member.id)
    }
}
