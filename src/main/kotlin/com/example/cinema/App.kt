package com.example.cinema

import com.example.cinema.utils.LocalCache

object App : CinemaApplication() {
    @JvmStatic
    var cache: LocalCache = LocalCache
}
