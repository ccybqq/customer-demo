package pers.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.employee.Employee;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project_details")
public class Project {
    @Id
    @SequenceGenerator(name = "project_details_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_details_sequence")
    private int id;
    private String name;

    @ManyToMany
    private Set<Employee> employees;
}
