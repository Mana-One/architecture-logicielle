package esgi.pmanaois.cc.modules.project.exposition;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProjectRequest {
    @NotNull
    @NotBlank
    public String name;

    @NotNull
    @NotBlank
    public String owner;

    @NotNull
    public List<String> requiredSkills;
}
