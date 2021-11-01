package seedu.track2gather.logic.parser;

import static seedu.track2gather.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_SHN_PERIOD_END;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_SHN_PERIOD_START;
import static seedu.track2gather.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.track2gather.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.track2gather.logic.commands.FindCommand;
import seedu.track2gather.model.person.CaseNumber;
import seedu.track2gather.model.person.ShnPeriod;
import seedu.track2gather.model.person.predicates.CaseNumberEqualsKeywordsPredicate;
import seedu.track2gather.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.track2gather.model.person.predicates.PhoneStartsWithKeywordsPredicate;
import seedu.track2gather.model.person.predicates.ShnPeriodEndEqualsKeywordsPredicate;
import seedu.track2gather.model.person.predicates.ShnPeriodStartEqualsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // No prefix
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // Name prefix with blank argument
        assertParseFailure(parser, " " + PREFIX_NAME + "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // Phone prefix with blank argument
        assertParseFailure(parser, " " + PREFIX_PHONE + "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // Case number prefix with blank argument
        assertParseFailure(parser, " " + PREFIX_CASE_NUMBER + "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // Shn period (start) prefix with blank argument
        assertParseFailure(parser, " " + PREFIX_SHN_PERIOD_START + "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // Shn period (end) prefix with blank argument
        assertParseFailure(parser, " " + PREFIX_SHN_PERIOD_END + "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces after prefix
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        String testInput = " " + PREFIX_NAME + "Alice Bob";
        assertParseSuccess(parser, testInput, expectedFindCommand);

        // multiple whitespaces between keywords after prefix
        String testInputWithWhiteSpaces = " " + PREFIX_NAME + " \n Alice \n \t Bob  \t";
        assertParseSuccess(parser, testInputWithWhiteSpaces, expectedFindCommand);
    }

    @Test
    public void parse_validPhoneArgs_returnsFindCommand() {
        // no leading and trailing whitespaces after prefix
        FindCommand expectedFindCommand =
                new FindCommand(new PhoneStartsWithKeywordsPredicate(Arrays.asList("111", "222")));

        String testInput = " " + PREFIX_PHONE + "111 222";
        assertParseSuccess(parser, testInput, expectedFindCommand);

        // multiple whitespaces between keywords after prefix
        String testInputWithWhiteSpaces = " " + PREFIX_PHONE + " \n 111 \n \t 222  \t";
        assertParseSuccess(parser, testInputWithWhiteSpaces, expectedFindCommand);
    }

    @Test
    public void parse_validCaseNumberArgs_returnsFindCommand() {
        // no leading and trailing whitespaces after prefix
        FindCommand expectedFindCommand =
                new FindCommand(new CaseNumberEqualsKeywordsPredicate(Arrays.asList("111", "222")));

        String testInput = " " + PREFIX_CASE_NUMBER + "111 222";
        assertParseSuccess(parser, testInput, expectedFindCommand);

        // multiple whitespaces between keywords after prefix
        String testInputWithWhiteSpaces = " " + PREFIX_CASE_NUMBER + " \n 111 \n \t 222  \t";
        assertParseSuccess(parser, testInputWithWhiteSpaces, expectedFindCommand);
    }

    @Test
    public void parse_invalidCaseNumberArgs_returnsFindCommand() {
        // One invalid keyword
        String invalidKeyword = " " + PREFIX_CASE_NUMBER + "-1";
        assertParseFailure(parser, invalidKeyword, CaseNumber.MESSAGE_CONSTRAINTS);

        // One valid and one invalid keyword
        String invalidTestInput = " " + PREFIX_CASE_NUMBER + "1 -1";
        assertParseFailure(parser, invalidTestInput, CaseNumber.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validShnPeriodStartArgs_returnsFindCommand() {
        // no leading and trailing whitespaces after prefix
        FindCommand expectedFindCommand =
                new FindCommand(new ShnPeriodStartEqualsKeywordsPredicate(Arrays.asList("2000-01-01",
                        "2000-01-10")));

        String testInput = " " + PREFIX_SHN_PERIOD_START + "2000-01-01 2000-01-10";
        assertParseSuccess(parser, testInput, expectedFindCommand);

        // multiple whitespaces between keywords after prefix
        String testInputWithWhiteSpaces = " " + PREFIX_SHN_PERIOD_START
                + " \n 2000-01-01 \n \t 2000-01-10  \t";
        assertParseSuccess(parser, testInputWithWhiteSpaces, expectedFindCommand);
    }

    @Test
    public void parse_invalidShnPeriodStartArgs_returnsFindCommand() {
        // One invalid keyword
        String invalidKeyword = " " + PREFIX_SHN_PERIOD_START + "2000/01/01";
        assertParseFailure(parser, invalidKeyword, ShnPeriod.MESSAGE_CONSTRAINTS_DATE);

        // One valid and one invalid keyword
        String invalidTestInput = " " + PREFIX_SHN_PERIOD_START + "2000-01-01 2000/01/01";
        assertParseFailure(parser, invalidTestInput, ShnPeriod.MESSAGE_CONSTRAINTS_DATE);
    }

    @Test
    public void parse_validShnPeriodEndArgs_returnsFindCommand() {
        // no leading and trailing whitespaces after prefix
        FindCommand expectedFindCommand =
                new FindCommand(new ShnPeriodEndEqualsKeywordsPredicate(Arrays.asList("2000-01-01",
                        "2000-01-10")));

        String testInput = " " + PREFIX_SHN_PERIOD_END + "2000-01-01 2000-01-10";
        assertParseSuccess(parser, testInput, expectedFindCommand);

        // multiple whitespaces between keywords after prefix
        String testInputWithWhiteSpaces = " " + PREFIX_SHN_PERIOD_END
                + " \n 2000-01-01 \n \t 2000-01-10  \t";
        assertParseSuccess(parser, testInputWithWhiteSpaces, expectedFindCommand);
    }

    @Test
    public void parse_invalidShnPeriodEndArgs_returnsFindCommand() {
        // One invalid keyword
        String invalidKeyword = " " + PREFIX_SHN_PERIOD_END + "2000/01/02";
        assertParseFailure(parser, invalidKeyword, ShnPeriod.MESSAGE_CONSTRAINTS_DATE);

        // One valid and one invalid keyword
        String invalidTestInput = " " + PREFIX_SHN_PERIOD_END + "2000-01-02 2000/01/02";
        assertParseFailure(parser, invalidTestInput, ShnPeriod.MESSAGE_CONSTRAINTS_DATE);
    }
}