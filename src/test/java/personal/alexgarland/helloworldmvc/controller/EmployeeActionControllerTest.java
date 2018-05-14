package personal.alexgarland.helloworldmvc.controller;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Level;
import personal.alexgarland.helloworldmvc.model.Employee;
import personal.alexgarland.helloworldmvc.service.IEmployeeRepository;
import personal.alexgarland.helloworldmvc.testutil.LogbackEventChecker;


public class EmployeeActionControllerTest {

	private static final Employee employee1 = new Employee(1, "Robert", "Dobalina");
	private static final Employee employee2 = new Employee(2, "Teren", "Jones");
	private static final List<Employee> employees = new ArrayList<Employee>(asList(employee1, employee2));
	
	private EmployeeActionController testController;
    
	@Mock
	private IEmployeeRepository employeeRepository;
	
    @Before
    public void setUp(){
    	
        MockitoAnnotations.initMocks(this);
		when(employeeRepository.getEmployeeList()).thenReturn(employees);
		when(employeeRepository.getEmployeeById(1)).thenReturn(employee1);
		
		testController = new EmployeeActionController();
		testController.setEmployeeRepository(employeeRepository);
		
		LogbackEventChecker.setup();
    
    }
    	
    @After
    public void teardown() {
    
    	LogbackEventChecker.teardown();
    
    }
	 
	@Test
	public void listsEmployees() {
		ModelAndView result = testController.getEmployeeList();
		
		assertEquals("employeeList", result.getViewName());
		
		Map<String, Object> model = result.getModel();
		assertEquals(1, model.size());
		assertEquals(employees, model.get("employeeList"));
		
		new LogbackEventChecker()
		.withExpectedEvent(Level.INFO, "Listing 2 employees...")
		.withExpectedEvent(Level.DEBUG, employee1.toString())
		.withExpectedEvent(Level.DEBUG, employee2.toString())		
		.verifyCalls();

	}
    
	@Test
	public void getsAddEmployeeForm() {
		ModelAndView result = testController.getAddEmployeeForm();
		Map<String, Object> model = result.getModel();
		
		assertEquals("employeeAdd", result.getViewName());
		assertEquals(1, model.size());
		assert(model.get("employeeEntity") instanceof Employee);
		
		new LogbackEventChecker()
		.withExpectedEvent(Level.INFO, "Setting up \"Add Employee\" form")
		.verifyCalls();
		
	}
    
	@Test
	public void addsEmployee() {
		ModelAndView result = testController.addEmployee(employee1);
		verify(employeeRepository, times(1)).addEmployee(employee1);
		assertEquals("redirect:employees", result.getViewName());
		assertEquals(0, result.getModel().size());
		
		new LogbackEventChecker()
		.withExpectedEvent(Level.INFO, "Adding employee:")
		.withExpectedEvent(Level.INFO, employee1.toString())
		.verifyCalls();
		
	}
	
	@Test
	public void deletesEmployee() {
		testController.deleteEmployeeById(3);
		verify(employeeRepository, times(1)).deleteEmployeeById(3);
		
		new LogbackEventChecker()
		.withExpectedEvent(Level.WARN, "Deleting employee with ID 3")
		.verifyCalls();
		
	}

	@Test
	public void getsUpdateEmployeeForm() {
		ModelAndView result = testController.getUpdateEmployeeForm(1);
		Map<String, Object> model = result.getModel();
		
		assertEquals("employeeUpdate", result.getViewName());
		assertEquals(1, model.size());
		assertEquals(employee1, model.get("employeeEntity"));
		
		new LogbackEventChecker()
		.withExpectedEvent(Level.INFO, "Setting up Employee Update form")
		.verifyCalls();
		
	}

	@Test
	public void updatesEmployee() {
		Employee employee1NewDetails = new Employee(1, "Robert", "Dobalina", "Bob");
		testController.updateEmployee(employee1NewDetails);
		verify(employeeRepository, times(1)).updateEmployee(employee1NewDetails);
		
		new LogbackEventChecker()
		.withExpectedEvent(Level.INFO, "Updating employee with details:")
		.withExpectedEvent(Level.INFO, employee1NewDetails.toString())
		.verifyCalls();
		
	}
	
}
