package esgi.pmanaois.user.domain;

public final class RegisterUserService
{
    private final Users users;

    public RegisterUserService(Users users)
    {
        this.users = users;
    }

    public void register(User user)
    {
        this.users.save(user);
    }
}
