package esgi.pmanaois.cc.modules.membership.exposition;

import javax.validation.constraints.NotNull;

public class CreateUserRequest {
    @NotNull
    public String firstName;

    @NotNull
    public String lastName;

    @NotNull
    public String email;

    @NotNull
    public String role;

    @NotNull
    public String paymentMethodId;
}
