package esgi.kernel;

@FunctionalInterface
public interface CommandHandler<TCommand extends Command, R> {
    R handle(TCommand command);
}