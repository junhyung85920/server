package com.popup_the_local.server.repository

import com.popup_the_local.server.entity.Bookmark
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BookmarkRepository: MongoRepository<Bookmark, String> {

    fun findByMemberId(memberId: String): List<Bookmark>

    fun findByMemberIdAndPopupId(memberId: String, popupId: String): Bookmark?
}