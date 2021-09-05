package com.example.cinema.dao

import com.example.cinema.entity.Cinema
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.sql.Statement
import javax.sql.DataSource

@Repository
class CinemaDao(override val dataSource: DataSource) : DAO<Cinema> {
    override val resource = "cinemas"
    override val fields = "name, is_deleted"

    override fun create(e: Cinema): Cinema {
        val query = createSqlTemplate.append(" VALUES ('${e.name}', ${e.isDeleted})")
        getConnection().use{
            val statement = it.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS)
            statement.execute()
            statement.generatedKeys.use { st ->
                if (st!!.next()) {
                    e.id = st.getInt(1)
                }
            }
            return e
        }
    }

    /**
     * Проверяем есть ли созданные (активные) места в кинотеатре (нужно для автогенерации мест)
     */
    fun checkPlacesInCinema(cinemaId: Int): Boolean {
        val query = """SELECT EXISTS(
                            SELECT 1 FROM places
                            WHERE cinema_id  = $cinemaId
                            AND NOT is_deleted)"""

        getConnection().use{
            val set = it.createStatement().executeQuery(query)
            set.next()
            return set.getBoolean(1)
        }
    }

    fun getPlaceIds(cinemaId: Int): List<Int> {
        val query = """SELECT id 
                            FROM places
                            WHERE cinema_id = $cinemaId
                            AND NOT is_deleted"""

        getConnection().use{
            val set = it.createStatement().executeQuery(query)
            val result = ArrayList<Int>()
            while (set.next()) {
                result.add(set.getInt("id"))
            }
            return result
        }
    }

    override fun update(e: Cinema) {
        TODO("Not yet implemented")
    }

    override fun parseSet(set: ResultSet?): List<Cinema> {
        val list = ArrayList<Cinema>()
        if (set != null) {
            while (set.next()) {
                list.add(
                    Cinema(
                        set.getInt("id"),
                        set.getString("name"),
                        set.getInt("size"),
                        set.getBoolean("is_deleted")
                    )
                )
            }
        }
        return list
    }

    fun getSessionsIdsByField(id: Int): List<Int> {
        val query = """SELECT id 
                            FROM sessions
                            WHERE cinema_id = $id
                            AND NOT is_deleted"""

        getConnection().use{
            val set = it.createStatement().executeQuery(query)
            val result = ArrayList<Int>()
            while (set.next()) {
                result.add(set.getInt("id"))
            }
            return result
        }
    }
}
