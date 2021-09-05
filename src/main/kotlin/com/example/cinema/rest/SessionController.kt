package com.example.cinema.rest

import com.example.cinema.entity.PlaceDto
import com.example.cinema.entity.Session
import com.example.cinema.exeptions.RestException
import com.example.cinema.service.SessionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/sessions", produces = ["application/json; charset=UTF-8"])
class SessionController(override val service: SessionService) : IController<Session> {
    @GetMapping("/{id}/places")
    fun getPlacesBySession(@PathVariable id: Int): List<PlaceDto> {
        return service.getPlacesBySession(id)
    }

    override fun verifyBody(entity: Session) {
        if (entity.name.isBlank() || entity.cinemaId < 1) {
            throw RestException("Required fields are missing. $entity")
        }
    }
}
