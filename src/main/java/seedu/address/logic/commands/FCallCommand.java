package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Indicates that a failed call was made to a person. This records that person has been called and increases recorded
 * number of failed attempts.
 */
public class FCallCommand extends Command {

    public static final String COMMAND_WORD = "fcall";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Indicates that a person was non-responsive when an attempt to contact failed\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_INCREMENT_PERSON_SUCCESS =
        "Failed to call Person: %s; non-compliance counter: %d";

    private final Index targetIndex;

    /**
     * Default constructor to create a new {@code FCallCommand}
     *
     * @param targetIndex index of target person.
     */
    public FCallCommand(Index targetIndex) {
        requireNonNull(targetIndex);
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
        Person newPerson = new Person(personToIncrement, personToIncrement.getCallStatus().incrementNumFailedCalls());
        model.setPerson(personToIncrement, newPerson);

        return new CommandResult(String.format(MESSAGE_INCREMENT_PERSON_SUCCESS, newPerson.getCaseNumber(),
            newPerson.getCallStatus().getNumFailedCalls()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FCallCommand // instanceof handles nulls
            && targetIndex.equals(((FCallCommand) other).targetIndex)); // state check
    }
}
