package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ShnPeriodTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ShnPeriod(null, null));
    }

    @Test
    public void constructor_invalidShnPeriod_throwsIllegalArgumentException() {
        LocalDateTime invalidStartDate = LocalDateTime.of(2021, 01, 02, 00, 00, 00);
        LocalDateTime invalidEndDate = LocalDateTime.of(2021, 01, 01, 00, 00, 00);
        assertThrows(IllegalArgumentException.class, () -> new ShnPeriod(invalidStartDate, invalidEndDate));
    }

    @Test
    public void isValidShnPeriod() {
        // null SHN period
        assertThrows(NullPointerException.class, () -> ShnPeriod.isValidShnPeriod(null, null));

        // invalid SHN periods
        assertFalse(ShnPeriod.isValidShnPeriod(
                LocalDateTime.of(2021, 01, 02, 00, 00, 00),
                LocalDateTime.of(2021, 01, 01, 00, 00, 00)
        )); // startDate is after endDate

        // valid SHN periods
        assertTrue(ShnPeriod.isValidShnPeriod(
                LocalDateTime.of(2021, 01, 01, 00, 00, 00),
                LocalDateTime.of(2021, 01, 01, 00, 00, 01)));
        assertTrue(ShnPeriod.isValidShnPeriod(
                LocalDateTime.of(2021, 01, 01, 00, 00, 00),
                LocalDateTime.of(2021, 01, 02, 00, 00, 00)));
    }
}
