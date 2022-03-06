package esgi.pmanaois.cc.modules.project.application.create;

import esgi.pmanaois.cc.kernel.Command;

import java.util.List;

public final class RegisterProject implements Command {

    public final String name;
    public final String owner;
    public final List<String> requiredSkills;

    public RegisterProject(String name, String owner, List<String> requiredSkills) {
        this.name = name;
        this.owner = owner;
        this.requiredSkills = requiredSkills;
    }
}
