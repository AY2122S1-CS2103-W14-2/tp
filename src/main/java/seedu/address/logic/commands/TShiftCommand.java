package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.ShnPeriod;

public class TShiftCommand extends Command {

    public static final String COMMAND_WORD = "tshift";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shifts all persons' SHN end date "
            + "by the specified number of DAYS.\n"
            + "Parameters: [PLUS_MINUS_SIGN]DAYS\n"
            + "Example: " + COMMAND_WORD + " 3";

    public static final String MESSAGE_SUCCESS = "All SHN end dates have been shifted accordingly.";
    public static final String MESSAGE_TSHIFT_BY_ZERO = "Number of days to shift the SHN end dates by should not be 0.";
    public static final String MESSAGE_BEYOND_LIMIT = "Magnitude of shift should not be larger than %d days.";
    public static final int MAX_ABS_DAYS_VALUE = 90;

    private final int days;

    /**
     * @param days to shift all persons' {@code ShnPeriod} end date by.
     */
    public TShiftCommand(int days) {
        this.days = days;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (days == 0) {
            throw new CommandException(MESSAGE_TSHIFT_BY_ZERO);
        }
        if (Math.abs(days) > MAX_ABS_DAYS_VALUE) {
            throw new CommandException(String.format(MESSAGE_BEYOND_LIMIT, MAX_ABS_DAYS_VALUE));
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        for (Person personToEdit : lastShownList) {
            EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
            personToEdit.getShnPeriod()
                    .map(this::shiftShnPeriodEndDate)
                    .ifPresent(editPersonDescriptor::setShnPeriod);
            Person editedPerson = EditCommand.createEditedPerson(personToEdit, editPersonDescriptor);
            model.setPerson(personToEdit, editedPerson);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Shifts the end date of the specified {@code ShnPeriod} by {@code days}.
     * @param shnPeriod that will have its end date shifted.
     * @return A new {@code ShnPeriod} object with its end date shifted.
     */
    public ShnPeriod shiftShnPeriodEndDate(ShnPeriod shnPeriod) {
        LocalDate startDate = shnPeriod.startDate;
        LocalDate endDate = shnPeriod.endDate;

        assert days != 0;

        LocalDate newEndDate = Collections.max(List.of(endDate.plusDays(days), startDate.plusDays(1)));

        return new ShnPeriod(startDate, newEndDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TShiftCommand // instanceof handles nulls
                && days == (((TShiftCommand) other).days));
    }
}
