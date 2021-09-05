package com.example.cinema.service

import com.example.cinema.dao.PlaceDao
import com.example.cinema.dao.TicketDao
import com.example.cinema.entity.ReservedParams
import com.example.cinema.entity.Ticket
import com.example.cinema.exeptions.RestException
import com.example.cinema.utils.Utils
import org.springframework.stereotype.Service

@Service
class TicketService(override val dao: TicketDao, val placeDao: PlaceDao) : IService<Ticket> {
    /**
     *  одновременно можем купить n-билетов на конкретный сеанс (исключаем одновременную покупку на разные сеансы)
     */
    @Synchronized
    fun reservationTickets(params: ReservedParams): Boolean {
        if (!placeDao.checkExistAndFreePlaces(params)) {
            throw RestException("Selected places already reserved or not found")
        }

        dao.createBatch(Utils.buildTicketValues(params))
        return true
    }
}
