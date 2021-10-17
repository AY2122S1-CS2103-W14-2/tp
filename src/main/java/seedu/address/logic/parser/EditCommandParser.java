package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
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
import static seedu.address.logic.parser.CliSyntax.PREFIX_WORK_ADDRESS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_CASE_NUMBER, PREFIX_HOME_ADDRESS, PREFIX_WORK_ADDRESS, PREFIX_QUARANTINE_ADDRESS,
                PREFIX_SHN_PERIOD, PREFIX_NEXT_OF_KIN_NAME, PREFIX_NEXT_OF_KIN_PHONE, PREFIX_NEXT_OF_KIN_ADDRESS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_CASE_NUMBER).isPresent()) {
            editPersonDescriptor.setCaseNumber(ParserUtil.parseCaseNumber(
                    argMultimap.getValue(PREFIX_CASE_NUMBER).get()));
        }
        if (argMultimap.getValue(PREFIX_HOME_ADDRESS).isPresent()) {
            editPersonDescriptor.setHomeAddress(ParserUtil.parseAddress(
                    argMultimap.getValue(PREFIX_HOME_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_WORK_ADDRESS).isPresent()) {
            editPersonDescriptor.setWorkAddress(ParserUtil.parseAddress(
                    argMultimap.getValue(PREFIX_WORK_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_QUARANTINE_ADDRESS).isPresent()) {
            editPersonDescriptor.setQuarantineAddress(ParserUtil.parseAddress(
                    argMultimap.getValue(PREFIX_QUARANTINE_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_SHN_PERIOD).isPresent()) {
            editPersonDescriptor.setShnPeriod(ParserUtil.parseShnPeriod(
                    argMultimap.getValue(PREFIX_SHN_PERIOD).get()));
        }
        if (argMultimap.getValue(PREFIX_NEXT_OF_KIN_NAME).isPresent()) {
            editPersonDescriptor.setNextOfKinName(ParserUtil.parseName(
                    argMultimap.getValue(PREFIX_NEXT_OF_KIN_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_NEXT_OF_KIN_PHONE).isPresent()) {
            editPersonDescriptor.setNextOfKinPhone(ParserUtil.parsePhone(
                    argMultimap.getValue(PREFIX_NEXT_OF_KIN_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_NEXT_OF_KIN_ADDRESS).isPresent()) {
            editPersonDescriptor.setNextOfKinAddress(ParserUtil.parseAddress(
                    argMultimap.getValue(PREFIX_NEXT_OF_KIN_ADDRESS).get()));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }
}
