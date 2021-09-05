package com.example.cinema.service

import com.example.cinema.dao.DAO
import com.example.cinema.entity.RequestParams

interface IService<E> {
    val dao: DAO<E>

    fun getList(params: RequestParams): List<E> {
        return dao.getList(params)
    }

    fun getById(id: Int): E? {
        return dao.getOneById(id)
    }

    fun add(entity: E): E {
        return dao.create(entity)
    }

    fun delete(id: Int): Boolean {
        return dao.delete(listOf(id))
    }
}
