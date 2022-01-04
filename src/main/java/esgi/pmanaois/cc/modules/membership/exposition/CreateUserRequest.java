package esgi.pmanaois.cc.modules.membership.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateUserRequest {
    @NotNull
    @NotBlank
    public String firstName;

    @NotNull
    @NotBlank
    public String lastName;

    @NotNull
    @NotBlank
    public String email;

    @NotNull
    @NotBlank
    public String role;

    @NotNull
    @NotBlank
    public String paymentMethodId;
}
