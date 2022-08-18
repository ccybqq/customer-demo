package pers.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.common.IController;
import pers.employee.Employee;
import pers.employee.EmployeeService;

import java.util.HashSet;
import java.util.Set;

@RestController
public class ProjectController implements IController<Project> {
    @Autowired
    ProjectService projectService;
    @Autowired
    EmployeeService employeeService;

    @Override
    @PostMapping("/project/add")
    public ResponseEntity<Project> add(@RequestBody Project project) {
        // Add employees to database if they do not already exist. Ignore otherwise.
        project.getEmployees()
                .forEach(employeeService::add);

        // Generate a new set of employees using data from the database. (Maintains list of projects already assigned to each project.)
        Set<Employee> newEmployees = new HashSet<>();
        project.getEmployees()
                .stream()
                .map(e -> e.getId())
                .map(employeeService::get)
                .map(ResponseEntity::getBody)
                .forEach(newEmployees::add);

        // Add this project to set.
        newEmployees.forEach(e -> e.getProjects().add(project));
        project.setEmployees(newEmployees);
        return projectService.add(project);
    }

    @Override
    @PostMapping("/project/get")
    public String get(@RequestParam("id") int id) {
        return projectService.get(id).toString();
    }

    @Override
    @PostMapping("/project/update")
    public ResponseEntity<Project> update(@RequestBody Project project) {
        return projectService.update(project);
    }

    @Override
    @PostMapping("/project/delete")
    public ResponseEntity<Project> delete(@RequestParam("id") int id) {
        return projectService.delete(id);
    }
}
