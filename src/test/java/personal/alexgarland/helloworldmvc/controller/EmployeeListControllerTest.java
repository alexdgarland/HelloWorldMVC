package personal.alexgarland.helloworldmvc.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import personal.alexgarland.helloworldmvc.model.Employee;
import personal.alexgarland.helloworldmvc.service.IEmployeeManager;

public class EmployeeListControllerTest {

	private static List<Employee> employeeList;

	@Mock
	private IEmployeeManager employeeManager;
	
    @Before
    public void initMocks(){
    	
        MockitoAnnotations.initMocks(this);
        
        employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(1, "Robert", "Dobalina"));
        employeeList.add(new Employee(2, "Teren", "Jones"));
        
        when(employeeManager.getEmployeeList()).thenReturn(employeeList);
    
    }
	
	@Test
	public void setsUpModelAndView() throws Exception{
		
		EmployeeListController controller = new EmployeeListController();
		controller.setEmployeeManager(employeeManager);
		
		ModelAndView result = controller.handleRequest();
		Map<String, Object> model = result.getModel();
		
		assertEquals("employeeList", result.getViewName());
		
		assertEquals(1, model.size());
		assertEquals(model.get("employeeList"), employeeList);
		
	}

}
