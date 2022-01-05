package esgi.pmanaois.cc.kernel;

import java.util.Map;

public class DefaultCommandBus<TCommand extends Command, R> implements CommandBus<TCommand, R> {
    private final Map<Class<? extends Command>, CommandHandler> dataMap;

    public DefaultCommandBus(Map<Class<? extends Command>, CommandHandler> dataMap) {
        this.dataMap = dataMap;
    }

    @Override
    public R send(TCommand command) {
        return dispatch(command);
    }    

    private <TCommand extends Command, R> R dispatch(TCommand command) {
        final CommandHandler commandHandler = dataMap.get(command.getClass());
        if (commandHandler == null) {
            throw new RuntimeException("No such command handler for " + command.getClass().getName());
        }

        return (R) commandHandler.handle(command);
    }
}
