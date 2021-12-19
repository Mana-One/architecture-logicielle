package esgi.kernel;

@FunctionalInterface
public interface CommandBus {
    <TCommand extends Command, R> R send(TCommand command);
}