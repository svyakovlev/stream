package yakovlev.stream.service;

import org.springframework.stereotype.Service;
import yakovlev.stream.model.Employee;
import yakovlev.stream.exception.EmployeeAlreadyAddedException;
import yakovlev.stream.exception.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employeeList;

    public EmployeeServiceImpl() {
        this.employeeList = new ArrayList<>();
    }

    @Override
    public Employee add(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employeeList.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employeeList.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableList(employeeList);
    }

    @Override
    public Employee maxSalaryInDept(int departmentId) {
        return employeeList.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .max(Comparator.comparing(Employee::getSalary))
                .get();
    }

    @Override
    public Employee minSalaryInDept(int departmentId) {
        return employeeList.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .get();
    }

    @Override
    public List<String> allEmployeesInDept(int departmentId) {
        return employeeList.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .map(e -> e.getFirstName() + " " + e.getLastName())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> allEmployeesSortByDept() {
        return Collections.unmodifiableList(employeeList).stream()
                .sorted(Comparator.comparingInt(Employee::getDepartment))
                .map(e -> e.getFirstName() + " " + e.getLastName())
                .collect(Collectors.toList());
    }
}
