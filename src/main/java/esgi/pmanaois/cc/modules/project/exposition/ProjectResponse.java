package esgi.pmanaois.cc.modules.project.exposition;

import java.time.ZonedDateTime;
import java.util.List;

@SuppressWarnings("all")
public class ProjectResponse {

    public String id;
    public String name;
    public String owner;
    public String status;
    public List<String> requiredSkills;
    public ZonedDateTime startDate;
    public ZonedDateTime endDate;

    public ProjectResponse(String id, String name, String owner, String status,List<String> requiredSkills,  ZonedDateTime startDate, ZonedDateTime endDate) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.status = status;
        this.requiredSkills = requiredSkills;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ProjectResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
