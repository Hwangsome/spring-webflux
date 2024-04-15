package com.webflux.springwebflux.exceptionhandler

import com.webflux.springwebflux.dto.InputFailedValidationResponse
import com.webflux.springwebflux.exception.InputValidationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class InputValidationHandler {
    @ExceptionHandler(InputValidationException::class)
    fun handleException(ex: InputValidationException): ResponseEntity<InputFailedValidationResponse> {
        val response = InputFailedValidationResponse(ex.errorCode, ex.input,  ex.message)
        return ResponseEntity.badRequest().body<InputFailedValidationResponse>(response)
    }
}