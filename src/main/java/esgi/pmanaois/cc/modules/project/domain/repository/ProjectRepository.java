package fr.al_cc2.domain.repository;

import fr.al_cc2.domain.model.Project;
import fr.al_cc2.domain.model.ProjectId;
import fr.al_cc2.kernel.Repository;

import java.util.List;

public interface ProjectRepository extends Repository<ProjectId, Project> {
    List<Project> findAll();
}
