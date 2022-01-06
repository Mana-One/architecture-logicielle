package esgi.pmanaois.cc.kernel;

public interface CommandBus<TCommand extends Command, R> {
     void addHandler(Class<TCommand> commandC, CommandHandler handler);
     R send(TCommand command);
}