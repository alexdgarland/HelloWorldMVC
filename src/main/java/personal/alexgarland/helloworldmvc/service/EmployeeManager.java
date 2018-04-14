package personal.alexgarland.helloworldmvc.service;

import java.util.ArrayList;
import java.util.List;
import personal.alexgarland.helloworldmvc.model.Employee;

public class EmployeeManager {
	
	private static List<Employee> employeeList;
 
	public EmployeeManager(){
		 employeeList = new ArrayList<Employee>();
		 employeeList.add(new Employee("1", "Michael", "Smith", "Mikey"));
		 employeeList.add(new Employee("2", "Jonathan", "Taylor", "Johnny"));
		 employeeList.add(new Employee("3", "David", "Wilson", "Dave"));	 
	}
 
	public List<Employee> getEmployeeList(){
		return employeeList;
	}

}
