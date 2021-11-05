package seedu.track2gather.model.person.attributes;

import static seedu.track2gather.commons.util.AppUtil.checkArgument;

/**
 * Represents a quarantined person's case number in the contacts list.
 * Guarantees: immutable; is valid as declared in {@link #isValidCaseNumber(String)}
 */
public class CaseNumber extends Attribute<String> implements Comparable<CaseNumber> {
    public static final String MESSAGE_CONSTRAINTS = "Case numbers should contain only integers with no "
            + "leading zeros, and must be between 1 to 6 digits long.";
    public static final String MESSAGE_CONSTRAINTS_KEYWORDS = "Case number keywords should contain only "
            + "integers with no leading zeros, and must be between 1 to 6 digits long.";
    public static final String VALIDATION_REGEX = "^[1-9]\\d{0,5}$";

    /**
     * Constructs a {@code CaseNumber}.
     *
     * @param caseNumber A valid case number.
     */
    public CaseNumber(String caseNumber) {
        super(caseNumber);
        checkArgument(isValidCaseNumber(caseNumber), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid case number.
     */
    public static boolean isValidCaseNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CaseNumber // instanceof handles nulls
                && value.equals(((CaseNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(CaseNumber otherCaseNumber) {
        return Integer.compare(Integer.parseInt(value), Integer.parseInt(otherCaseNumber.value));
    }
}
