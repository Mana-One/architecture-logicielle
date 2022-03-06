package esgi.pmanaois.cc.modules.project.domain;

import esgi.pmanaois.cc.modules.project.domain.model.ProjectId;

final public class NoSuchProject extends RuntimeException {
    private NoSuchProject(String message) {
        super(message);
    }

    public static NoSuchProject withId(ProjectId id) {
        return new NoSuchProject("No project with id '" + id.getValue().toString() + "'");
    }
}