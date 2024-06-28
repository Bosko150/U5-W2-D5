package francescocossu.u5w2d5.services;

import francescocossu.u5w2d5.entities.Employee;
import francescocossu.u5w2d5.exceptions.NotFoundException;
import francescocossu.u5w2d5.payloads.EmployeeDTO;
import francescocossu.u5w2d5.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;


    public Employee saveEmployee(EmployeeDTO employeePayload) {
        this.employeeRepository.findByEmail(employeePayload.email()).ifPresent(employee -> {
            throw new IllegalArgumentException("Email already in use");
        });

        Employee employee = new Employee(employeePayload.username(), employeePayload.name(), employeePayload.surname(),
                employeePayload.email());
        return employeeRepository.save(employee);
    }


    public Employee getEmployeeById(UUID id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Page<Employee> getAllEmployees(int pageNumbr, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumbr, pageSize, Sort.by(sortBy));

        return employeeRepository.findAll(pageable);
    }


    public void deleteEmployeeById(UUID id) {
        employeeRepository.deleteById(id);
    }

    public Employee getByEmployeeIdAndUpdate(UUID id, EmployeeDTO updatedEmployeePayload) {
        Employee updatedEmployee = new Employee(updatedEmployeePayload.username(), updatedEmployeePayload.name(), updatedEmployeePayload.surname(), updatedEmployeePayload.email());
        Employee foundEmployee = getEmployeeById(id);
        foundEmployee.setUsername(updatedEmployee.getUsername());
        foundEmployee.setName(updatedEmployee.getName());
        foundEmployee.setSurname(updatedEmployee.getSurname());
        foundEmployee.setEmail(updatedEmployee.getEmail());
        foundEmployee.setAvatarUrl(updatedEmployee.getAvatarUrl());
        return employeeRepository.save(foundEmployee);
    }


}
