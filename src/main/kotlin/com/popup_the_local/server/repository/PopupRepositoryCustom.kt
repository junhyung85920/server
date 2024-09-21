package com.popup_the_local.server.repository

import com.popup_the_local.server.entity.Category
import com.popup_the_local.server.entity.Popup
import org.springframework.stereotype.Repository

@Repository
interface PopupRepositoryCustom {
    fun findByCategoryAndCity(category: Category?, city: String?): List<Popup>
}