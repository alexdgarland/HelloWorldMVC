package personal.alexgarland.helloworldmvc.controller;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import personal.alexgarland.helloworldmvc.service.IEmployeeRepository;

public class EmployeeActionControllerTest {

	private static final Employee employee1 = new Employee(1, "Robert", "Dobalina");
	private static final Employee employee2 = new Employee(2, "Teren", "Jones");
	private static final List<Employee> employees = new ArrayList<Employee>(asList(employee1, employee2));
	
	private EmployeeActionController testController;
	
	@Mock
	private IEmployeeRepository employeeRepository;
	
    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
		when(employeeRepository.getEmployeeList()).thenReturn(employees);
		when(employeeRepository.getEmployeeById(1)).thenReturn(employee1);
		testController = new EmployeeActionController();
		testController.setEmployeeRepository(employeeRepository);
    }
	
	@Test
	public void getsAddEmployeeForm() {
		ModelAndView result = testController.getAddEmployeeForm();
		Map<String, Object> model = result.getModel();
		
		assertEquals("employeeAdd", result.getViewName());
		assertEquals(1, model.size());
		assert(model.get("employeeEntity") instanceof Employee);
	}
    
	@Test
	public void addsEmployee() {
		ModelAndView result = testController.addEmployee(employee1);
		verify(employeeRepository, times(1)).addEmployee(employee1);
		assertEquals("redirect:employees", result.getViewName());
		assertEquals(0, result.getModel().size());
	}
	
	@Test
	public void listsEmployees() {
		ModelAndView result = testController.getEmployeeList();
		assertEquals("employeeList", result.getViewName());
		
		Map<String, Object> model = result.getModel();
		assertEquals(1, model.size());
		assertEquals(employees, model.get("employeeList"));
	}
	
	@Test
	public void deletesEmployee() {
		testController.deleteEmployeeById(3);
		verify(employeeRepository, times(1)).deleteEmployeeById(3);
	}

	@Test
	public void getsUpdateEmployeeForm() {
		ModelAndView result = testController.getUpdateEmployeeForm(1);
		Map<String, Object> model = result.getModel();
		
		assertEquals("employeeUpdate", result.getViewName());
		assertEquals(1, model.size());
		assertEquals(employee1, model.get("employeeEntity"));
	}

	@Test
	public void updatesEmployee() {
		Employee employee1NewDetails = new Employee(1, "Robert", "Dobalina", "Bob");
		testController.updateEmployee(employee1NewDetails);
		verify(employeeRepository, times(1)).updateEmployee(employee1NewDetails);
	}
	
}
