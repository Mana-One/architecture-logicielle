package esgi.pmanaois.cc;

import esgi.pmanaois.cc.kernel.CommandBus;
import esgi.pmanaois.cc.kernel.EventDispatcher;
import esgi.pmanaois.cc.modules.membership.application.RegisterUser;
import esgi.pmanaois.cc.modules.membership.application.RegisterUserHandler;
import esgi.pmanaois.cc.modules.membership.domain.EmailValidationEngine;
import esgi.pmanaois.cc.modules.membership.domain.Users;
import esgi.pmanaois.cc.modules.membership.infrastructure.InMemoryUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MembershipConfiguration {
    @Autowired KernelConfiguration kernelConfiguration;

    @Bean
    public Users users() {
        return new InMemoryUsers();
    }

    @Bean
    public EmailValidationEngine emailValidationEngine() {
        return new EmailValidationEngine();
    }

    @Bean
    public RegisterUserHandler registerUserHandler() {
        EventDispatcher dispatcher = kernelConfiguration.eventEventDispatcher();
        return new RegisterUserHandler(users(), emailValidationEngine(), dispatcher);
    }

    @Bean
    public CommandBus membershipCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(RegisterUser.class, registerUserHandler());
        return commandBus;
    }
}
