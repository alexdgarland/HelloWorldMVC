package personal.alexgarland.helloworldmvc.service.repository.database


import personal.alexgarland.helloworldmvc.model.Employee
import java.sql.PreparedStatement
import java.sql.SQLException
import java.util.*

class PostgresDatabaseEmployeeRepository : AbstractDatabaseEmployeeRepository() {

    override val dbPropertiesFileName: String = "postgres.database.properties"

    override val addEmployeeQuery: String =
            "INSERT INTO employees (first_name, last_name, nick_name) VALUES (?, ?, ?) RETURNING id"

    override val getEmployeeListQuery: String =
            "SELECT id, first_name, last_name, nick_name FROM employees WHERE logical_delete = false"

    override val deleteQuery: String =
            "UPDATE employees SET logical_delete = true WHERE id = ?"

    override val updateQuery: String =
            "UPDATE employees SET first_name = ?, last_name = ?, nick_name = ? WHERE id = ?"

    override val getEmployeeByIdQuery: String =
            "SELECT first_name, last_name, nick_name FROM employees WHERE logical_delete = false AND id = ?"

    @Throws(SQLException::class)
    override fun addEmployeeWithPS(ps: PreparedStatement, e: Employee): Employee {
        ps.setString(1, e.firstName)
        ps.setString(2, e.lastName)
        ps.setString(3, e.nickName)
        ps.executeQuery().use { rs ->
            rs.next()
            val dbGeneratedId = rs.getInt("id")
            return e.copy(id = dbGeneratedId)
        }
    }

    @Throws(SQLException::class)
    override fun getEmployeeListWithPS(ps: PreparedStatement): List<Employee> {
        val list = ArrayList<Employee>()
        ps.executeQuery().use { rs ->
            while (rs.next()) {
                val id = rs.getInt("id")
                val firstName = rs.getString("first_name")
                val lastName = rs.getString("last_name")
                val nickName = rs.getString("nick_name")
                val e = Employee(id, firstName, lastName, nickName)
                list.add(e)
            }
        }
        return list
    }

    @Throws(SQLException::class)
    override fun deleteEmployeeWithPS(ps: PreparedStatement, employeeId: Int): Int {
        ps.setInt(1, employeeId)
        return ps.executeUpdate()
    }

    @Throws(SQLException::class)
    override fun updateEmployeeWithPS(ps: PreparedStatement, e: Employee): Int {
        ps.setString(1, e.firstName)
        ps.setString(2, e.lastName)
        ps.setString(3, e.nickName)
        ps.setInt(4, e.id)
        return ps.executeUpdate()
    }

    @Throws(SQLException::class)
    override fun getEmployeeByIdWithPS(ps: PreparedStatement, employeeId: Int): Employee {
        ps.setInt(1, employeeId)
        ps.executeQuery().use { rs ->
            rs.next()
            val firstName = rs.getString("first_name")
            val lastName = rs.getString("last_name")
            val nickName = rs.getString("nick_name")
            return Employee(employeeId, firstName, lastName, nickName)
        }
    }

}
