package personal.alexgarland.helloworldmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import personal.alexgarland.helloworldmvc.service.IEmployeeManager;

@Controller
public class EmployeeListController {
 
	private IEmployeeManager employeeManager;
	 
	@Autowired
	public void setEmployeeManager(IEmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}

	@RequestMapping("/employees")
	public ModelAndView handleRequest() {	
		ModelAndView mv = new ModelAndView("employeeList");
		mv.addObject("employeeList", employeeManager.getEmployeeList());
		return mv;
	}
}


