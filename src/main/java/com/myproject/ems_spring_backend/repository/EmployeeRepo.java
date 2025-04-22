package com.myproject.ems_spring_backend.repository;

import com.myproject.ems_spring_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {

}
