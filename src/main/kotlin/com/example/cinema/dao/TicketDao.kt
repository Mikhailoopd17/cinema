package com.example.cinema.dao

import com.example.cinema.entity.Ticket
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.sql.Statement
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.sql.DataSource

@Repository
class TicketDao(override val dataSource: DataSource) : DAO<Ticket> {

    override val resource = "tickets"
    override val fields = "customer, place_id, session_id, sale_date, is_deleted"


    override fun create(e: Ticket): Ticket {
        val query = createSqlTemplate.append(
            " VALUES ('${e.customer}', ${e.placeId} ${e.sessionId}, ${e.saleDate}, ${e.isDeleted})"
        )
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

    fun createBatch(values: String): Boolean {
        val query = createSqlTemplate.append(" VALUES $values")
        getConnection().use{
            return it.createStatement().execute(query.toString())
        }
    }

    override fun update(e: Ticket) {
        TODO("Not yet implemented")
    }

    override fun parseSet(set: ResultSet?): List<Ticket> {
        val list = ArrayList<Ticket>()
        if (set != null) {
            while (set.next()) {
                list.add(
                    Ticket(
                        set.getInt("id"),
                        set.getString("customer"),
                        set.getInt("place_id"),
                        set.getInt("session_id"),
                        LocalDateTime.parse(set.getString("sale_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")),
                        set.getBoolean("is_deleted")
                        ))
            }
        }
        return list
    }
}
