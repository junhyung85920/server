package com.popup_the_local.server.dto

data class CreateCommentRequest(
    val popupId: String,
    val content: String,
)


data class CreateCommentResponse(
     val commentId: String,
)