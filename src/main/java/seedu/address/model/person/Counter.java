package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a counter used to record the number of times an event has occurred with respect to parent Person.
 * Guarantee: is valid as declared in {@link #isValidCounter(String)}
 */
public class Counter {
    public static final String MESSAGE_CONSTRAINTS =
        "Counter should be a non-negative integer without left-padding 0s";
    public static final String VALIDATION_REGEX = "(^[1-9]+\\d*$)|^0$|((^[1-9]+\\d*)|^0) (true|false)";
    private int value;
    private boolean called;

    /**
     * Default {@code Counter} constructor.
     *
     * @param counter A valid counter.
     */
    public Counter(String counter) {
        this(counter.split(" ")[0], counter.split(" ")[1]);
    }

    /**
     * Constructs a {@code Counter}.
     *
     * @param counter A valid counter.
     */
    public Counter(String counter, String called) {
        requireNonNull(counter);
        checkArgument(isValidCounter(counter), MESSAGE_CONSTRAINTS);
        value = Integer.parseInt(counter);
        this.called = Boolean.valueOf(called);
    }

    /**
     * Returns true if a given string is a valid counter
     */
    public static boolean isValidCounter(String test) { return test.matches(VALIDATION_REGEX); }



    /**
     * Stores that the person has been called for the day.
     * @return Whether the person was previously called for the day already.
     */
    public boolean call() {
        boolean res = called;
        called = true;
        return res;
    }

    /**
     * Sets the person as not being called. Primarily used to refresh called state for new schedules
     * @return Whether the person was previously in called state.
     */
    public boolean unCall() {
        boolean res = called;
        called = false;
        return res;
    }

    /**
     * Sets the person as not picking up for the day.
     */
    public void nonCompliant() {
        this.call();
        this.increment();
    }

    /**
     * Indicates whether the person has been called. Used by predicate to filter our people to insert into schedule.
     * @return Whether the person was previously called for the day already.
     */
    public boolean isCalled() {
        return called;
    }

    /**
     * Sets the person as not picking up for the day.
     */
    public void increment() {
        this.call();
        this.value = this.value + 1;
    }

    @Override
    public String toString() {
        return String.valueOf(value) + " " + String.valueOf(called);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Counter // instanceof handles nulls
            && value == ((Counter) other).value); // state check
    }

    @Override
    public int hashCode() {
        return value;
    }

}
