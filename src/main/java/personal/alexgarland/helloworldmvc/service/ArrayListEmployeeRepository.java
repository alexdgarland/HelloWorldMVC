package personal.alexgarland.helloworldmvc.service;

import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import personal.alexgarland.helloworldmvc.model.Employee;

public final class ArrayListEmployeeRepository implements IEmployeeRepository {

  private static List<Employee> employeeList;

  private static final IdSequence sequence;

  static {
    employeeList = new ArrayList<Employee>();
    employeeList.add(new Employee(1, "Michael", "Smith", "Mikey"));
    employeeList.add(new Employee(2, "Jonathan", "Taylor", "Johnny"));
    employeeList.add(new Employee(3, "David", "Wilson", "Dave"));
    sequence = new IdSequence(4);
  }

  @Override
  public List<Employee> getEmployeeList() {
    return employeeList;
  }

  @Override
  public Employee addEmployee(Employee e) {
    Employee employee = e.copyWithNewId(sequence.getNextId());
    employeeList.add(employee);
    return employee;
  }

  private List<Employee> listExcludingEmployee(int employeeId) {
    return employeeList.stream().filter(e -> e.getId() != employeeId).collect(toList());
  }

  @Override
  public void deleteEmployeeById(int employeeId) {
    employeeList = listExcludingEmployee(employeeId);
  }

  @Override
  public void updateEmployee(Employee e) {
    List<Employee> newList = listExcludingEmployee(e.getId());
    newList.add(e);
    Collections.sort(newList);
    employeeList = newList;
  }

  @Override
  public Employee getEmployeeById(int employeeId) {
    return employeeList.stream().filter(e -> e.getId() == employeeId).findFirst().get();
  }

}
