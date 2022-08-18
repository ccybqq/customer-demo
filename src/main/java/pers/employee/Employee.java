package pers.employee;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.project.Project;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee_details")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee {
    @Id
    @SequenceGenerator(name = "employee_details_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_details_sequence")
    private int id;
    private String name;

    @ManyToMany(mappedBy = "employees")
    private Set<Project> projects = new HashSet<>();

    @Override
    public int hashCode() {
        return (id + name).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) return false;
        Employee e = (Employee) o;
        return (e.getId() + e.getName()).equals(this.getId() + this.getName());
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("%n%d%n%s%n", id, name));
        projects.forEach(p -> sb.append(String.format("%s%n", p.getName())));
        return sb.toString();
    }
}