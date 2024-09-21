package com.popup_the_local.server.controller

import com.popup_the_local.server.common.MEMBER_ID
import com.popup_the_local.server.common.responsebody.BaseResponse
import com.popup_the_local.server.dto.GetMemberDetailResponse
import com.popup_the_local.server.service.MemberService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
    @Qualifier("mongoTemplate") private val mongoTemplate: MongoTemplate,
    private val memberService: MemberService
) {

//    @PostMapping("")
//    fun createMember(@Valid @RequestBody request: CreateMemberRequest): Member {
//        val member = Member(
//            email = request.email,
//            role = Role.valueOf(request.role),
//            image = request.image,
//            name = request.name,
//        )
//
//        val save = mongoTemplate.insert(member)
//
//        return save
//    }

    @GetMapping("")
    fun getMemberDetail(): BaseResponse<GetMemberDetailResponse> {
        return BaseResponse(msg = "멤버 정보 조회 성공", data = memberService.getMemberDetail(MEMBER_ID))
    }

}