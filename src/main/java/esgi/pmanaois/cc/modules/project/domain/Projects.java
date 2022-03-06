package esgi.pmanaois.cc.modules.project.domain;

import java.util.List;

import esgi.pmanaois.cc.kernel.Repository;
import esgi.pmanaois.cc.modules.project.domain.model.Project;
import esgi.pmanaois.cc.modules.project.domain.model.ProjectId;

public interface Projects extends Repository<ProjectId, Project> {
    List<Project> list();
}
