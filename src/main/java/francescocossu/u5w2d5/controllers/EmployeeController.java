package francescocossu.u5w2d5.controllers;


import francescocossu.u5w2d5.entities.Employee;
import francescocossu.u5w2d5.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    private Page<Employee> getEmployees() {
        return employeeService.getAllEmployees(0, 10, "name");
    }

    @GetMapping("/{id}")
    private Employee getEmployeeById(UUID id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    private Employee updateEmployee(UUID id, Employee updatedEmployee) {
        return employeeService.getByEmployeeIdAndUpdate(id, updatedEmployee);
    }

    @DeleteMapping("/{id}")
    private void deleteEmployeeById(UUID id) {
        employeeService.deleteEmployeeById(id);
    }

}
