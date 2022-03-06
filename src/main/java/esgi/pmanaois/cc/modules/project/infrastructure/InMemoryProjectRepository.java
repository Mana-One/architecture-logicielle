package esgi.pmanaois.cc.modules.project.infrastructure;


import esgi.pmanaois.cc.modules.project.domain.Projects;
import esgi.pmanaois.cc.modules.project.domain.model.Project;
import esgi.pmanaois.cc.modules.project.domain.model.ProjectId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryProjectRepository implements Projects {

    final private Map<ProjectId, Project> data = new ConcurrentHashMap<>();

    @Override
    public Optional<Project> findById(ProjectId id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void remove(ProjectId id) {
        this.data.remove(id);
    }

    @Override
    public void save(Project project) {
        this.data.put(project.getId(), project);
    }

    @Override
    public List<Project> list() {
        return new ArrayList<Project>(this.data.values());
    }
}
