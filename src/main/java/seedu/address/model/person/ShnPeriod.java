package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

import javafx.util.Pair;

/**
 * Represents a Person's SHN period in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidShnPeriod(LocalDateTime, LocalDateTime)}
 */
public class ShnPeriod {
    public static final String MESSAGE_CONSTRAINTS =
            "SHN period should be of two dates in the ISO-8601 format (i.e. yyyy-MM-ddTHH:mm:ss), "
                    + "separated by a space. Start date should be keyed before the end date, "
                    + "and must occur earlier than the end date by at least 1 second.";

    public final LocalDateTime startDate;
    public final LocalDateTime endDate;

    /**
     * Constructs an {@code SHN period}.
     *
     * @param startDate A valid SHN start date.
     * @param endDate A valid SHN end date.
     */
    public ShnPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        requireNonNull(startDate);
        requireNonNull(endDate);
        checkArgument(isValidShnPeriod(startDate, endDate), MESSAGE_CONSTRAINTS);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs an {@code SHN period} from a String.
     *
     * @param shnPeriod A valid SHN period.
     */
    public ShnPeriod(String shnPeriod) {
        this(LocalDateTime.parse(shnPeriod.split(" => ", 2)[0]),
                LocalDateTime.parse(shnPeriod.split(" => ", 2)[1]));
    }

    /**
     * Returns true if a given SHN period is a valid SHN period.
     */
    public static boolean isValidShnPeriod(LocalDateTime testStartDate, LocalDateTime testEndDate) {
        requireNonNull(testStartDate);
        requireNonNull(testEndDate);
        return testEndDate.isAfter(testStartDate);
    }

    /**
     * Returns true if a given SHN period in string form is a valid SHN period.
     */
    public static boolean isValidShnPeriodString(String shnPeriod) {
        requireNonNull(shnPeriod);
        LocalDateTime testStartDate = LocalDateTime.parse(shnPeriod.split(" => ", 2)[0]);
        LocalDateTime testEndDate = LocalDateTime.parse(shnPeriod.split(" => ", 2)[1]);
        return testEndDate.isAfter(testStartDate);
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
        return new Pair<>(startDate, endDate).hashCode();
    }
}
