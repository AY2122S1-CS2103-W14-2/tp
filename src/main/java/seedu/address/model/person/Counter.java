package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a counter used to record the number of times an event has occurred with respect to parent Person.
 * Guarantee: is valid as declared in {@link #isValidCounter(String)}
 */
public class Counter {
    public static final String MESSAGE_CONSTRAINTS =
        "Counter should be a non-negative integer without left-padding 0s";
    public static final String VALIDATION_REGEX = "(^[1-9]+\\d*$)|^0$|((^[1-9]+\\d*)|^0) (true|false)";
    private final int numFailedCalls;
    private final boolean isCalledForCurrentSession;

    /**
     * Default {@code Counter} constructor.
     *
     * @param counter Arguments containing number of times person has been called, and optionally if they have
     *                previously been called.
     */
    public Counter(String counter) {
        String[] parsed = counter.split(" ");
        if (parsed.length == 1) {
            this.isCalledForCurrentSession = false;
        } else {
            this.isCalledForCurrentSession = Boolean.valueOf(parsed[1]);
        }
        this.numFailedCalls = Integer.valueOf(parsed[0]);
    }

    /**
     * {@code Counter} constuctor used by Counter's methods.
     *
     * @param counter A valid {@code Counter}.
     */
    public Counter(int counter, boolean isCalled) {
        requireNonNull(counter);
        requireNonNull(isCalled);
        this.numFailedCalls = counter;
        this.isCalledForCurrentSession = isCalled;
    }

    /**
     * Tests validity of a given string as input to be parsed into {@code Counter}
     * @param test string to test
     * @return bool whether the string is valid as an input.
     */
    public static boolean isValidCounter(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Stores that the person has been called for the day.
     * @return counter A new {@code Counter} storing that the person has been called.
     */
    public Counter call() {
        return new Counter(numFailedCalls, true);
    }

    /**
     * Sets the person as not being called. Primarily used to refresh called state for new schedules.
     * @return counter A new {@code Counter} storing that the person has not been called.
     */
    public Counter resetCallStatus() {
        return new Counter(numFailedCalls, false);
    }

    /**
     * Indicates whether the person has been called. Used by predicate to filter our people to insert into schedule.
     * @return Whether the person was previously called for the day already.
     */
    public boolean isCalledForCurrentSession() {
        return isCalledForCurrentSession;
    }

    /**
     * Indicates number of times person has failed to pick up since the start of SHN period.
     * @return Number of times person has failed to pick up since the start of SHN period.
     */
    public int getNumFailedCalls() {
        return numFailedCalls;
    }

    /**
     * Sets the person as not picking up for the day.
     * @return counter A new {@code Counter} storing a call and non-compliance instance.
     */
    public Counter increment() {
        return new Counter(numFailedCalls + 1, true);
    }

    @Override
    public String toString() {
        return numFailedCalls + " " + isCalledForCurrentSession;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Counter // instanceof handles nulls
            && numFailedCalls == ((Counter) other).numFailedCalls); // state check
    }

    @Override
    public int hashCode() {
        return numFailedCalls;
    }

}
