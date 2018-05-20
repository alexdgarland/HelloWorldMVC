package personal.alexgarland.helloworldmvc.controller

import ch.qos.logback.classic.Level
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import personal.alexgarland.helloworldmvc.model.Employee
import personal.alexgarland.helloworldmvc.service.repository.IEmployeeRepository
import personal.alexgarland.helloworldmvc.testutil.LogbackEventChecker
import java.util.*
import java.util.Arrays.asList

class EmployeeActionControllerTest {

    private var testController: EmployeeActionController? = null

    @Mock
    private val employeeRepository: IEmployeeRepository? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(employeeRepository!!.employeeList).thenReturn(employees)
        `when`(employeeRepository.getEmployeeById(1)).thenReturn(employee1)

        testController = EmployeeActionController(employeeRepository)

        LogbackEventChecker.setup()
    }

    @After
    fun teardown() = LogbackEventChecker.teardown()

    @Test
    fun listsEmployees() {
        val result = testController!!.employeeList

        assertEquals("employeeList", result.viewName)

        val model = result.model
        assertEquals(1, model.size.toLong())
        assertEquals(employees, model["employeeList"])

        LogbackEventChecker()
                .withExpectedEvent(Level.INFO, "Listing 2 employees...")
                .withExpectedEvent(Level.DEBUG, employee1.toString())
                .withExpectedEvent(Level.DEBUG, employee2.toString())
                .verifyCalls()

    }

    @Test
    fun getsAddEmployeeForm() {
        val result = testController!!.addEmployeeForm
        val model = result.model

        assertEquals("employeeAdd", result.viewName)
        assertEquals(1, model.size.toLong())
        assert(model["employeeEntity"] is Employee)

        LogbackEventChecker()
                .withExpectedEvent(Level.INFO, "Setting up \"Add Employee\" form")
                .verifyCalls()

    }

    @Test
    fun addsEmployee() {
        val result = testController!!.addEmployee(employee1)
        verify<IEmployeeRepository>(employeeRepository, times(1)).addEmployee(employee1)
        assertEquals("redirect:employees", result.viewName)
        assertEquals(0, result.model.size.toLong())

        LogbackEventChecker()
                .withExpectedEvent(Level.INFO, "Adding employee:")
                .withExpectedEvent(Level.INFO, employee1.toString())
                .verifyCalls()

    }

    @Test
    fun deletesEmployee() {

        testController!!.deleteEmployeeById(3)

        verify<IEmployeeRepository>(employeeRepository, times(1))
                .deleteEmployeeById(3)

        LogbackEventChecker()
                .withExpectedEvent(Level.WARN, "Deleting employee with ID 3")
                .verifyCalls()

    }

    @Test
    fun getsUpdateEmployeeForm() {
        val result = testController!!.getUpdateEmployeeForm(1)
        val model = result.model

        assertEquals("employeeUpdate", result.viewName)
        assertEquals(1, model.size.toLong())
        assertEquals(employee1, model["employeeEntity"])

        LogbackEventChecker()
                .withExpectedEvent(Level.INFO, "Setting up Employee Update form")
                .verifyCalls()

    }

    @Test
    fun updatesEmployee() {

        val employee1NewDetails = Employee(1, "Robert", "Dobalina", "Bob")
        testController!!.updateEmployee(employee1NewDetails)

        verify<IEmployeeRepository>(employeeRepository, times(1))
                .updateEmployee(employee1NewDetails)

        LogbackEventChecker()
                .withExpectedEvent(Level.INFO, "Updating employee with details:")
                .withExpectedEvent(Level.INFO, employee1NewDetails.toString())
                .verifyCalls()

    }

    companion object {
        private val employee1 = Employee(1, "Robert", "Dobalina")
        private val employee2 = Employee(2, "Teren", "Jones")
        private val employees = ArrayList(asList(employee1, employee2))
    }

}
