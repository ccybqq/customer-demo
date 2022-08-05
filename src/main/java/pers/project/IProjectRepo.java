package pers.project;

import org.springframework.data.jpa.repository.JpaRepository;

interface IProjectRepo extends JpaRepository<Project, Integer> {
}
