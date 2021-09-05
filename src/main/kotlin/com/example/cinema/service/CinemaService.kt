package com.example.cinema.service

import com.example.cinema.dao.CinemaDao
import com.example.cinema.dao.PlaceDao
import com.example.cinema.dao.SessionDao
import com.example.cinema.entity.Cinema
import org.springframework.stereotype.Service

@Service
class CinemaService(override val dao: CinemaDao, val placeDao: PlaceDao, val sessionDao: SessionDao) : IService<Cinema> {
    override fun delete(id: Int): Boolean {
        val placeIds = dao.getPlaceIds(id)
        if (placeIds.isNotEmpty()) {
            placeDao.delete(placeIds)
        }
        val sessionIds = dao.getSessionsIdsByField(id)
        if (sessionIds.isNotEmpty()) {
            sessionDao.delete(sessionIds)
        }
        return super.delete(id)
    }
}
