package com.popup_the_local.server.controller

import com.popup_the_local.server.common.MEMBER_ID
import com.popup_the_local.server.common.responsebody.BaseResponse
import com.popup_the_local.server.dto.ApplyPopupRequest
import com.popup_the_local.server.dto.ApplyPopupResponse
import com.popup_the_local.server.dto.RecommendEventRequest
import com.popup_the_local.server.dto.RecommendEventResponse
import com.popup_the_local.server.service.ApplicationService

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/application")
class ApplicationController(private val applicationService: ApplicationService) {

    @PostMapping("")
    fun applyPopup( request: ApplyPopupRequest): BaseResponse<ApplyPopupResponse> {
        return BaseResponse(msg = "팝업 등록 신청 완료", data = applicationService.applyPopup(MEMBER_ID, request))
    }

    @PostMapping("/recommendation")
    fun recommendEvent(@RequestBody request: RecommendEventRequest): BaseResponse<RecommendEventResponse>{
        return BaseResponse(msg = "팝업 이벤트 추천 성공", data = applicationService.recommendEvent(request))
    }

}