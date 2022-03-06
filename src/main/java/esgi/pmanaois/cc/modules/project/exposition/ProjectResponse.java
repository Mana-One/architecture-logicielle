package esgi.pmanaois.cc.modules.project.exposition;

import java.time.ZonedDateTime;
import java.util.List;

public class ProjectResponse {

    public final String id;
    public final String name;
    public final String owner;
    public final String status;
    public final List<String> requiredSkills;
    public final List<String> workers;
    public final ZonedDateTime startDate;
    public final ZonedDateTime endDate;

    public ProjectResponse(String id, String name, String owner, String status, List<String> requiredSkills, List<String> workers, ZonedDateTime startDate, ZonedDateTime endDate) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.status = status;
        this.requiredSkills = requiredSkills;
        this.workers = workers;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
