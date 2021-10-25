package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_NON_CALLED;

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
public class IncrementCommand extends Command {

    public static final String COMMAND_WORD = "fcall";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Indicates that a person was non-responsive when an attempt to contact failed\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_INCREMENT_PERSON_SUCCESS = "Incremented Person: %1$s";

    private final Index targetIndex;

    public IncrementCommand(Index targetIndex) {
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
        Person newPerson = new Person(personToIncrement, personToIncrement.getCounter().increment());

        model.setPerson(personToIncrement, newPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_NON_CALLED);

        return new CommandResult(String.format(MESSAGE_INCREMENT_PERSON_SUCCESS, newPerson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof IncrementCommand // instanceof handles nulls
            && targetIndex.equals(((IncrementCommand) other).targetIndex)); // state check
    }
}
