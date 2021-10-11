package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ShnPeriodTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ShnPeriod(null, null));
    }

    @Test
    public void constructor_invalidShnPeriod_throwsIllegalArgumentException() {
        LocalDate invalidStartDate = LocalDate.of(2021, 01, 02);
        LocalDate invalidEndDate = LocalDate.of(2021, 01, 01);
        assertThrows(IllegalArgumentException.class, () -> new ShnPeriod(invalidStartDate, invalidEndDate));
    }

    @Test
    public void isValidShnPeriod() {
        // null SHN period
        assertThrows(NullPointerException.class, () -> ShnPeriod.isValidShnPeriod(null, null));

        // invalid SHN periods
        assertFalse(ShnPeriod.isValidShnPeriod(
                LocalDate.of(2021, 01, 02),
                LocalDate.of(2021, 01, 01))); // startDate is after endDate

        // valid SHN periods
        assertTrue(ShnPeriod.isValidShnPeriod(
                LocalDate.of(2021, 01, 01),
                LocalDate.of(2021, 01, 01)));
        assertTrue(ShnPeriod.isValidShnPeriod(
                LocalDate.of(2021, 01, 01),
                LocalDate.of(2021, 01, 02)));
    }
}
