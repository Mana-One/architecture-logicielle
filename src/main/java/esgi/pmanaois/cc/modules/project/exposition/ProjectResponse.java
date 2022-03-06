package esgi.pmanaois.cc.modules.project.exposition;

import java.time.LocalDate;

@SuppressWarnings("all")
public class ProjectResponse {

    public String id;
    public String name;
    public String owner;
    public String status;
    public LocalDate startDate;
    public LocalDate endDate;

    public ProjectResponse(String id, String name, String owner, String status, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.status = status;
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
