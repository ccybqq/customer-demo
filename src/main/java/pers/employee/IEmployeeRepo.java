package pers.employee;

import org.springframework.data.jpa.repository.JpaRepository;

interface IEmployeeRepo extends JpaRepository<Employee, Integer> {
}
