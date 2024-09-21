package com.popup_the_local.server.service

import com.popup_the_local.server.common.responsebody.InvalidInputException
import com.popup_the_local.server.dto.CreateCommentRequest
import com.popup_the_local.server.dto.CreateCommentResponse
import com.popup_the_local.server.entity.Comment
import com.popup_the_local.server.repository.CommentRepository
import com.popup_the_local.server.repository.MemberRepository
import com.popup_the_local.server.repository.PopupRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CommentService(
    private val memberRepository: MemberRepository,
    private val popupRepository: PopupRepository,
    private val commentRepository: CommentRepository
) {

    @Transactional
    fun createComment(memberId: String, request: CreateCommentRequest): CreateCommentResponse{
        val member = memberRepository.findByIdOrNull(memberId) ?: throw InvalidInputException(fieldName = "member")
        val popup = popupRepository.findByIdOrNull(request.popupId) ?: throw InvalidInputException(fieldName = "popup")


        val comment = commentRepository.save(
            Comment.createComment(
                member = member,
                popup = popup,
                content = request.content
            )
        )

        return CreateCommentResponse(commentId = comment.id)
    }
}