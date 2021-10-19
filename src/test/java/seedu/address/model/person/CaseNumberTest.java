package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CaseNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CaseNumber(null));
    }

    @Test
    public void constructor_invalidCaseNumber_throwsIllegalArgumentException() {
        String invalidCaseNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new CaseNumber(invalidCaseNumber));
    }

    @Test
    public void isValidCaseNumber() {
        // null
        assertThrows(NullPointerException.class, () -> CaseNumber.isValidCaseNumber(null));

        // blank caseNumber
        assertFalse(CaseNumber.isValidCaseNumber("")); // empty string
        assertFalse(CaseNumber.isValidCaseNumber(" ")); // spaces only

        // invalid numbers
        assertFalse(CaseNumber.isValidCaseNumber("-1")); // negative integer
        assertFalse(CaseNumber.isValidCaseNumber("1.4")); // decimal
        assertFalse(CaseNumber.isValidCaseNumber("1a")); // number with letters
        assertFalse(CaseNumber.isValidCaseNumber("1.")); // decimal point
        assertFalse(CaseNumber.isValidCaseNumber("1 ")); // white space back
        assertFalse(CaseNumber.isValidCaseNumber(" 1")); // white space front
        assertFalse(CaseNumber.isValidCaseNumber("01")); // padded with 0 in front
        assertFalse(CaseNumber.isValidCaseNumber("1234567")); // 7 digit number

        // valid caseNumber
        assertTrue(CaseNumber.isValidCaseNumber("123456")); // 6 digit number
        assertTrue(CaseNumber.isValidCaseNumber("1")); // <6 digit number
    }
}
