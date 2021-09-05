package com.example.cinema.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import java.util.*

/**
 * Сущность места в кинотеатре - хранит № ряда, № места, id кинотеатра
 */
data class Place(var id: Int,
                 var row: Int,
                 var number: Int,
                 var cinemaId: Int,
                 @JsonIgnore var isDeleted: Boolean = false)

data class PlaceDto(var id: Int,
                 var row: Int,
                 var number: Int,
                 var isFree: Boolean)

/**
 * Сущность сеанса - хранит название, id кинотеатра
 */
data class Session(var id: Int,
                   var name: String,
                   var cinemaId: Int,
                   @JsonIgnore var isDeleted: Boolean = false) {
    @JsonIgnore var places: List<Place> = ArrayList()
}

/**
 * Сущность кинотеатра - хранит имя и количество мест
 */
data class Cinema(var id: Int,
                  var name: String,
                  var size: Int = 0,
                  @JsonIgnore var isDeleted: Boolean = false) {
    @JsonIgnore var placeIds: List<Int> = ArrayList()
}

/**
 * Сущность билета на сеанс - хранит ссылку на покупателя, id места, id сеанса, дату продажи
 */
data class Ticket(var id: Int,
                  var customer: String,
                  var placeId: Int,
                  var sessionId: Int,
                  var saleDate: LocalDateTime?,
                  @JsonIgnore var isDeleted: Boolean = false)

/**
 *  Служебные классы для передачи параметров
 */
data class RequestParams(val ids: List<Int> = listOf(),
                         val withDeleted: Boolean = false)
data class BatchParams(val rows: Int,
                       val numbers: Int,
                       val cinemaId: Int)
data class ReservedParams(val placeIds: Set<Int>,
                          val sessionId: Int,
                          val customer: String = UUID.randomUUID().toString(),
                          val cinemaId: Int)
