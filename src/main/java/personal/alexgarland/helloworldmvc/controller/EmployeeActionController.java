package personal.alexgarland.helloworldmvc.controller;

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
 
	private IEmployeeRepository employeeRepository;
	
	private static final ModelAndView EMPLOYEE_LIST_REDIRECT = new ModelAndView("redirect:employees");
	
	@Autowired
	public void setEmployeeManager(IEmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@RequestMapping("/employees")
	public ModelAndView getEmployeeList() {	
		ModelAndView mv = new ModelAndView("employeeList");
		mv.addObject("employeeList", employeeRepository.getEmployeeList());
		return mv;
	}
	
	@RequestMapping("/showAddEmployeeForm")
	public ModelAndView getAddEmployeeForm() {
		ModelAndView mv = new ModelAndView("employeeAdd");
		mv.addObject("employeeEntity", new Employee());
		return mv;
	}
 
	@RequestMapping("/addEmployee")
	public ModelAndView addEmployee(@ModelAttribute Employee e) {
		employeeRepository.addEmployee(e);
		return EMPLOYEE_LIST_REDIRECT;
	}
	
	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST)
	public ModelAndView deleteEmployeeById(@RequestParam int employeeId) {
		employeeRepository.deleteEmployeeById(employeeId);
		return EMPLOYEE_LIST_REDIRECT;
	}

	@RequestMapping("/showUpdateEmployeeForm")
	public ModelAndView getUpdateEmployeeForm(@RequestParam int employeeId) {
		ModelAndView mv = new ModelAndView("employeeUpdate");
		Employee e = employeeRepository.getEmployeeById(employeeId);
		mv.addObject("employeeEntity", e);
		return mv;
	}
	
	@RequestMapping("/updateEmployee")
	public ModelAndView updateEmployee(@ModelAttribute Employee e) {
		employeeRepository.updateEmployee(e);
		return EMPLOYEE_LIST_REDIRECT;
	}
	
}
