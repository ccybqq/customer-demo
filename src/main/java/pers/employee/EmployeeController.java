package pers.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.common.IController;
import pers.project.Project;
import pers.project.ProjectService;

import java.util.HashSet;
import java.util.Set;

@RestController
public class EmployeeController implements IController<Employee> {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ProjectService projectService;

    @Override
    @PostMapping("/employee/add")
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
        // Add projects to database if they do not already exist. Ignore otherwise.
        employee.getProjects()
                .forEach(projectService::add);

        // Generate a new set of projects using data from the database. (Maintains list of employees already assigned to each project.)
        Set<Project> newProjects = new HashSet<>();
        employee.getProjects()
                .stream()
                .map(p -> p.getId())
                .map(projectService::get)
                .map(ResponseEntity::getBody)
                .forEach(newProjects::add);

        // Add this employee to set.
        newProjects.forEach(p -> p.getEmployees().add(employee));
        employee.setProjects(newProjects);
        return employeeService.add(employee);
    }

    @Override
    @PostMapping("/employee/get")
    public String get(@RequestParam("id") int id) {
        return employeeService.get(id).toString();
    }

    @Override
    @PostMapping("/employee/update")
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        delete(employee.getId());
        return add(employee);
    }

    @Override
    @PostMapping("/employee/delete")
    public ResponseEntity<Employee> delete(@RequestParam("id") int id) {
        ResponseEntity<Employee> response = employeeService.get(id);
        if (response.getStatusCode() == HttpStatus.OK) {
            Employee employee = response.getBody();
            Set<Project> projects = employee.getProjects();

            projects.forEach(p -> p.getEmployees().removeIf(employee::equals));
            projects.forEach(projectService::update);
        }
        return employeeService.delete(id);
    }
}
