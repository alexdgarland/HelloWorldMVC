package personal.alexgarland.helloworldmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import personal.alexgarland.helloworldmvc.service.IEmployeeManager;

public class EmployeeListController implements Controller {
 
	protected IEmployeeManager employeeManager;
	 
	public IEmployeeManager getEmployeeManager() {
		return employeeManager;
	}
 
	public void setEmployeeManager(IEmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}

	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {	
		ModelAndView mv = new ModelAndView("employeeList");
		mv.addObject("employeeList", employeeManager.getEmployeeList());
		return mv;
	}
}


