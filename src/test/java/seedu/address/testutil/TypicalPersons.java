package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CASE_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CASE_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOME_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOME_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUARANTINE_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SHN_PERIOD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WORK_ADDRESS_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline").withPhone("94351253")
            .withEmail("alice@example.com").withCaseNumber("5").withHomeAddress("123, Jurong West Ave 6, #08-111")
            .withWorkAddress("1 Brooke Rd #B1-11 Katong Plaza").withQuarantineAddress("448 Hougang Ave 10 #B1-529")
            .withShnPeriod("2020-09-23 => 2020-10-07").withNextOfKinName("Anthony Reed").withNextOfKinPhone("92456234")
            .withNextOfKinAddress("4 Jalan Besut").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier").withPhone("98765432")
            .withEmail("johnd@example.com").withCaseNumber("6").withHomeAddress("311, Clementi Ave 2, #02-25")
            .withWorkAddress("29 Flora Rd #01-08").withQuarantineAddress("3 Temasek Boulevard #03-028C")
            .withShnPeriod("2020-04-13 => 2020-04-27").withNextOfKinName("John Green").withNextOfKinPhone("84515153")
            .withNextOfKinAddress("120 Hillview Avenue #06-02").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withCaseNumber("7").withHomeAddress("wall street").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withCaseNumber("8").withHomeAddress("10th street").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withCaseNumber("9").withHomeAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withCaseNumber("10").withHomeAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withCaseNumber("11").withHomeAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withCaseNumber("12").withHomeAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withCaseNumber("13").withHomeAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withCaseNumber(VALID_CASE_NUMBER_AMY).withHomeAddress(VALID_HOME_ADDRESS_AMY)
            .build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withCaseNumber(VALID_CASE_NUMBER_BOB).withHomeAddress(VALID_HOME_ADDRESS_BOB)
            .withWorkAddress(VALID_WORK_ADDRESS_BOB).withQuarantineAddress(VALID_QUARANTINE_ADDRESS_BOB)
            .withShnPeriod(VALID_SHN_PERIOD_BOB).withNextOfKinName(VALID_NEXT_OF_KIN_NAME_BOB)
            .withNextOfKinPhone(VALID_NEXT_OF_KIN_PHONE_BOB).withNextOfKinAddress(VALID_NEXT_OF_KIN_ADDRESS_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
