package com.example.cinema.rest

import com.example.cinema.entity.Cinema
import com.example.cinema.entity.Session
import com.example.cinema.exeptions.RestException
import com.example.cinema.service.CinemaService
import com.example.cinema.service.IService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/cinema", produces = ["application/json; charset=UTF-8"])
class CinemaController(override val service: IService<Cinema>) : IController<Cinema> {
    override fun verifyBody(entity: Cinema) {
        if (entity.name.isBlank()) {
            throw RestException("Required fields are missing. $entity")
        }
    }
}
