package esgi.pmanaois.cc.modules.membership.application;

import esgi.pmanaois.cc.kernel.Command;

final public class RegisterUser implements Command {
    final public String firstName;
    final public String lastName;
    final public String email;
    final public String role;
    final public String paymentMethodId;

    public RegisterUser(String firstName, String lastName, String email, String role, String paymentMethodId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.paymentMethodId = paymentMethodId;
    }

}
