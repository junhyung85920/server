package com.popup_the_local.server.controller

import com.popup_the_local.server.common.responsebody.BaseResponse
import com.popup_the_local.server.dto.*
import com.popup_the_local.server.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/google-login")
    fun googleLogin(
        @RequestBody request: LoginRequest,
    ): BaseResponse<UserInfoResponse> {
        return BaseResponse(msg = "로그인 성공", data = authService.googleLogin(request))
    }

    @PostMapping("/customer-register")
    fun customerRegister(
        @RequestBody request: CustomerRegisterRequest,
    ): BaseResponse<ResisterResponse> {
        return BaseResponse(msg = "회원 가입 성공", data = authService.customerRegister(request))
    }

    @PostMapping("/seller-register")
    fun sellerRegister(
        @RequestBody request: SellerRegisterRequest,
    ): BaseResponse<ResisterResponse> {
        return BaseResponse(msg = "회원 가입 성공", data = authService.sellerRegister(request))
    }
}