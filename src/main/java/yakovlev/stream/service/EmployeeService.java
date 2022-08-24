package yakovlev.stream.service;

import yakovlev.stream.model.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {
    Employee add(String firstName, String lastName, int department, double salary);

    Employee remove(String firstName, String lastName, int department, double salary);

    Employee find(String firstName, String lastName, int department, double salary);

    Collection<Employee> findAll();

    Employee maxSalaryInDept(int department);

    Employee minSalaryInDept(int departmentId);

    List<String> allEmployeesInDept(int departmentId);

    List<String> allEmployeesSortByDept();
}
