package personal.alexgarland.helloworldmvc.service

import personal.alexgarland.helloworldmvc.model.Employee
import java.util.*

class HashMapEmployeeRepository : IEmployeeRepository {

    override val employeeList: List<Employee>
        get() = ArrayList(employeeMap.values)

    override fun addEmployee(e: Employee): Employee {
        val employee = e.copyWithNewId(sequence.getNextId())
        putEmployeeToMap(employee)
        return employee
    }

    override fun deleteEmployeeById(employeeId: Int) {
        employeeMap.remove(employeeId)
    }

    override fun updateEmployee(e: Employee) {
        putEmployeeToMap(e)
    }

    override fun getEmployeeById(employeeId: Int): Employee {
        return employeeMap[employeeId]!!
    }

    companion object {

        private var employeeMap: HashMap<Int, Employee> = HashMap()

        private fun putEmployeeToMap(e: Employee) {
            employeeMap[e.id] = e
        }

        private val sequence: IdSequence

        init {
            putEmployeeToMap(Employee(1, "Michael", "Smith", "Mikey"))
            putEmployeeToMap(Employee(2, "Jonathan", "Taylor", "Johnny"))
            putEmployeeToMap(Employee(3, "David", "Wilson", "Dave"))
            sequence = IdSequence(4)
        }
    }

}
