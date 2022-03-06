package esgi.pmanaois.cc.kernel;

public interface CommandBus {
     <TCommand extends Command, R> void addHandler(Class<TCommand> commandC, CommandHandler<TCommand, R> handler);
     <TCommand extends Command, R> R send(TCommand command);
}