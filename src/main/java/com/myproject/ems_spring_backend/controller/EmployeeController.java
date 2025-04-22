package com.myproject.ems_spring_backend.controller;

import com.myproject.ems_spring_backend.dto.EmployeeDTO;
import com.myproject.ems_spring_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO savedEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long employeeId){
        //EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
        //return ResponseEntity.ok(employeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeById(employeeId));
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDTO> updateEmployees(@PathVariable("id") Long employeeId,@RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.updateEmployee(employeeId,employeeDTO));
    }
    @DeleteMapping("{id}")
    public ResponseEntity <String> deleteEmployees(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee Deleted Successfully...!");
    }
}
