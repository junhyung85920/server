package com.popup_the_local.server.dto

import com.popup_the_local.server.entity.Category
import com.popup_the_local.server.entity.PopupStatus
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate

data class CreatePopupRequest(
    val title: String,
    val description: String,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val startDate: LocalDate,
    val images: List<MultipartFile>,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val endDate: LocalDate,
    val address: CreatePopupAddressRequest,
    val category: Category,
)

data class CreatePopupAddressRequest(
    val city : String,
    val street: String,
    val zipCode : String?,
)

data class CreatePopupResponse(
    val popupId: String,
)

data class GetPopupDetailResponse(
    val popupId: String,
    val title: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val category: Category,
    val address: String,
    val status: PopupStatus,
    val images: List<String>,
    val bookmarkId: String?,
)

data class GetPopupListByCategoryResponse(
    val popupId: String,
    val title: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val category: Category,
    val image: String,
    val status: PopupStatus,
    val address: String,
)
