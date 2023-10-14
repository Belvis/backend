package com.poly.sof3021.ph29788.repositories.user;

import com.poly.sof3021.ph29788.entities.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
