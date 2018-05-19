package personal.alexgarland.helloworldmvc.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*

class EmployeeTest {

    @Test
    fun returnsFullNameWithoutNickname() {
        val employee = Employee(1, "Robert", "Dobalina")
        assertEquals("Robert Dobalina", employee.fullName)
    }

    @Test
    fun returnsFullNameWithNickname() {
        val employee = Employee(1, "Robert", "Dobalina", "Bob")
        assertEquals("Robert \"Bob\" Dobalina", employee.fullName)
    }

    @Test
    fun returnsToString() {
        val employee = Employee(1, "Robert", "Dobalina", "Bob")
        assertEquals("Employee 1: Robert \"Bob\" Dobalina", employee.toString())
    }

    @Test
    fun sortsAsExpected() {
        val employee1 = Employee(1, "John", "Kennedy", "Jack")
        val employee2 = Employee(2, "Robert", "Dobalina")
        val employee3 = Employee(3, "James", "Fallon", "Jimmy")
        val list = ArrayList<Employee>()
        list.add(employee2)
        list.add(employee3)
        list.add(employee1)

        list.sort()

        assertTrue(list[0] === employee1)
        assertTrue(list[1] === employee2)
        assertTrue(list[2] === employee3)
    }

    @Test
    fun idAccessorsWork() {
        val employee = Employee(1, "Robert", "Dobalina")
        employee.id = 2
        assertEquals(2, employee.id.toLong())
    }

    @Test
    fun firstNameAccessorsWork() {
        val employee = Employee(1, "Bruce", "Jenner")
        employee.firstName = "Caitlyn"
        assertEquals("Caitlyn", employee.firstName)
    }

    @Test
    fun lastNameAccessorsWork() {
        val employee = Employee(1, "Kim", "Kardashian")
        employee.lastName = "Kardashian West"
        assertEquals("Kardashian West", employee.lastName)
    }

    @Test
    fun nickNameAccessorsWork() {
        val employee = Employee(1, "Kanye", "West", "The Louis Vuitton Don")
        employee.nickName = "The Lyor Cohen of Dior Homme"
        assertEquals("The Lyor Cohen of Dior Homme", employee.nickName)
    }

    @Test
    fun copiesWithNewId() {
        val employee = Employee(1, "Robert", "Dobalina")
        val newEmployee = employee.copyWithNewId(2)
        assertEquals(Employee(2, "Robert", "Dobalina"), newEmployee)
    }

}
