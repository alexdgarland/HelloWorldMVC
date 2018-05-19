package personal.alexgarland.helloworldmvc.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import personal.alexgarland.helloworldmvc.model.Employee
import personal.alexgarland.helloworldmvc.service.IEmployeeRepository

@Controller
class EmployeeActionController(private val employeeRepository: IEmployeeRepository) {

    val employeeList: ModelAndView
        @RequestMapping("/employees")
        get() {

            val employeeList = employeeRepository.employeeList

            LOGGER.info(String.format("Listing %d employees...", employeeList.size))
            for (e in employeeList) {
                LOGGER.debug(e.toString())
            }

            return ModelAndView("employeeList")
                    .withObject("employeeList", employeeList)

        }

    val addEmployeeForm: ModelAndView
        @RequestMapping("/showAddEmployeeForm")
        get() {

            LOGGER.info("Setting up \"Add Employee\" form")

            return ModelAndView("employeeAdd")
                    .withObject("employeeEntity", Employee())

        }

    @RequestMapping("/addEmployee")
    fun addEmployee(@ModelAttribute e: Employee): ModelAndView {

        LOGGER.info("Adding employee:")
        LOGGER.info(e.toString())

        employeeRepository.addEmployee(e)

        return EMPLOYEE_LIST_REDIRECT
    }

    @RequestMapping(value = "/deleteEmployee", method = [RequestMethod.POST])
    fun deleteEmployeeById(@RequestParam employeeId: Int): ModelAndView {

        LOGGER.warn(String.format("Deleting employee with ID %d", employeeId))

        employeeRepository.deleteEmployeeById(employeeId)

        return EMPLOYEE_LIST_REDIRECT
    }

    @RequestMapping("/showUpdateEmployeeForm")
    fun getUpdateEmployeeForm(@RequestParam employeeId: Int): ModelAndView {

        LOGGER.info("Setting up Employee Update form")

        val e = employeeRepository.getEmployeeById(employeeId)

        return ModelAndView("employeeUpdate")
                .withObject("employeeEntity", e)
    }

    @RequestMapping("/updateEmployee")
    fun updateEmployee(@ModelAttribute e: Employee): ModelAndView {

        LOGGER.info("Updating employee with details:")
        LOGGER.info(e.toString())

        employeeRepository.updateEmployee(e)

        return EMPLOYEE_LIST_REDIRECT
    }

    companion object {

        private val LOGGER = LoggerFactory.getLogger(EmployeeActionController::class.java)

        private val EMPLOYEE_LIST_REDIRECT = ModelAndView("redirect:employees")
    }

}
