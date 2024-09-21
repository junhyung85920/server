package com.popup_the_local.server.dto

import org.example.springmongodb.entity.Role

data class CreateMemberRequest(
    val email: String,
    val role: String,
    val image: String,
    val name: String,
)

data class GetMemberDetailResponse(
    val email: String,
    val role: Role,
    val image: String,
    val name: String,
)