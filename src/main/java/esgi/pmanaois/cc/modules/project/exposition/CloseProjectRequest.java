package esgi.pmanaois.cc.modules.project.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public final class CloseProjectRequest {
    @NotNull
    @NotBlank
    public String projectId;
}