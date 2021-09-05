package com.example.cinema.dao

import com.example.cinema.entity.RequestParams
import com.example.cinema.exeptions.ServerException
import java.sql.Connection
import java.sql.ResultSet
import javax.sql.DataSource

interface DAO<E> {
    val dataSource: DataSource
    val resource: String
    val fields: String
    val selectSqlTemplate: StringBuffer
        get() = StringBuffer("SELECT * FROM $resource")
    val createSqlTemplate: StringBuffer
        get() = StringBuffer("INSERT INTO $resource ($fields)")
    val updateSqlTemplate: StringBuffer
        get() = StringBuffer("UPDATE $resource SET")
    val deleteSqlTemplate: StringBuffer
        get() = StringBuffer("DELETE FROM $resource")

    fun getConnection(): Connection {
        return dataSource.connection ?: throw ServerException("Error create connection to data source")
    }

    fun getList(params: RequestParams): List<E> {
        val query = selectSqlTemplate.append(" WHERE true")
        if (params.ids.isNotEmpty()) {
            query.append(" AND id in (${params.ids.joinToString(",")})")
        }
        if (!params.withDeleted) {
            query.append(" AND NOT is_deleted")
        }
        getConnection().use{
            return parseSet(it.createStatement().executeQuery(query.toString()))
        }
    }

    fun getOneById(id: Int, withDeleted: Boolean = true) : E? {
        return getList(RequestParams(listOf(id), withDeleted)).getOrNull(0)
    }

    fun delete(ids: List<Int>): Boolean {
        val query = updateSqlTemplate.append(" is_deleted = true WHERE id in (${ids.joinToString()})")
        getConnection().use {
            it.createStatement().execute(query.toString())
        }
        return true
    }

    fun create(e: E): E
    fun update(e: E)
    fun parseSet(set: ResultSet?): List<E>
}
