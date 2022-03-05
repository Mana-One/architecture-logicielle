package fr.al_cc2.web.project;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OwnerRequest {
    @NotNull
    @NotBlank
    public String name;
}
