package personal.alexgarland.helloworldmvc.service;

import java.util.ArrayList;
import java.util.List;

import personal.alexgarland.helloworldmvc.model.Employee;

public final class ArrayListEmployeeManager implements IEmployeeManager {
	
	private static final List<Employee> employeeList;
 
	static {
		employeeList = new ArrayList<Employee>();
		employeeList.add(new Employee(1, "Michael", "Smith", "Mikey"));
		employeeList.add(new Employee(2, "Jonathan", "Taylor", "Johnny"));
		employeeList.add(new Employee(3, "David", "Wilson", "Dave"));
	}
 
	public List<Employee> getEmployeeList(){
		return employeeList;
	}
	
	public Employee addEmployee(Employee e) {
		Employee employee = e.copyWithNewId(employeeList.size() + 1);
		employeeList.add(employee);
		return employee;
	}
	
}
