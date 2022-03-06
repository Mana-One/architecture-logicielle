package esgi.pmanaois.cc.modules.project.infrastructure;

import esgi.pmanaois.cc.modules.project.domain.WorkerMatcher;
import esgi.pmanaois.cc.modules.project.domain.model.Project;
import esgi.pmanaois.cc.modules.project.domain.model.Worker;

public class SimpleWorkerMatcher implements WorkerMatcher {
    @Override
    public boolean isSuitableForProject(Worker worker, Project project) {
        return true;
    }
}
