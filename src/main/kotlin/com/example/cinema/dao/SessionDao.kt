package com.example.cinema.dao

import com.example.cinema.entity.Session
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.sql.Statement
import javax.sql.DataSource

@Repository
class SessionDao(override val dataSource: DataSource) : DAO<Session> {
    override val resource = "sessions"
    override val fields = "name, cinema_id, is_deleted"

    override fun create(e: Session): Session {
        val query = createSqlTemplate.append(" VALUES ('${e.name}', ${e.cinemaId}, ${e.isDeleted})")
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



    override fun update(e: Session) {
        TODO("Not yet implemented")
    }

    override fun parseSet(set: ResultSet?): List<Session> {
        val list = ArrayList<Session>()
        if (set != null) {
            while (set.next()) {
                list.add(
                    Session(
                        set.getInt("id"),
                        set.getString("name"),
                        set.getInt("cinema_id"),
                        set.getBoolean("is_deleted")
                    )
                )
            }
        }
        return list
    }

}
