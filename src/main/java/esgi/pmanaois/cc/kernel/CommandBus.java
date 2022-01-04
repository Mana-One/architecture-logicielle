package esgi.pmanaois.cc.kernel;

@FunctionalInterface
public interface CommandBus {
    <TCommand extends Command, R> R send(TCommand command);
}