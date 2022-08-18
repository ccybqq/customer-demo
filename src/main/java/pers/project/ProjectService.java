package pers.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private static final int CONFLICT_STATUS_CODE = 409;
    private static final int TEAPOT_STATUS_CODE = 418;

    @Autowired
    IProjectRepo projectRepo;

    // Adds project if project is not already in the database.
    public ResponseEntity<Project> add(Project project) {
        if (get(project.getId()).getStatusCodeValue() == TEAPOT_STATUS_CODE)
            return ResponseEntity.ok(projectRepo.save(project));
        return ResponseEntity.status(CONFLICT_STATUS_CODE).build();
    }

    // Returns a ResponseEntity with the Project in the body. TEAPOT if Project cannot be found.
    public ResponseEntity<Project> get(int id) {
        return projectRepo.findById(id)
                .map(e -> ResponseEntity.ok(e))
                .orElseGet(() -> ResponseEntity.status(TEAPOT_STATUS_CODE).build());
    }

    // Replaces Project with the same id if it already exists in the database. Creates a new entry otherwise.
    public ResponseEntity<Project> update(Project project) {
        return ResponseEntity.ok(projectRepo.save(project));
    }

    // Deletes Project from database if it exists, leaves the database unchanged otherwise.
    public ResponseEntity<Project> delete(int id) {
        projectRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
