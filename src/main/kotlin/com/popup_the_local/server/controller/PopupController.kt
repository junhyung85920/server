package com.popup_the_local.server.controller

import com.popup_the_local.server.common.MEMBER_ID
import com.popup_the_local.server.common.responsebody.BaseResponse
import com.popup_the_local.server.dto.CreatePopupRequest
import com.popup_the_local.server.dto.CreatePopupResponse
import com.popup_the_local.server.dto.GetPopupDetailResponse
import com.popup_the_local.server.dto.GetPopupListByCategoryResponse
import com.popup_the_local.server.entity.Popup
import com.popup_the_local.server.repository.PopupRepository
import com.popup_the_local.server.service.PopupService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/popups"])
class PopupController(
    private val popupRepository: PopupRepository,
    private val popupService: PopupService,
) {
    @GetMapping("/{popupId}")
    fun getPopupDetail(@PathVariable popupId: String): BaseResponse<GetPopupDetailResponse> {
        return BaseResponse(msg = "팝업 상세 조회 성공", data = popupService.getPopupDetail(popupId))
    }

    @PostMapping("")
    fun createPopup(
         request: CreatePopupRequest
    ): BaseResponse<CreatePopupResponse> {
        return BaseResponse(msg = "팝업 생성 성공", data = popupService.createPopup(MEMBER_ID, request))
    }

    @GetMapping("/member/{memberId}")
    fun readPopupByMemberId(@PathVariable memberId: String): BaseResponse<List<Popup>> {
        return BaseResponse(msg = "멤버 별 팝업 목록 조회 성공", data = popupRepository.findByMemberId(memberId))
    }

    @GetMapping("/list")
    fun getPopupListByCategory(@RequestParam(required = false) category: String?,
                               @RequestParam(required = false) city: String?): BaseResponse<List<GetPopupListByCategoryResponse>> {

        return BaseResponse(msg = "$category 카테고리 팝업 목록 조회 성공", data = popupService.getPopupList(city, category))

    }
}