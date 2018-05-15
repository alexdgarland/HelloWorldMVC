package personal.alexgarland.helloworldmvc.service;

import java.util.List;
import personal.alexgarland.helloworldmvc.model.Employee;

public interface IEmployeeRepository {

  public List<Employee> getEmployeeList();

  public Employee addEmployee(Employee e);

  public void deleteEmployeeById(int employeeId);

  public void updateEmployee(Employee e);

  public Employee getEmployeeById(int employeeId);

}
