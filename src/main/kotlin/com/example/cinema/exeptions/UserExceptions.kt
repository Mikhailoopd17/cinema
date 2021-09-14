package com.example.cinema.exeptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import com.example.cinema.LOG
import org.postgresql.util.PSQLException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class ExceptionHandlingAdvice() {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleBadRequestException(e: RestException): ErrorMessage {
        LOG.error("Error: " + e.message)
        return ErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            e.message
        )
    }


    // это не очень хорошая практика - сделал только для отладки
    /*@ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleSQLException(e: PSQLException): ErrorMessage {
        LOG.error("Error: " + e.message)
        return ErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            "SQL error! ${e.message}"
        )
    }*/

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleServerException(e: ServerException): ErrorMessage {
        LOG.error("Error: " + e.message)
        return ErrorMessage(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            e.message
        )
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleInternalServerError(e: NullPointerException): ErrorMessage {
        LOG.error("Error: " + e.message)
        return ErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            "${e.message}"
        )
    }
}


data class ErrorMessage(val code: Int, val message: String)

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
data class RestException(override val message: String) : RuntimeException()

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
data class ServerException(override val message: String) : RuntimeException()
