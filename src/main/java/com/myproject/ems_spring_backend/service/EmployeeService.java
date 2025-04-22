package com.myproject.ems_spring_backend.service;

import com.myproject.ems_spring_backend.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee (EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeById (Long employeeId);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO updateEmployee (Long employeeId , EmployeeDTO updatedEmployee);

    void deleteEmployee (Long employeeId);
}
