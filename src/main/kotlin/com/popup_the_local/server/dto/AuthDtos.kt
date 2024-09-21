package com.popup_the_local.server.dto

import com.popup_the_local.server.entity.Address
import org.example.springmongodb.entity.Role

data class UserInfoResponse(
    var accessToken: String? = null,
    val name: String,
    val email: String,
    var picture: String,
    var isMember: Boolean?,
    var memberId: String?,
    var role: Role?
)

data class LoginRequest(
    val displayName: String,
    val email: String,
    val id: String,
    val photoUrl: String,
)

data class CustomerRegisterRequest(
    val name: String,
    val email: String,
    var image: String,
    val socialId: String,
)

data class SellerRegisterRequest (
    val name: String,
    val email: String,
    var image: String,
    val socialId: String,
    val phone: String,
    val address: Address,
    val businessNumber: String
)

data class ResisterResponse(
    val memberId: String,
)