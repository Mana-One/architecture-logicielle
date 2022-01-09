package esgi.pmanaois.cc.modules.membership;

import esgi.pmanaois.cc.modules.common.PaymentMethodId;
import esgi.pmanaois.cc.modules.membership.domain.*;
import org.junit.Test;
import org.junit.Assert;

public class UserTest {
    @Test
    public void creation_should_generate_UserInitializedEvent() {
        Name name = Name.of("Paolo", "Manaois");
        PaymentMethodId paymentMethodId = PaymentMethodId.of("some id");
        User user = User.create(name, "username@host.com", Role.CONTRACTOR, paymentMethodId);

        Assert.assertTrue(user.getEvents().size() == 1);
        Assert.assertTrue(user.getEvents().get(0) instanceof UserInitialized);
    }

    @Test
    public void verification_should_generate_UserVerifiedEvent() {
        Name name = Name.of("Paolo", "Manaois");
        PaymentMethodId paymentMethodId = PaymentMethodId.of("some id");
        User user = User.create(name, "username@host.com", Role.CONTRACTOR, paymentMethodId);

        user.verify();

        Assert.assertTrue(user.getEvents().size() == 2);
        Assert.assertTrue(user.getEvents().get(1) instanceof UserVerified);
    }

    @Test
    public void replay_should_map_the_correct_attributes() {
        Name name = Name.of("Paolo", "Manaois");
        String email = "username@host.com";
        PaymentMethodId paymentMethodId = PaymentMethodId.of("some id");
        User user = User.create(name, email, Role.CONTRACTOR, paymentMethodId);
        user.verify();

        user.replay(user.getEvents());

        Assert.assertEquals(name, user.getName());
        Assert.assertEquals(email, user.getEmail());
        Assert.assertEquals(paymentMethodId, user.getPaymentMethodId());
        Assert.assertEquals(Role.CONTRACTOR, user.getRole());
        Assert.assertTrue(user.isVerified());
    }
}
