package personal.alexgarland.helloworldmvc.service;

import java.util.List;

import personal.alexgarland.helloworldmvc.model.Employee;

public interface IEmployeeManager {

	public List<Employee> getEmployeeList();
	
	public Employee addEmployee(Employee e) throws CloneNotSupportedException;
	
}
