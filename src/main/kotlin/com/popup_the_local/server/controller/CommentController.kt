package com.popup_the_local.server.controller

import com.popup_the_local.server.common.MEMBER_ID
import com.popup_the_local.server.common.responsebody.BaseResponse
import com.popup_the_local.server.dto.CreateCommentRequest
import com.popup_the_local.server.dto.CreateCommentResponse
import com.popup_the_local.server.service.CommentService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/comments")
class CommentController(private val commentService: CommentService) {

    @PostMapping("")
    fun createComment(@RequestBody request: CreateCommentRequest): BaseResponse<CreateCommentResponse> {
        return BaseResponse(msg = "댓글 게시 성공", data = commentService.createComment(MEMBER_ID, request))

    }
}