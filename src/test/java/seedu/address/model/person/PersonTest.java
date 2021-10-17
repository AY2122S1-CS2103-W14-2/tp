package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CASE_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOME_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUARANTINE_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SHN_PERIOD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WORK_ADDRESS_BOB;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withCaseNumber(VALID_CASE_NUMBER_BOB).withHomeAddress(VALID_HOME_ADDRESS_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different case number -> returns false
        editedAlice = new PersonBuilder(ALICE).withCaseNumber(VALID_CASE_NUMBER_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different homeAddress -> returns false
        editedAlice = new PersonBuilder(ALICE).withHomeAddress(VALID_HOME_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different workAddress -> returns false
        editedAlice = new PersonBuilder(ALICE).withWorkAddress(VALID_WORK_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different quarantineAddress -> returns false
        editedAlice = new PersonBuilder(ALICE).withQuarantineAddress(VALID_QUARANTINE_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different shnPeriod -> returns false
        editedAlice = new PersonBuilder(ALICE).withShnPeriod(VALID_SHN_PERIOD_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different nextOfKinName -> returns false
        editedAlice = new PersonBuilder(ALICE).withNextOfKinName(VALID_NEXT_OF_KIN_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different nextOfKinPhone -> returns false
        editedAlice = new PersonBuilder(ALICE).withNextOfKinPhone(VALID_NEXT_OF_KIN_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different nextOfKinAddress -> returns false
        editedAlice = new PersonBuilder(ALICE).withNextOfKinAddress(VALID_NEXT_OF_KIN_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
