package seedu.track2gather.model.person.attributes;

import static seedu.track2gather.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the contacts list.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name extends Attribute<String> implements Comparable<Name> {

    public static final String MESSAGE_CONSTRAINTS =
        "Names should not be blank and should not only contain whitespace";

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        super(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return !test.isBlank();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Name // instanceof handles nulls
            && value.equals(((Name) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Name otherName) {
        return value.compareToIgnoreCase(otherName.value);
    }
}
