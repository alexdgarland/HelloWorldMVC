package personal.alexgarland.helloworldmvc.service

import personal.alexgarland.helloworldmvc.model.Employee
import java.io.FileInputStream
import java.io.IOException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException
import java.util.*

abstract class AbstractDatabaseEmployeeRepository : IEmployeeRepository {

    private val dbProperties: Properties

    protected abstract val dbPropertiesFileName: String

    private val connection: Connection
        @Throws(SQLException::class)
        get() = DriverManager.getConnection(dbProperties.getProperty("url"), dbProperties)

    protected abstract val addEmployeeQuery: String

    protected abstract val getEmployeeListQuery: String

    protected abstract val deleteQuery: String

    protected abstract val updateQuery: String

    protected abstract val getEmployeeByIdQuery: String

    init {
        val classLoader = Thread.currentThread().contextClassLoader
        val path = classLoader.getResource(dbPropertiesFileName)!!.path
        val properties = Properties()
        try {
            properties.load(FileInputStream(path))
        } catch (ex: IOException) {
            throw RuntimeException(ex)
        }

        this.dbProperties = properties
    }

    private fun <A> withPS(sql: String, function: (PreparedStatement) -> A): A {
        try {
            connection.use { con -> con.prepareStatement(sql).use { ps -> return function(ps) } }
        } catch (ex: SQLException) {
            throw RuntimeException(ex.message)
        }
    }

    @Throws(SQLException::class)
    protected abstract fun addEmployeeWithPS(ps: PreparedStatement, e: Employee): Employee

    override fun addEmployee(e: Employee): Employee {
        return withPS(addEmployeeQuery, { ps -> addEmployeeWithPS(ps, e) })
    }

    @Throws(SQLException::class)
    protected abstract fun getEmployeeListWithPS(ps: PreparedStatement): List<Employee>

    override val employeeList: List<Employee>
        get() {
            return withPS(getEmployeeListQuery, { ps -> getEmployeeListWithPS(ps) })
        }

    @Throws(SQLException::class)
    protected abstract fun deleteEmployeeWithPS(ps: PreparedStatement, employeeId: Int): Int

    override fun deleteEmployeeById(employeeId: Int) {
        withPS<Int>(deleteQuery, { ps -> deleteEmployeeWithPS(ps, employeeId) })
    }

    @Throws(SQLException::class)
    protected abstract fun updateEmployeeWithPS(ps: PreparedStatement, e: Employee): Int

    override fun updateEmployee(e: Employee) {
        withPS(updateQuery, { ps -> updateEmployeeWithPS(ps, e) })
    }

    @Throws(SQLException::class)
    protected abstract fun getEmployeeByIdWithPS(ps: PreparedStatement, employeeId: Int): Employee

    override fun getEmployeeById(employeeId: Int): Employee {
        return withPS(getEmployeeByIdQuery, { ps -> getEmployeeByIdWithPS(ps, employeeId) })
    }

}