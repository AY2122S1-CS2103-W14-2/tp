package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import static seedu.address.model.Model.PREDICATE_SHOW_NON_CALLED;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class CallCommand extends Command {

    public static final String COMMAND_WORD = "call";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Indicates that the person has been successfully called\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CALL_PERSON_SUCCESS = "Called Person: %1$s";

    private final Index targetIndex;

    public CallCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToIncrement = lastShownList.get(targetIndex.getZeroBased());
        model.callPerson(personToIncrement);
        model.updateFilteredPersonList(PREDICATE_SHOW_NON_CALLED);
        return new CommandResult(String.format(MESSAGE_CALL_PERSON_SUCCESS, personToIncrement));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CallCommand // instanceof handles nulls
            && targetIndex.equals(((CallCommand) other).targetIndex)); // state check
    }
}
