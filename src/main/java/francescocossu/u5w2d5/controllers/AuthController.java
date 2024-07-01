package francescocossu.u5w2d5.controllers;

import francescocossu.u5w2d5.entities.Employee;
import francescocossu.u5w2d5.exceptions.BadRequestException;
import francescocossu.u5w2d5.payloads.EmployeeDTO;
import francescocossu.u5w2d5.payloads.UserLoginDTO;
import francescocossu.u5w2d5.payloads.UserLoginResponseDTO;
import francescocossu.u5w2d5.services.AuthService;
import francescocossu.u5w2d5.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO payload) {
        return new UserLoginResponseDTO(authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    private Employee saveEmployee(@RequestBody @Validated EmployeeDTO employeePayload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new BadRequestException(validationResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(",")));
        }
        return employeeService.saveEmployee(employeePayload);
    }
}

