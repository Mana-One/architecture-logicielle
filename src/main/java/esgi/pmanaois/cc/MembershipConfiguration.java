package esgi.pmanaois.cc;

import esgi.pmanaois.cc.kernel.CommandBus;
import esgi.pmanaois.cc.kernel.DefaultCommandBus;
import esgi.pmanaois.cc.kernel.DefaultEventDispatcher;
import esgi.pmanaois.cc.modules.membership.application.RegisterUser;
import esgi.pmanaois.cc.modules.membership.application.RegisterUserHandler;
import esgi.pmanaois.cc.modules.common.UserRegistered;
import esgi.pmanaois.cc.modules.membership.domain.EmailValidationEngine;
import esgi.pmanaois.cc.modules.membership.domain.Users;
import esgi.pmanaois.cc.modules.membership.infrastructure.DefaultUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MembershipConfiguration {
    @Autowired KernelConfiguration kernelConfiguration;

    @Bean
    public Users users() {
        return new DefaultUsers();
    }

    @Bean
    public EmailValidationEngine emailValidationEngine() {
        return new EmailValidationEngine();
    }

    @Bean
    public RegisterUserHandler registerUserHandler() {
        DefaultEventDispatcher dispatcher = (DefaultEventDispatcher) kernelConfiguration.eventEventDispatcher();
        dispatcher.registerEvent(UserRegistered.class);
        return new RegisterUserHandler(users(), emailValidationEngine(), dispatcher);
    }

    @Bean
    public CommandBus membershipCommandBus() {
        final DefaultCommandBus commandBus = (DefaultCommandBus) kernelConfiguration.commandBus();
        commandBus.addHandler(RegisterUser.class, registerUserHandler());
        return commandBus;
    }
}
