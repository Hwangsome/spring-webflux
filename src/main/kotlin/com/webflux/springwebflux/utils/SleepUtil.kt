package com.webflux.springwebflux.utils

object SleepUtil {
    fun sleepSeconds(seconds: Int) {
        Thread.sleep(seconds * 1000L)
    }
}