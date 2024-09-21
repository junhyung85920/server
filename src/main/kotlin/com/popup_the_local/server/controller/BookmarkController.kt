package com.popup_the_local.server.controller

import com.fasterxml.jackson.databind.ser.Serializers.Base
import com.popup_the_local.server.common.MEMBER_ID
import com.popup_the_local.server.common.responsebody.BaseResponse
import com.popup_the_local.server.dto.CreateBookmarkResponse
import com.popup_the_local.server.dto.GetBookmarkListResponse
import com.popup_the_local.server.service.BookmarkService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/bookmarks")
class BookmarkController(private val bookmarkService: BookmarkService) {

    @PostMapping("/{popupId}")
    fun createBookmark(@PathVariable popupId: String): BaseResponse<CreateBookmarkResponse> {
        return BaseResponse(msg = "북마크 생성 성공", data = bookmarkService.createBookmark(popupId, MEMBER_ID))
    }

    @GetMapping("")
    fun getBookmarkList(): BaseResponse<List<GetBookmarkListResponse>> {
        return  BaseResponse(msg = "북마크 목록 조회 성공", data = bookmarkService.getBookmarkList(MEMBER_ID))
    }

    @DeleteMapping("/{bookmarkId}")
    fun deleteBookmark(@PathVariable bookmarkId: String): BaseResponse<Unit> {
        bookmarkService.deleteBookmark(bookmarkId)

        return BaseResponse(msg = "북마크 삭제 성공")
    }
}