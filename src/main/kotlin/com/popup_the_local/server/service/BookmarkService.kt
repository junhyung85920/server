package com.popup_the_local.server.service

import com.popup_the_local.server.common.responsebody.InvalidInputException
import com.popup_the_local.server.dto.CreateBookmarkResponse
import com.popup_the_local.server.dto.GetBookmarkListResponse
import com.popup_the_local.server.entity.Bookmark
import com.popup_the_local.server.repository.BookmarkRepository
import com.popup_the_local.server.repository.MemberRepository
import com.popup_the_local.server.repository.PopupRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookmarkService(
    private val popupRepository: PopupRepository,
    private val memberRepository: MemberRepository,
    private val bookmarkRepository: BookmarkRepository
) {
    @Transactional
    fun createBookmark(popupId: String, memberId: String): CreateBookmarkResponse {
        val popup = popupRepository.findByIdOrNull(popupId)?: throw InvalidInputException(fieldName = "popup")
        val member = memberRepository.findByIdOrNull(memberId)?: throw InvalidInputException(fieldName = "member")

        bookmarkRepository.save(Bookmark.createBookmark(popup = popup, member = member))
            .run{
                return CreateBookmarkResponse(bookmarkId = this.id)
            }
    }

    fun getBookmarkList(memberId: String): List<GetBookmarkListResponse> {
        val bookmarkList = bookmarkRepository.findByMemberId(memberId)
            .map { bookmark: Bookmark ->  GetBookmarkListResponse(
                bookmarkId = bookmark.id,
                popupId = bookmark.popup.id,
                title = bookmark.popup.title,
                description = bookmark.popup.description,
                startDate = bookmark.popup.toStringStartDate(),
                endDate = bookmark.popup.toStringEndDate(),
                address = bookmark.popup.address.toString(),
                category = bookmark.popup.category,
                status = bookmark.popup.status,
                image = bookmark.popup.images[0]
            )}

        return bookmarkList
    }

    fun deleteBookmark(bookmarkId: String) {
        bookmarkRepository.deleteById(bookmarkId)
    }
}