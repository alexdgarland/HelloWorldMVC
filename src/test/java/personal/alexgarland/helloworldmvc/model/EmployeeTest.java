package personal.alexgarland.helloworldmvc.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EmployeeTest {

	@Test
	public void returnsFullNameWithoutNickname() {
		Employee employee = new Employee(1, "Robert", "Dobalina");
		assertEquals("Robert Dobalina", employee.getFullName());
	}
	
	@Test
	public void returnsFullNameWithNickname() {
		Employee employee = new Employee(1, "Robert", "Dobalina", "Bob");
		assertEquals("Robert \"Bob\" Dobalina", employee.getFullName());
	}
	
}
