package pers.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class ProjectService {
    private static final int CONFLICT_STATUS_CODE = 409;
    private static final int TEAPOT_STATUS_CODE = 418;

    @Autowired
    IProjectRepo projectRepo;

    // Adds employee if employee is not already in the database.
    ResponseEntity<Project> add(Project project) {
        if (find(project.getId()).getStatusCodeValue() == TEAPOT_STATUS_CODE)
            return ResponseEntity.ok(projectRepo.save(project));
        return ResponseEntity.status(CONFLICT_STATUS_CODE).build();
    }

    // Returns a ResponseEntity with the Employee in the body. TEAPOT if Employee cannot be found.
    ResponseEntity<Project> find(int id) {
        return projectRepo.findById(id)
                .map(e -> ResponseEntity.ok(e))
                .orElseGet(() -> ResponseEntity.status(TEAPOT_STATUS_CODE).build());
    }

    // Replaces Employee with the same id if it already exists in the database. Creates a new entry otherwise.
    ResponseEntity<Project> update(Project project) {
        return ResponseEntity.ok(projectRepo.save(project));
    }

    // Deletes Employee from database if it exists, leaves the database unchanged otherwise.
    ResponseEntity<Project> delete(Project project) {
        projectRepo.delete(project);
        return ResponseEntity.ok().build();
    }
}
