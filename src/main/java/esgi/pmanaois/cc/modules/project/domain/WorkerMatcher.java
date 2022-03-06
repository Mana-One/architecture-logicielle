package esgi.pmanaois.cc.modules.project.domain;

import esgi.pmanaois.cc.modules.project.domain.model.Project;
import esgi.pmanaois.cc.modules.project.domain.model.Worker;

@FunctionalInterface
public interface WorkerMatcher {
    boolean isSuitableForProject(Worker worker, Project project);
}
