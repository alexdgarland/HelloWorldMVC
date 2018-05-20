package personal.alexgarland.helloworldmvc.service.repository

import personal.alexgarland.helloworldmvc.model.Employee

interface IEmployeeRepository {

    val employeeList: List<Employee>

    fun addEmployee(e: Employee): Employee

    fun deleteEmployeeById(employeeId: Int)

    fun updateEmployee(e: Employee)

    fun getEmployeeById(employeeId: Int): Employee

}
