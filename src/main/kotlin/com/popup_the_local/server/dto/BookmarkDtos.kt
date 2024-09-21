package com.popup_the_local.server.dto

import com.popup_the_local.server.entity.Category
import com.popup_the_local.server.entity.PopupStatus

data class GetBookmarkListResponse(
    val bookmarkId: String,
    val popupId: String,
    val title: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val address: String,
    val category: Category,
    val status: PopupStatus,
    val image: String,
)

data class CreateBookmarkResponse(
    val bookmarkId: String,
)