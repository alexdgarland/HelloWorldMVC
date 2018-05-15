package personal.alexgarland.helloworldmvc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import personal.alexgarland.helloworldmvc.model.Employee;

public final class HashMapEmployeeRepository implements IEmployeeRepository {

  private static HashMap<Integer, Employee> employeeMap;

  private static void putEmployeeToMap(Employee e) {
    employeeMap.put(e.getId(), e);
  }

  private static final IdSequence sequence;

  static {
    employeeMap = new HashMap<Integer, Employee>();
    putEmployeeToMap(new Employee(1, "Michael", "Smith", "Mikey"));
    putEmployeeToMap(new Employee(2, "Jonathan", "Taylor", "Johnny"));
    putEmployeeToMap(new Employee(3, "David", "Wilson", "Dave"));
    sequence = new IdSequence(4);
  }

  @Override
  public List<Employee> getEmployeeList() {
    return new ArrayList<Employee>(employeeMap.values());
  }

  @Override
  public Employee addEmployee(Employee e) {
    Employee employee = e.copyWithNewId(sequence.getNextId());
    putEmployeeToMap(employee);
    return employee;
  }

  @Override
  public void deleteEmployeeById(int employeeId) {
    employeeMap.remove(employeeId);
  }

  @Override
  public void updateEmployee(Employee e) {
    putEmployeeToMap(e);
  }

  @Override
  public Employee getEmployeeById(int employeeId) {
    return employeeMap.get(employeeId);
  }

}
