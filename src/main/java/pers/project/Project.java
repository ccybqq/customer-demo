package pers.project;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.employee.Employee;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project_details")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Project {
    @Id
    @SequenceGenerator(name = "project_details_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_details_sequence")
    private int id;
    private String name;

    @ManyToMany
    private Set<Employee> employees = new HashSet<>();

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
        employees.forEach(e -> sb.append(String.format("%s%n", e.getName())));
        return sb.toString();
    }
}
