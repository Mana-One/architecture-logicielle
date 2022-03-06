package esgi.pmanaois.cc.modules.project.application.list;

import java.util.List;
import java.util.Objects;

import esgi.pmanaois.cc.kernel.QueryHandler;
import esgi.pmanaois.cc.modules.project.domain.Projects;
import esgi.pmanaois.cc.modules.project.domain.model.Project;

public class ListProjectsHandler implements QueryHandler<ListProjects, List<Project>> {
    final private Projects projects;
    
    public ListProjectsHandler(Projects projects) {
        this.projects = Objects.requireNonNull(projects);
    }

    @Override
    public List<Project> handle(ListProjects query) {
        return projects.list();
    }
}
