package modules.membership.application;

import esgi.kernel.Command;

public class RegisterUser implements Command {
    public String firstName;
    public String lastName;
    public String email;
    public String role;
    public String paymentMethodId;
}
