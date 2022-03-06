package esgi.pmanaois.cc.modules.project.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ProjectRequest {
    @NotNull
    @NotBlank
    public String name;

    @NotNull
    @NotBlank
    public String owner;

    @NotNull
    @NotBlank
    public String status;

    @NotNull
    public LocalDate startDate;

    public LocalDate endDate;

}
