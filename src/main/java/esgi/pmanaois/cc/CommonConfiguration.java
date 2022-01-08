package esgi.pmanaois.cc;

import esgi.pmanaois.cc.modules.common.DefaultPaymentMethodIdValidationEngine;
import esgi.pmanaois.cc.modules.common.PaymentMethodIdValidationEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {
    @Bean
    public PaymentMethodIdValidationEngine paymentMethodIdValidationEngine() {
        return new DefaultPaymentMethodIdValidationEngine();
    }
}
