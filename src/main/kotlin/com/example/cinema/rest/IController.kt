package com.example.cinema.rest

import com.example.cinema.entity.RequestParams
import com.example.cinema.service.IService
import org.springframework.web.bind.annotation.*

interface IController<E> {
    val service: IService<E>

    @PostMapping("/list")
    fun getList(@RequestBody params: RequestParams): List<E> {
        return service.getList(params)
    }

    @GetMapping("/{id}")
    fun getPlaceById(@PathVariable id: Int): E? {
        return service.getById(id)
    }

    @PostMapping
    fun addPlace(@RequestBody entity: E): E {
        verifyBody(entity)
        return service.add(entity)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): Boolean {
        return service.delete(id)
    }

    fun verifyBody(entity: E)
}
