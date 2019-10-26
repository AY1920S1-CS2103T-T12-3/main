package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.history.HistoryManager;

/**
 * Redo the previous undone Command.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    private static final String MESSAGE_SUCCESS = "Redo Command Success";
    private static final String MESSAGE_FAILURE = "Redo Command Failure: You have not "
        + "undone any commands. As such, you are unable to redo any commands.";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        HistoryManager history = new HistoryManager();
        if (history.isRedoneEmpty()) {
            return new CommandResult(MESSAGE_FAILURE);
        }
        model.redo();
        return new CommandResult(MESSAGE_SUCCESS);
    }
    @Override
    public boolean isUndoable() {
        return false;
    }
}
