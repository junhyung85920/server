package com.popup_the_local.server.common.responsebody

data class BaseResponse<T>(
    val status: Int = SuccessCode.SUCCESS.statusCode,
    val msg: String = SuccessCode.SUCCESS.msg,
    val data: T? = null,
)