package com.example.cinema.dao

import com.example.cinema.entity.Place
import com.example.cinema.entity.PlaceDto
import com.example.cinema.entity.ReservedParams
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.sql.Statement
import javax.sql.DataSource

@Repository
class PlaceDao(override val dataSource: DataSource) : DAO<Place> {
    final override val resource = "places"
    override val fields = "row, number, cinema_id, is_deleted"

    override fun create(e: Place): Place {
        val query = createSqlTemplate.append(" VALUES (${e.row}, ${e.number}, ${e.cinemaId}, ${e.isDeleted})")
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

    fun checkExistPlace(place: Place): Boolean {
        val query = """SELECT EXISTS(
                            SELECT 1 FROM $resource
                            WHERE row = ${place.row}
                            AND number = ${place.number}
                            AND cinema_id  = ${place.cinemaId}
                            AND NOT is_deleted)"""

        getConnection().use{
            val set = it.createStatement().executeQuery(query)
            set.next()
            return set.getBoolean(1)
        }
    }

    fun checkExistAndFreePlaces(params: ReservedParams): Boolean {
        val query = """SELECT ${params.placeIds.size} = (
                            SELECT COUNT(*) FROM places p
                            WHERE p.id in (${params.placeIds.joinToString(",")})
                            AND p.cinema_id = ${params.cinemaId}
                            AND NOT p.is_deleted)
                       AND (SELECT  0 = (SELECT COUNT(id) FROM tickets 
                       WHERE place_id IN (${params.placeIds.joinToString(",")}) 
                       AND session_id = ${params.sessionId} 
                       AND NOT is_deleted))"""

        getConnection().use{
            val set = it.createStatement().executeQuery(query)
            set.next()
            return set.getBoolean(1)
        }
    }

    /**
     * Создаем сразу пачку мест
     */
    fun createBatch(values: String): Boolean {
        val query = createSqlTemplate.append(" VALUES $values")
        getConnection().use{
            return it.createStatement().execute(query.toString())
        }
    }

    fun getPlacesByCinemaId(sessionId: Int, cinemaId: Int): List<PlaceDto> {
        val query = """SELECT 
                            p.id, p.row, p.number,
                            (SELECT NOT EXISTS(SELECT 1 FROM tickets t 
                                                WHERE t.place_id = p.id AND t.session_id = $sessionId
                                                AND NOT t.is_deleted)) AS is_free
                            FROM places p
                            WHERE p.cinema_id = $cinemaId
                            AND NOT p.is_deleted"""

        getConnection().use{
            val set = it.createStatement().executeQuery(query)
            val result = ArrayList<PlaceDto>()
            while (set.next()) {
                result.add(
                    PlaceDto(set.getInt("id"),
                        set.getInt("row"),
                        set.getInt("number"),
                        set.getBoolean("is_free")
                    )
                )
            }
            return result
        }
    }

    override fun update(e: Place) {
    }

    override fun parseSet(set: ResultSet?): List<Place> {
        val list = ArrayList<Place>()
        if (set != null) {
            while (set.next()) {
                list.add(
                    Place(
                        set.getInt("id"),
                        set.getInt("row"),
                        set.getInt("number"),
                        set.getInt("cinema_id"),
                        set.getBoolean("is_deleted")
                    ))
            }
        }
        return list
    }




}
