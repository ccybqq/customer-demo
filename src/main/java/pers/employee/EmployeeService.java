package pers.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
class EmployeeService {
    private static final int CONFLICT_STATUS_CODE = 409;
    private static final int TEAPOT_STATUS_CODE = 418;

    @Autowired
    IEmployeeRepo employeeRepo;

    // Adds employee if employee is not already in the database.
    ResponseEntity<Employee> add(Employee employee) {
        if (find(employee.getId()).getStatusCodeValue() == TEAPOT_STATUS_CODE)
            return ResponseEntity.ok(employeeRepo.save(employee));
        return ResponseEntity.status(CONFLICT_STATUS_CODE).build();
    }

    // Returns a ResponseEntity with the Employee in the body. TEAPOT if Employee cannot be found.
    ResponseEntity<Employee> find(int id) {
        return employeeRepo.findById(id)
                .map(e -> ResponseEntity.ok(e))
                .orElseGet(() -> ResponseEntity.status(TEAPOT_STATUS_CODE).build());
    }

    // Replaces Employee with the same id if it already exists in the database. Creates a new entry otherwise.
    ResponseEntity<Employee> update(Employee employee) {
        return ResponseEntity.ok(employeeRepo.save(employee));
    }

    // Deletes Employee from database if it exists, leaves the database unchanged otherwise.
    ResponseEntity<Employee> delete(Employee employee) {
        employeeRepo.delete(employee);
        return ResponseEntity.ok().build();
    }
}
