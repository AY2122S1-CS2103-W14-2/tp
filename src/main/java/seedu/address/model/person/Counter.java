package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a counter used to record the number of times an event has occurred with respect to parent Person.
 * Guarantee: is valid as declared in {@link #isValidCounter(String)}
 */
public class Counter {
    public static final String MESSAGE_CONSTRAINTS =
        "Counter should be a non-negative integer with no leading zeros";
    public static final String VALIDATION_REGEX = "(^[1-9]+\\d*$)|^0$";
    public final String value;

    /**
     * Constructs a {@code Counter}.
     *
     * @param counter A valid counter.
     */
    public Counter(String counter) {
        requireNonNull(counter);
        checkArgument(isValidCounter(counter), MESSAGE_CONSTRAINTS);
        value = counter;
    }

    /**
     * Returns true if a given string is a valid counter
     */
    public static boolean isValidCounter(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Counter // instanceof handles nulls
            && value.equals(((Counter) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
