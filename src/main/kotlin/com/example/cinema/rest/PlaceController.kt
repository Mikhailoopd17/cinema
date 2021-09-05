package com.example.cinema.rest

import com.example.cinema.entity.BatchParams
import com.example.cinema.entity.Place
import com.example.cinema.exeptions.RestException
import com.example.cinema.service.PlaceService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/place", produces = ["application/json; charset=UTF-8"])
class PlaceController(override val service: PlaceService) : IController<Place> {

    override fun verifyBody(entity: Place) {
        if (entity.number == 0 || entity.row == 0 || entity.cinemaId == 0) {
            throw RestException("Required fields are missing. $entity")
        }
    }

    @PostMapping("/generate")
    fun createBatch(@RequestBody params: BatchParams): HttpStatus {
        service.generatePlaces(params)
        return HttpStatus.OK
    }
}
