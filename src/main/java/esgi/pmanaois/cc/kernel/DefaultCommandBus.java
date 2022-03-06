package esgi.pmanaois.cc.kernel;

import java.util.Map;

public class DefaultCommandBus implements CommandBus{
    private final Map<Class<? extends Command>, CommandHandler> handlers;

    public DefaultCommandBus(Map<Class<? extends Command>, CommandHandler> hendlers) {
        this.handlers = hendlers;
    }

    @Override
    public <TCommand extends Command, R> void addHandler(
        Class<TCommand> commandC,
         CommandHandler<TCommand, R> handler
    ) {
        this.handlers.putIfAbsent(commandC, handler);
    }

    @Override
    public <TCommand extends Command, R> R send(TCommand command) {
        return dispatch(command);
    }

    private <C extends Command, R> R dispatch(C command) {
        final CommandHandler commandHandler = handlers.get(command.getClass());
        if (commandHandler == null) {
            throw new RuntimeException("No such command handler for " + command.getClass().getName());
        }

        return (R) commandHandler.handle(command);
    }
}
