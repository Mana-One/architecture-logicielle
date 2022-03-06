package esgi.pmanaois.cc.modules.project.domain.repository;

import esgi.pmanaois.cc.kernel.Repository;
import esgi.pmanaois.cc.modules.project.domain.model.Project;
import esgi.pmanaois.cc.modules.project.domain.model.ProjectId;

import java.util.List;

public interface ProjectRepository extends Repository<ProjectId, Project> {
    List<Project> findAll();
}
