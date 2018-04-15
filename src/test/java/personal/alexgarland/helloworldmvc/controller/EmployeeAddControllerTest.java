package personal.alexgarland.helloworldmvc.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import personal.alexgarland.helloworldmvc.model.Employee;
import personal.alexgarland.helloworldmvc.service.IEmployeeManager;

public class EmployeeAddControllerTest {

	private static final Employee employee = new Employee(1, "Robert", "Dobalina");

	@Mock
	private IEmployeeManager employeeManager;
	
    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void getsEmployeeForm() {
		
		EmployeeAddController controller = new EmployeeAddController();
		
		ModelAndView result = controller.getEmployeeForm();
		Map<String, Object> model = result.getModel();
		
		assertEquals("employeeAdd", result.getViewName());
		assertEquals(1, model.size());
		assert(model.get("employeeEntity") instanceof Employee);
		
	}
    
	@Test
	public void addsEmployee() {
		
		EmployeeAddController controller = new EmployeeAddController();
		controller.setEmployeeManager(employeeManager);
		
		ModelAndView result = controller.addEmployee(employee);
	
		verify(employeeManager, times(1)).addEmployee(employee);
		assertEquals("redirect:employees", result.getViewName());
		assertEquals(0, result.getModel().size());
		
	}

}
