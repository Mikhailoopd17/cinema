package com.example.cinema.service

import com.example.cinema.dao.PlaceDao
import com.example.cinema.dao.SessionDao
import com.example.cinema.entity.PlaceDto
import com.example.cinema.entity.Session
import com.example.cinema.exeptions.RestException
import org.springframework.stereotype.Service

@Service
class SessionService(override val dao: SessionDao, val placeDao: PlaceDao) : IService<Session> {

    fun getPlacesBySession(sessionId: Int): List<PlaceDto> {
        val session = dao.getOneById(sessionId, false) ?: throw RestException("Session by id=$sessionId not found!")
        return placeDao.getPlacesByCinemaId(sessionId, session.cinemaId)
    }
}
