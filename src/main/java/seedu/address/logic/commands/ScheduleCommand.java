package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_NON_CALLED;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";
    public static final String MESSAGE_SUCCESS = "Refreshed schedule for the day.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetCall();
//        model.updateFilteredPersonList(PREDICATE_SHOW_NON_CALLED);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
