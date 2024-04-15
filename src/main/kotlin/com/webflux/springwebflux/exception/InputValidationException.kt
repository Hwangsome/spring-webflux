package com.webflux.springwebflux.exception


class InputValidationException(val input: Int) : RuntimeException(MSG) {

    val errorCode: Int
        get() = Companion.errorCode

    companion object {
        private const val MSG = "allowed range is 10 - 20"
        private const val errorCode = 100
    }
}