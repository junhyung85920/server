package com.popup_the_local.server.dto

import com.popup_the_local.server.entity.Category
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate

data class ApplyPopupRequest(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val description: String,
    val category: Category,
    val images: List<MultipartFile>,
    val address: String,
)


data class ApplyPopupResponse(
    val applicationId: String,
)

data class RecommendEventResponse (
    val content: List<String>,
)

data class RecommendEventRequest (
    val description: String,
)
