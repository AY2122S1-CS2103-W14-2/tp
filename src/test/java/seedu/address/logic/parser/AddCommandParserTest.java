package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CASE_NUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CASE_NUMBER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.HOME_ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.HOME_ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CASE_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_HOME_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NEXT_OF_KIN_ADDRESS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NEXT_OF_KIN_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NEXT_OF_KIN_PHONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUARANTINE_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SHN_PERIOD;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_WORK_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NEXT_OF_KIN_ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NEXT_OF_KIN_ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NEXT_OF_KIN_NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NEXT_OF_KIN_NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NEXT_OF_KIN_PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NEXT_OF_KIN_PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.QUARANTINE_ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.QUARANTINE_ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SHN_PERIOD_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SHN_PERIOD_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CASE_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOME_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.WORK_ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.WORK_ADDRESS_DESC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.CaseNumber;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.ShnPeriod;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + CASE_NUMBER_DESC_BOB + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB
                + SHN_PERIOD_DESC_BOB + NEXT_OF_KIN_NAME_DESC_BOB + NEXT_OF_KIN_PHONE_DESC_BOB
                + NEXT_OF_KIN_ADDRESS_DESC_BOB, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + CASE_NUMBER_DESC_BOB + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB
                + SHN_PERIOD_DESC_BOB + NEXT_OF_KIN_NAME_DESC_BOB + NEXT_OF_KIN_PHONE_DESC_BOB
                + NEXT_OF_KIN_ADDRESS_DESC_BOB, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + CASE_NUMBER_DESC_BOB + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB
                + SHN_PERIOD_DESC_BOB + NEXT_OF_KIN_NAME_DESC_BOB + NEXT_OF_KIN_PHONE_DESC_BOB
                + NEXT_OF_KIN_ADDRESS_DESC_BOB, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + CASE_NUMBER_DESC_BOB + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB
                + SHN_PERIOD_DESC_BOB + NEXT_OF_KIN_NAME_DESC_BOB + NEXT_OF_KIN_PHONE_DESC_BOB
                + NEXT_OF_KIN_ADDRESS_DESC_BOB, new AddCommand(expectedPerson));

        // multiple case numbers - last case number accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + CASE_NUMBER_DESC_AMY + CASE_NUMBER_DESC_BOB + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB
                + QUARANTINE_ADDRESS_DESC_BOB + SHN_PERIOD_DESC_BOB + NEXT_OF_KIN_NAME_DESC_BOB
                + NEXT_OF_KIN_PHONE_DESC_BOB + NEXT_OF_KIN_ADDRESS_DESC_BOB, new AddCommand(expectedPerson));

        // multiple home addresses - last home address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_AMY + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB
                + QUARANTINE_ADDRESS_DESC_BOB + SHN_PERIOD_DESC_BOB + NEXT_OF_KIN_NAME_DESC_BOB
                + NEXT_OF_KIN_PHONE_DESC_BOB + NEXT_OF_KIN_ADDRESS_DESC_BOB, new AddCommand(expectedPerson));

        // multiple work addresses - last work address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_AMY + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_AMY + WORK_ADDRESS_DESC_BOB
                + QUARANTINE_ADDRESS_DESC_BOB + SHN_PERIOD_DESC_BOB + NEXT_OF_KIN_NAME_DESC_BOB
                + NEXT_OF_KIN_PHONE_DESC_BOB + NEXT_OF_KIN_ADDRESS_DESC_BOB, new AddCommand(expectedPerson));

        // multiple quarantine addresses - last quarantine address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_AMY + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_AMY
                + QUARANTINE_ADDRESS_DESC_BOB + SHN_PERIOD_DESC_BOB + NEXT_OF_KIN_NAME_DESC_BOB
                + NEXT_OF_KIN_PHONE_DESC_BOB + NEXT_OF_KIN_ADDRESS_DESC_BOB, new AddCommand(expectedPerson));

        // multiple shn periods - last shn period accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_AMY + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB
                + SHN_PERIOD_DESC_AMY + SHN_PERIOD_DESC_BOB + NEXT_OF_KIN_NAME_DESC_BOB
                + NEXT_OF_KIN_PHONE_DESC_BOB + NEXT_OF_KIN_ADDRESS_DESC_BOB, new AddCommand(expectedPerson));

        // multiple next-of-kin names - last next-of-kin name accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_AMY + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB
                + SHN_PERIOD_DESC_BOB + NEXT_OF_KIN_NAME_DESC_AMY + NEXT_OF_KIN_NAME_DESC_BOB
                + NEXT_OF_KIN_PHONE_DESC_BOB + NEXT_OF_KIN_ADDRESS_DESC_BOB, new AddCommand(expectedPerson));

        // multiple next-of-kin phones - last next-of-kin phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_AMY + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB
                + SHN_PERIOD_DESC_BOB + NEXT_OF_KIN_NAME_DESC_BOB + NEXT_OF_KIN_PHONE_DESC_AMY
                + NEXT_OF_KIN_PHONE_DESC_BOB + NEXT_OF_KIN_ADDRESS_DESC_BOB, new AddCommand(expectedPerson));

        // multiple next-of-kin addresses - last next-of-kin address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_AMY + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB
                + SHN_PERIOD_DESC_BOB + NEXT_OF_KIN_NAME_DESC_BOB + NEXT_OF_KIN_PHONE_DESC_BOB
                + NEXT_OF_KIN_ADDRESS_DESC_AMY + NEXT_OF_KIN_ADDRESS_DESC_BOB, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Person expectedPerson = new PersonBuilder(AMY).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + CASE_NUMBER_DESC_AMY
                + HOME_ADDRESS_DESC_AMY, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + VALID_HOME_ADDRESS_BOB, expectedMessage);

        // missing case number prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_CASE_NUMBER_BOB
                + HOME_ADDRESS_DESC_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_CASE_NUMBER_BOB
                + VALID_HOME_ADDRESS_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB + SHN_PERIOD_DESC_BOB
                + NEXT_OF_KIN_NAME_DESC_BOB + NEXT_OF_KIN_PHONE_DESC_BOB
                + NEXT_OF_KIN_ADDRESS_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB + SHN_PERIOD_DESC_BOB
                + NEXT_OF_KIN_NAME_DESC_BOB + NEXT_OF_KIN_PHONE_DESC_BOB
                + NEXT_OF_KIN_ADDRESS_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB + SHN_PERIOD_DESC_BOB
                + NEXT_OF_KIN_NAME_DESC_BOB + NEXT_OF_KIN_PHONE_DESC_BOB
                + NEXT_OF_KIN_ADDRESS_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid case number
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_CASE_NUMBER_DESC
                + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB + SHN_PERIOD_DESC_BOB
                + NEXT_OF_KIN_NAME_DESC_BOB + NEXT_OF_KIN_PHONE_DESC_BOB
                + NEXT_OF_KIN_ADDRESS_DESC_BOB, CaseNumber.MESSAGE_CONSTRAINTS);

        // invalid home address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + INVALID_HOME_ADDRESS_DESC + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB + SHN_PERIOD_DESC_BOB
                + NEXT_OF_KIN_NAME_DESC_BOB + NEXT_OF_KIN_PHONE_DESC_BOB
                + NEXT_OF_KIN_ADDRESS_DESC_BOB, Address.MESSAGE_CONSTRAINTS);

        // invalid work address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_BOB + INVALID_WORK_ADDRESS_DESC + QUARANTINE_ADDRESS_DESC_BOB + SHN_PERIOD_DESC_BOB
                + NEXT_OF_KIN_NAME_DESC_BOB + NEXT_OF_KIN_PHONE_DESC_BOB
                + NEXT_OF_KIN_ADDRESS_DESC_BOB, Address.MESSAGE_CONSTRAINTS);

        // invalid quarantine address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + INVALID_QUARANTINE_ADDRESS_DESC
                + SHN_PERIOD_DESC_BOB + NEXT_OF_KIN_NAME_DESC_BOB + NEXT_OF_KIN_PHONE_DESC_BOB
                + NEXT_OF_KIN_ADDRESS_DESC_BOB, Address.MESSAGE_CONSTRAINTS);

        // invalid shn period
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB
                + INVALID_SHN_PERIOD + NEXT_OF_KIN_NAME_DESC_BOB + NEXT_OF_KIN_PHONE_DESC_BOB
                + NEXT_OF_KIN_ADDRESS_DESC_BOB, ShnPeriod.MESSAGE_CONSTRAINTS);

        // invalid next-of-kin name
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB
                + SHN_PERIOD_DESC_BOB + INVALID_NEXT_OF_KIN_NAME + NEXT_OF_KIN_PHONE_DESC_BOB
                + NEXT_OF_KIN_ADDRESS_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid next-of-kin phone
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB
                + SHN_PERIOD_DESC_BOB + NEXT_OF_KIN_NAME_DESC_BOB + INVALID_NEXT_OF_KIN_PHONE
                + NEXT_OF_KIN_ADDRESS_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid next-of-kin address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB
                + SHN_PERIOD_DESC_BOB + NEXT_OF_KIN_NAME_DESC_BOB + NEXT_OF_KIN_PHONE_DESC_BOB
                + INVALID_NEXT_OF_KIN_ADDRESS, Address.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB
                + INVALID_HOME_ADDRESS_DESC + WORK_ADDRESS_DESC_BOB + QUARANTINE_ADDRESS_DESC_BOB
                + SHN_PERIOD_DESC_BOB + NEXT_OF_KIN_NAME_DESC_BOB + NEXT_OF_KIN_PHONE_DESC_BOB
                + NEXT_OF_KIN_ADDRESS_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + CASE_NUMBER_DESC_BOB + HOME_ADDRESS_DESC_BOB + WORK_ADDRESS_DESC_BOB
                        + QUARANTINE_ADDRESS_DESC_BOB + SHN_PERIOD_DESC_BOB + NEXT_OF_KIN_NAME_DESC_BOB
                        + NEXT_OF_KIN_PHONE_DESC_BOB + NEXT_OF_KIN_ADDRESS_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
