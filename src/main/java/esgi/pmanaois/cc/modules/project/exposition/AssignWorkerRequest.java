package esgi.pmanaois.cc.modules.project.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AssignWorkerRequest {
    @NotNull
    @NotBlank
    public String worker;
}
