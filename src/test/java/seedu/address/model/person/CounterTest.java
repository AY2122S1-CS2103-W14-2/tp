package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CounterTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Counter(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidCounter = "";
        assertThrows(IllegalArgumentException.class, () -> new Counter(invalidCounter));
    }

    @Test
    public void isValidCounter() {
        // null
        assertThrows(NullPointerException.class, () -> Counter.isValidCounter(null));

        // blank Counter
        assertFalse(Counter.isValidCounter("")); // empty string
        assertFalse(Counter.isValidCounter(" ")); // spaces only

        // invalid numbers
        assertFalse(Counter.isValidCounter("-1")); // negative integer
        assertFalse(Counter.isValidCounter("1.4")); // decimal
        assertFalse(Counter.isValidCounter("1a")); // number with letters
        assertFalse(Counter.isValidCounter("1.")); // decimal point
        assertFalse(Counter.isValidCounter("1 ")); // white space back
        assertFalse(Counter.isValidCounter(" 1")); // white space front
        assertFalse(Counter.isValidCounter("01")); // padded with 0 in front

        // valid Counter
        assertTrue(Counter.isValidCounter("123456")); // 6 digit number
        assertTrue(Counter.isValidCounter("1")); // <6 digit number
        assertTrue(Counter.isValidCounter("0")); // default starting value
    }
}
