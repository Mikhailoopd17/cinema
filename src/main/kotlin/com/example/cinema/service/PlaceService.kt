package com.example.cinema.service

import com.example.cinema.dao.CinemaDao
import com.example.cinema.dao.PlaceDao
import com.example.cinema.entity.BatchParams
import com.example.cinema.entity.Place
import com.example.cinema.exeptions.RestException
import com.example.cinema.utils.Utils
import org.springframework.stereotype.Service

@Service
class PlaceService(override val dao: PlaceDao, val cinemaDao: CinemaDao) : IService<Place> {
    override fun add(entity: Place): Place {
        // нельзя создать 2 одинаковых (активных) места в одном кинотеатре
        if (dao.checkExistPlace(entity)) {
            throw RestException("Place already exists! $entity")
        }
        return super.add(entity)
    }

    fun generatePlaces(params: BatchParams): Boolean {
        if (params.rows < 1 || params.numbers < 1 || params.cinemaId < 1) {
            throw RestException("Incorrect input in batch params: $params")
        }
        if (cinemaDao.getOneById(params.cinemaId, false) == null ) {
            throw RestException("Not found cinema with id=${params.cinemaId}")
        }
        // генерацию мест допускаем только в пустые кинотеатры (в которых нет мест)
        if (cinemaDao.checkPlacesInCinema(params.cinemaId)) {
            throw RestException("Error generate places in cinema. Cinema must be empty!")
        }
        // генерим VALUES строку SQL
        return dao.createBatch(Utils.generatePlaces(params.rows, params.numbers, params.cinemaId))
    }


}
