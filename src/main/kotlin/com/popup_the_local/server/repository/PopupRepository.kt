package com.popup_the_local.server.repository

import com.popup_the_local.server.entity.Popup
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PopupRepository: MongoRepository<Popup, String> {

    fun findByMemberId(memberId: String): List<Popup>

}