package personal.alexgarland.helloworldmvc.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import personal.alexgarland.helloworldmvc.model.Employee;
import personal.alexgarland.helloworldmvc.service.IEmployeeRepository;

@Controller
public class EmployeeActionController {

  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeActionController.class);

  private IEmployeeRepository employeeRepository;

  private static final ModelAndView EMPLOYEE_LIST_REDIRECT = new ModelAndView("redirect:employees");

  @Autowired
  public void setEmployeeRepository(IEmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @RequestMapping("/employees")
  public ModelAndView getEmployeeList() {

    List<Employee> employeeList = employeeRepository.getEmployeeList();

    LOGGER.info(String.format("Listing %d employees...", employeeList.size()));
    for (Employee e : employeeList) {
      LOGGER.debug(e.toString());
    }

    ModelAndView mv = new ModelAndView("employeeList");
    mv.addObject("employeeList", employeeList);
    return mv;
  }

  @RequestMapping("/showAddEmployeeForm")
  public ModelAndView getAddEmployeeForm() {

    LOGGER.info("Setting up \"Add Employee\" form");

    ModelAndView mv = new ModelAndView("employeeAdd");
    mv.addObject("employeeEntity", new Employee());
    return mv;
  }

  @RequestMapping("/addEmployee")
  public ModelAndView addEmployee(@ModelAttribute Employee e) {

    LOGGER.info("Adding employee:");
    LOGGER.info(e.toString());

    employeeRepository.addEmployee(e);
    return EMPLOYEE_LIST_REDIRECT;
  }

  @RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST)
  public ModelAndView deleteEmployeeById(@RequestParam int employeeId) {

    LOGGER.warn(String.format("Deleting employee with ID %d", employeeId));

    employeeRepository.deleteEmployeeById(employeeId);
    return EMPLOYEE_LIST_REDIRECT;
  }

  @RequestMapping("/showUpdateEmployeeForm")
  public ModelAndView getUpdateEmployeeForm(@RequestParam int employeeId) {

    LOGGER.info("Setting up Employee Update form");

    ModelAndView mv = new ModelAndView("employeeUpdate");
    Employee e = employeeRepository.getEmployeeById(employeeId);
    mv.addObject("employeeEntity", e);
    return mv;
  }

  @RequestMapping("/updateEmployee")
  public ModelAndView updateEmployee(@ModelAttribute Employee e) {

    LOGGER.info("Updating employee with details:");
    LOGGER.info(e.toString());

    employeeRepository.updateEmployee(e);
    return EMPLOYEE_LIST_REDIRECT;
  }

}
