package personal.alexgarland.helloworldmvc.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;


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
	
	@Test
	public void returnsToString() {
		Employee employee = new Employee(1, "Robert", "Dobalina", "Bob");
		assertEquals("Employee 1: Robert \"Bob\" Dobalina", employee.toString());
	}
		
	@Test
	public void sortsAsExpected() {
		Employee employee1 = new Employee(1, "John", "Kennedy", "Jack");
		Employee employee2 = new Employee(2, "Robert", "Dobalina");
		Employee employee3 = new Employee(3, "James", "Fallon", "Jimmy");
		List<Employee> list = new ArrayList<Employee>();
		list.add(employee2);
		list.add(employee3);
		list.add(employee1);
		
		Collections.sort(list);
		
		assertTrue(list.get(0) == employee1);
		assertTrue(list.get(1) == employee2);
		assertTrue(list.get(2) == employee3);
	}
	
	@Test
	public void idAccessorsWork() {
		Employee employee = new Employee(1, "Robert", "Dobalina");
		employee.setId(2);
		assertEquals(2, employee.getId());
	}
	
	@Test
	public void firstNameAccessorsWork() {
		Employee employee = new Employee(1, "Bruce", "Jenner");
		employee.setFirstName("Caitlyn");
		assertEquals("Caitlyn", employee.getFirstName());
	}
	
	@Test
	public void lastNameAccessorsWork() {
		Employee employee = new Employee(1, "Kim", "Kardashian");
		employee.setLastName("Kardashian West");
		assertEquals("Kardashian West", employee.getLastName());
	}

	@Test
	public void nickNameAccessorsWork() {
		Employee employee = new Employee(1, "Kanye", "West", "The Louis Vuitton Don");
		employee.setNickName("The Lyor Cohen of Dior Homme");
		assertEquals("The Lyor Cohen of Dior Homme", employee.getNickName());
	}
	
	@Test
	public void copiesWithNewId() {
		Employee employee = new Employee(1, "Robert", "Dobalina");
		Employee newEmployee = employee.copyWithNewId(2);
		assertEquals(new Employee(2, "Robert", "Dobalina"), newEmployee);
	}
	
}
