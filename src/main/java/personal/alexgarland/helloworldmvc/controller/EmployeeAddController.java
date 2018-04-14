package personal.alexgarland.helloworldmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import personal.alexgarland.helloworldmvc.model.Employee;
import personal.alexgarland.helloworldmvc.service.FixedListEmployeeManager; 

@Controller
public class EmployeeAddController {
 
	@RequestMapping("/showEmployeeForm")
	public ModelAndView getEmployeeForm() {
		ModelAndView mv = new ModelAndView("employeeAdd");
		mv.addObject("employeeEntity", new Employee());
		return mv;
	}
 
	@RequestMapping("/addEmployee")
	public ModelAndView addEmployee(@ModelAttribute Employee e) throws CloneNotSupportedException {
		new FixedListEmployeeManager().addEmployee(e);
		return new ModelAndView("redirect:employees");
	}
}