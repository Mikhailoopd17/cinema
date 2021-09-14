package com.example.cinema.rest

import com.example.cinema.entity.ReservedParams
import com.example.cinema.entity.RequestParams
import com.example.cinema.entity.Ticket
import com.example.cinema.exeptions.RestException
import com.example.cinema.service.TicketService
import org.springframework.context.annotation.Scope
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tickets", produces = ["application/json; charset=UTF-8"])
class TicketController(val service: TicketService) {

    @GetMapping
    fun getAll(): List<Ticket> {
        return service.getList(RequestParams())
    }

    @PostMapping("/reserve")
    fun reservation(@RequestBody params: ReservedParams): List<Ticket> {
        if (params.placeIds.isEmpty() || params.cinemaId < 1 || params.sessionId < 1) {
            throw RestException("Required fields are missing. $params")
        }

        val ids = service.reservationTickets(params)
        return service.getList(RequestParams(ids))
    }
}
