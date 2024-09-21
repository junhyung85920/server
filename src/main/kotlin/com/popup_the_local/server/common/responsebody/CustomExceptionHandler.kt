package com.popup_the_local.server.common.responsebody

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun methodArgumentNotValidException(
        ex: MethodArgumentNotValidException,
    ): ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mutableMapOf<String, String>()
        ex.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage
            errors[fieldName] = errorMessage ?: "Not Exception RequestMessage"
        }

        return ResponseEntity(
            BaseResponse(
                ErrorCode.BAD_REQUEST.statusCode,
                ErrorCode.BAD_REQUEST.msg,
                errors,
            ),
            HttpStatus.BAD_REQUEST,
        )
    }

    @ExceptionHandler(InvalidInputException::class)
    protected fun invalidInputException(ex: InvalidInputException): ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mapOf(ex.fieldName to (ex.message ?: "Not Exception RequestMessage"))
        return ResponseEntity(
            BaseResponse(
                ErrorCode.BAD_REQUEST.statusCode,
                ErrorCode.BAD_REQUEST.msg,
                errors,
            ),
            HttpStatus.BAD_REQUEST,
        )
    }

}
