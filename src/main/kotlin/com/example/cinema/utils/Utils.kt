package com.example.cinema.utils

import com.example.cinema.entity.ReservedParams
import com.example.cinema.entity.Ticket

object Utils {

    /**
     * метод генерирует матрицу посадочных мест в зале на основе длины (row) и ширины (numberInRow)
     */
    @JvmStatic
    fun generatePlaces(rows: Int, numberInRow: Int, cinemaId: Int): String {
        val sqlValues = StringBuffer()
        for (i in 1..rows) {
            for (j in 1..numberInRow) {
                sqlValues.append("\n ($i, $j, $cinemaId, false)")
                if (!(i == rows && j == numberInRow)) {
                    sqlValues.append(",")
                }

            }
        }
        return sqlValues.toString()
    }


    @JvmStatic
    fun buildTicketValues(params: ReservedParams): String {
        val values = StringBuffer()
        params.placeIds.forEach {
            values.append("('${params.customer}', ${it}, ${params.sessionId}, now(), false),")
        }
        values.deleteCharAt(values.lastIndexOf(","))
        return values.toString()
    }
}
