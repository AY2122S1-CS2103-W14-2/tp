package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.ShnPeriod;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Contacts with completed SHN periods have been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // A dummy SHN period for contacts that do not have SHN periods
        // These contacts will be ignored as the endDate is always set to the future
        LocalDate futureDate = LocalDate.now().plusDays(1);
        ShnPeriod dummyIncompleteShnPeriod = new ShnPeriod(LocalDate.of(2000, 1, 1),
                futureDate);

        for (int i = 0; i < lastShownList.size(); i++) {
            Person person = lastShownList.get(i);
            if (person.getShnPeriod().orElse(dummyIncompleteShnPeriod).isCompletedBy(LocalDate.now())) {
                model.deletePerson(person);
            }
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
