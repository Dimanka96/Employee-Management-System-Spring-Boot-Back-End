package com.myproject.ems_spring_backend.service.impl;

import com.myproject.ems_spring_backend.dto.EmployeeDTO;
import com.myproject.ems_spring_backend.entity.Employee;
import com.myproject.ems_spring_backend.exceptions.ResourceNotFoundException;
import com.myproject.ems_spring_backend.mapper.EmployeeMapper;
import com.myproject.ems_spring_backend.repository.EmployeeRepo;
import com.myproject.ems_spring_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepo employeeRepo;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDTO);
        Employee savedEmployee = employeeRepo.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        Optional<Employee> employee= employeeRepo.findById(employeeId);
              if(employee.isEmpty()){
                  throw new RejectedExecutionException("Employee is not exist with given id : "+employeeId);
              }
        return EmployeeMapper.mapToEmployeeDto(employee.get());
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream().map(employee -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO updatedEmployee) {
        Optional<Employee> availability = employeeRepo.findById(employeeId);
        if(availability.isEmpty()){
            throw new ResourceNotFoundException("Employee is not exist with given id "+employeeId);
        }
        Employee employee = EmployeeMapper.mapToEmployee(updatedEmployee);
        employee.setId(employeeId);
        Employee updatedEntity = employeeRepo.save(employee);
       // Employee employee = employeeRepo.save(EmployeeMapper.mapToEmployee(updatedEmployee));
        return EmployeeMapper.mapToEmployeeDto(updatedEntity);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Optional <Employee> availability = employeeRepo.findById(employeeId);
        if(availability.isEmpty()){
            throw new ResourceNotFoundException("Employee is not exist with given id "+employeeId);
        }
        employeeRepo.deleteById(availability.get().getId());
    }
}
