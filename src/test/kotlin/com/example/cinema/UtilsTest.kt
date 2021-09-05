package com.example.cinema

import com.example.cinema.entity.ReservedParams
import com.example.cinema.utils.Utils
import org.junit.jupiter.api.Test

class UtilsTest {
    @Test
    fun generatePlacesTest() {
        val str = "\n" +
                " (1, 1, 1, false),\n" +
                " (1, 2, 1, false),\n" +
                " (2, 1, 1, false),\n" +
                " (2, 2, 1, false),\n" +
                " (3, 1, 1, false),\n" +
                " (3, 2, 1, false)"
        val result = Utils.generatePlaces(3, 2, 1)
        assert(result == str)
    }


    @Test
    fun buildTicketValuesTest() {
        val str = "('customer', 1, 5, now(), false)," +
                "('customer', 2, 5, now(), false)," +
                "('customer', 3, 5, now(), false)"
        val tickets = Utils.buildTicketValues(ReservedParams(setOf(1, 2, 3), 5, "customer", 25))
        assert(tickets == str)
    }
}
