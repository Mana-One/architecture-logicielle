package esgi.pmanaois.user.infrastructure;

import esgi.pmanaois.common.RegistrationFeePaidEvent;
import esgi.pmanaois.user.domain.User;
import esgi.pmanaois.user.domain.RegisterUserService;
import esgi.pmanaois.event.Subscriber;

public final class RegistrationFeePaidSubscriber implements Subscriber<RegistrationFeePaidEvent>
{
    private final RegisterUserService registerUserService;

    public RegistrationFeePaidSubscriber(RegisterUserService registerUserService)
    {
        this.registerUserService = registerUserService;
    }

    @Override
    public void on(RegistrationFeePaidEvent event) {
        System.out.println(event.getClass().getSimpleName() + " received: " + event);
        User user = User.fromCardOwnerDto(event.getPayload());
        this.registerUserService.register(user);
    }
}
