package esgi.pmanaois.cc.kernel;

import java.util.Map;

public class DefaultCommandBus implements CommandBus {
    private final Map<Class<? extends Command>, CommandHandler> dataMap;

    public DefaultCommandBus(Map<Class<? extends Command>, CommandHandler> dataMap) {
        this.dataMap = dataMap;
    }

    @Override
    public <TCommand extends Command, R> R send(TCommand command) {
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
