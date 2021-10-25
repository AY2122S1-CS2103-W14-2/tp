package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_NON_CALLED;

import seedu.address.model.Model;

/**
 * Retrieves last-created schedule session and presents the session view to the user.
 */
public class SessionCommand extends Command {

    public static final String COMMAND_WORD = "session";

    public static final String MESSAGE_SUCCESS = "Retrieved last session";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_NON_CALLED);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
