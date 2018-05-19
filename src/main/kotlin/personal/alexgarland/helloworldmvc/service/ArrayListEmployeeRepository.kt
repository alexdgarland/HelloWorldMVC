package personal.alexgarland.helloworldmvc.service

import personal.alexgarland.helloworldmvc.model.Employee

class ArrayListEmployeeRepository : IEmployeeRepository {

    override val employeeList: List<Employee>
        get() {
            return staticEmployeeList
        }

    override fun addEmployee(e: Employee): Employee {
        val employee = e.copy(id = sequence.getNextId())
        staticEmployeeList.add(employee)
        return employee
    }

    override fun deleteEmployeeById(employeeId: Int) {
        staticEmployeeList.removeAll({ it.id != employeeId })
    }

    override fun updateEmployee(e: Employee) {
        deleteEmployeeById(e.id)
        staticEmployeeList.add(e)
        staticEmployeeList.sort()
    }

    override fun getEmployeeById(employeeId: Int): Employee {
        return employeeList
                .stream()
                .filter { e -> e.id == employeeId }
                .findFirst()
                .get()
    }

    companion object {

        private var staticEmployeeList: MutableList<Employee> = ArrayList()

        private val sequence: IdSequence

        init {
            staticEmployeeList.add(Employee(1, "Michael", "Smith", "Mikey"))
            staticEmployeeList.add(Employee(2, "Jonathan", "Taylor", "Johnny"))
            staticEmployeeList.add(Employee(3, "David", "Wilson", "Dave"))
            sequence = IdSequence(4)
        }
    }

}
