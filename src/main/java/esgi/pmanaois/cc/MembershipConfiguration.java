package esgi.pmanaois.cc;

import esgi.pmanaois.cc.modules.membership.application.RegisterUserHandler;
import esgi.pmanaois.cc.modules.membership.domain.EmailValidationEngine;
import esgi.pmanaois.cc.modules.membership.domain.Users;
import esgi.pmanaois.cc.modules.membership.infrastructure.DefaultUsers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MembershipConfiguration {
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
        return new RegisterUserHandler(users(), emailValidationEngine());
    }
}
