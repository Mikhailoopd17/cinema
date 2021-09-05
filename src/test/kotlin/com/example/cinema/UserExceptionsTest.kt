package com.example.cinema

import com.example.cinema.exeptions.RestException
import org.junit.jupiter.api.Test

class UserExceptionsTest {
    @Test
    fun restException() {
        try {
            throw RestException("test error")
        } catch (e: RestException) {
            assert("test error" == e.message)
        }
    }
}
