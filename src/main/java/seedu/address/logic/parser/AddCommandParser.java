package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WORK_ADDRESS;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.CaseNumber;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.ShnPeriod;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_CASE_NUMBER, PREFIX_HOME_ADDRESS, PREFIX_WORK_ADDRESS, PREFIX_QUARANTINE_ADDRESS,
                PREFIX_SHN_PERIOD, PREFIX_NEXT_OF_KIN_NAME, PREFIX_NEXT_OF_KIN_PHONE, PREFIX_NEXT_OF_KIN_ADDRESS,
                PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_CASE_NUMBER,
                PREFIX_HOME_ADDRESS) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        CaseNumber caseNumber = ParserUtil.parseCaseNumber(argMultimap.getValue(PREFIX_CASE_NUMBER).get());
        Address homeAddress = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_HOME_ADDRESS).get());

        Optional<String> workAddressOptional = argMultimap.getValue(PREFIX_WORK_ADDRESS);
        Optional<Address> workAddress = workAddressOptional.isEmpty()
                ? Optional.empty()
                : Optional.of(ParserUtil.parseAddress(workAddressOptional.get()));

        Optional<String> quarantineAddressOptional = argMultimap.getValue(PREFIX_QUARANTINE_ADDRESS);
        Optional<Address> quarantineAddress = quarantineAddressOptional.isEmpty()
                ? Optional.empty()
                : Optional.of(ParserUtil.parseAddress(quarantineAddressOptional.get()));

        Optional<String> shnPeriodOptional = argMultimap.getValue(PREFIX_SHN_PERIOD);
        Optional<ShnPeriod> shnPeriod = shnPeriodOptional.isEmpty()
                ? Optional.empty()
                : Optional.of(ParserUtil.parseShnPeriod(shnPeriodOptional.get()));

        Optional<String> nextOfKinNameOptional = argMultimap.getValue(PREFIX_NEXT_OF_KIN_NAME);
        Optional<Name> nextOfKinName = nextOfKinNameOptional.isEmpty()
                ? Optional.empty()
                : Optional.of(ParserUtil.parseName(nextOfKinNameOptional.get()));

        Optional<String> nextOfKinPhoneOptional = argMultimap.getValue(PREFIX_NEXT_OF_KIN_PHONE);
        Optional<Phone> nextOfKinPhone = nextOfKinPhoneOptional.isEmpty()
                ? Optional.empty()
                : Optional.of(ParserUtil.parsePhone(nextOfKinPhoneOptional.get()));

        Optional<String> nextOfKinAddressOptional = argMultimap.getValue(PREFIX_NEXT_OF_KIN_ADDRESS);
        Optional<Address> nextOfKinAddress = nextOfKinAddressOptional.isEmpty()
                ? Optional.empty()
                : Optional.of(ParserUtil.parseAddress(nextOfKinAddressOptional.get()));

        // TODO: To be removed after integrating changes into Add command.
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(name, phone, email, caseNumber, homeAddress, workAddress, quarantineAddress,
                shnPeriod, nextOfKinName, nextOfKinPhone, nextOfKinAddress, tagList);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
