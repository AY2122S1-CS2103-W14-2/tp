package seedu.track2gather.logic.parser;

import static seedu.track2gather.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_SHN_PERIOD_END;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_SHN_PERIOD_START;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.track2gather.logic.commands.FindCommand;
import seedu.track2gather.logic.parser.exceptions.ParseException;
import seedu.track2gather.model.person.CaseNumber;
import seedu.track2gather.model.person.Name;
import seedu.track2gather.model.person.Phone;
import seedu.track2gather.model.person.ShnPeriod;
import seedu.track2gather.model.person.predicates.CaseNumberEqualsKeywordsPredicate;
import seedu.track2gather.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.track2gather.model.person.predicates.PhoneStartsWithKeywordsPredicate;
import seedu.track2gather.model.person.predicates.ShnPeriodEndEqualsKeywordsPredicate;
import seedu.track2gather.model.person.predicates.ShnPeriodStartEqualsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_CASE_NUMBER, PREFIX_SHN_PERIOD_START, PREFIX_SHN_PERIOD_END);

        List<Prefix> prefixesWithValue = getPrefixesWithValue(argMultimap, PREFIX_NAME,
                PREFIX_PHONE, PREFIX_CASE_NUMBER, PREFIX_SHN_PERIOD_START, PREFIX_SHN_PERIOD_END);

        // Checks if exactly one prefix is entered by the user
        if (prefixesWithValue.size() != 1 || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Prefix prefixWithValue = prefixesWithValue.get(0);
        String value = argMultimap.getValue(prefixWithValue).get().trim();
        // Checks if user entered an argument along with the prefix
        if (value.isBlank()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] keywords = value.split("\\s+");
        List<String> keywordList = Arrays.asList(keywords);
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            boolean areValidNames = keywordList.stream().allMatch(Name::isValidName);
            if (!areValidNames) {
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            }
            return new FindCommand(new NameContainsKeywordsPredicate(keywordList));
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            boolean areValidPhones = keywordList.stream().allMatch(Phone::isValidPhoneKeyword);
            if (!areValidPhones) {
                throw new ParseException(Phone.MESSAGE_CONSTRAINTS_KEYWORD);
            }
            return new FindCommand(new PhoneStartsWithKeywordsPredicate(keywordList));
        }

        if (argMultimap.getValue(PREFIX_CASE_NUMBER).isPresent()) {
            boolean areValidCaseNumbers = keywordList.stream().allMatch(CaseNumber::isValidCaseNumber);
            if (!areValidCaseNumbers) {
                throw new ParseException(CaseNumber.MESSAGE_CONSTRAINTS);
            }
            return new FindCommand(new CaseNumberEqualsKeywordsPredicate(keywordList));
        }

        if (argMultimap.getValue(PREFIX_SHN_PERIOD_START).isPresent()) {
            boolean areValidDates = keywordList.stream().allMatch(ShnPeriod::isValidDate);
            if (!areValidDates) {
                throw new ParseException(ShnPeriod.MESSAGE_CONSTRAINTS_DATE);
            }
            return new FindCommand(new ShnPeriodStartEqualsKeywordsPredicate(keywordList));
        }

        if (argMultimap.getValue(PREFIX_SHN_PERIOD_END).isPresent()) {
            boolean areValidDates = keywordList.stream().allMatch(ShnPeriod::isValidDate);
            if (!areValidDates) {
                throw new ParseException(ShnPeriod.MESSAGE_CONSTRAINTS_DATE);
            }
            return new FindCommand(new ShnPeriodEndEqualsKeywordsPredicate(keywordList));
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    /**
     * Returns a list of prefixes with values.
     */
    private List<Prefix> getPrefixesWithValue(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .collect(Collectors.toList());
    }
}
