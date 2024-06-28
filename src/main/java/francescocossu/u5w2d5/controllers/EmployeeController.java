package francescocossu.u5w2d5.controllers;


import francescocossu.u5w2d5.entities.Employee;
import francescocossu.u5w2d5.exceptions.BadRequestException;
import francescocossu.u5w2d5.payloads.EmployeeDTO;
import francescocossu.u5w2d5.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

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

    @PostMapping
    private Employee saveEmployee(@RequestBody @Validated EmployeeDTO employeePayload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new BadRequestException(validationResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(",")));
        }
        return employeeService.saveEmployee(employeePayload);
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable UUID id, @RequestBody @Validated EmployeeDTO employeePayload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new BadRequestException(validationResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", ")));
        }
        return employeeService.getByEmployeeIdAndUpdate(id, employeePayload);
    }

    @DeleteMapping("/{id}")
    private void deleteEmployeeById(UUID id) {
        employeeService.deleteEmployeeById(id);
    }

}
