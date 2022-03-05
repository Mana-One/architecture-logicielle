package esgi.pmanaois.cc.modules.project.application.create;

import esgi.pmanaois.cc.kernel.Command;
import esgi.pmanaois.cc.modules.project.domain.model.Owner;

import java.time.LocalDate;

public final class CreateProject implements Command {

    public final String name;
    public final Owner owner;
    public final String status;
    public final LocalDate startDate;
    public LocalDate endDate;

    public CreateProject(String name, Owner owner, String status, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.owner = owner;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
