package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a Person's SHN period in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidShnPeriod(LocalDate, LocalDate)}
 */
public class ShnPeriod {
    public static final String MESSAGE_CONSTRAINTS =
            "SHN period should be of two dates in the ISO-8601 format (i.e. yyyy-MM-dd), "
            + "separated by a space. Start date should be keyed before the end date, "
            + "and must occur earlier than the end date by at least 1 second.";

    public final LocalDate startDate;
    public final LocalDate endDate;

    /**
     * Constructs a {@code SHN period}.
     *
     * @param startDate A valid SHN start date.
     * @param endDate A valid SHN end date.
     */
    public ShnPeriod(LocalDate startDate, LocalDate endDate) {
        requireNonNull(startDate);
        requireNonNull(endDate);
        checkArgument(isValidShnPeriod(startDate, endDate), MESSAGE_CONSTRAINTS);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs a {@code SHN period} from a String.
     *
     * @param shnPeriod A valid SHN period.
     */
    public ShnPeriod(String shnPeriod) {
        this(LocalDate.parse(shnPeriod.split(" => ", 2)[0]),
                LocalDate.parse(shnPeriod.split(" => ", 2)[1]));
    }

    /**
     * Tests validity of a given dates as input to be parsed into {@code ShnPeriod}
     * @param testStartDate date to test as start of period
     * @param testEndDate date to test as end of period
     * @return bool whether the dates is valid as range for ShnPeriod.
     */
    public static boolean isValidShnPeriod(LocalDate testStartDate, LocalDate testEndDate) {
        requireNonNull(testStartDate);
        requireNonNull(testEndDate);
        return testEndDate.compareTo(testStartDate) >= 0; // end date is same date or after start date
    }

    /**
     * Tests validity of a given string as input to be parsed into {@code ShnPeriod}
     * @param test string to test
     * @return bool whether the string is valid as an input.
     */
    public static boolean isValidShnPeriodString(String test) {
        requireNonNull(test);
        String[] dates = test.split(" => ", 2);
        LocalDate testStartDate = LocalDate.parse(dates[0]);
        LocalDate testEndDate = LocalDate.parse(dates[1]);
        return isValidShnPeriod(testStartDate, testEndDate);
    }

    @Override
    public String toString() {
        return String.format("%s => %s", startDate, endDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShnPeriod // instanceof handles nulls
                && startDate.equals(((ShnPeriod) other).startDate)
                && endDate.equals(((ShnPeriod) other).endDate)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
