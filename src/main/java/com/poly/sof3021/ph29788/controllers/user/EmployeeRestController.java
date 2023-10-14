package com.poly.sof3021.ph29788.controllers.user;

import com.poly.sof3021.ph29788.dto.request.user.EmployeeRequestDTO;
import com.poly.sof3021.ph29788.dto.response.user.EmployeeResponseDTO;
import com.poly.sof3021.ph29788.services.user.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<EmployeeResponseDTO>> getAllEmployees(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Page<EmployeeResponseDTO> employees = employeeService.getAll(page, size, sortField, sortOrder);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> findEmployeeById(@PathVariable("id") Long id) {
        EmployeeResponseDTO employee = employeeService.getById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> saveEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO responseDTO = employeeService.save(employeeRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO, @PathVariable("id") Long id) {
        EmployeeResponseDTO responseDTO = employeeService.update(employeeRequestDTO, id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.delete(id);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }
}
