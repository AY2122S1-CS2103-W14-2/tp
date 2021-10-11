package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOME_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUARANTINE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHN_PERIOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WORK_ADDRESS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_CASE_NUMBER + "CASE NUMBER "
            + PREFIX_HOME_ADDRESS + "HOME ADDRESS "
            + "[" + PREFIX_WORK_ADDRESS + "WORK ADDRESS] "
            + "[" + PREFIX_QUARANTINE_ADDRESS + "QUARANTINE ADDRESS] "
            + "[" + PREFIX_SHN_PERIOD + "SHN PERIOD] "
            + "[" + PREFIX_NEXT_OF_KIN_NAME + "NEXT OF KIN NAME] "
            + "[" + PREFIX_NEXT_OF_KIN_PHONE + "NEXT OF KIN PHONE] "
            + "[" + PREFIX_NEXT_OF_KIN_ADDRESS + "NEXT OF KIN ADDRESS] "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_CASE_NUMBER + "123 "
            + PREFIX_HOME_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_SHN_PERIOD + "2021-10-23 2021-10-30"
            + PREFIX_NEXT_OF_KIN_NAME + "Bob Doe";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
