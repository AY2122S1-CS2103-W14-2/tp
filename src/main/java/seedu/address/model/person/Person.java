package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final CaseNumber caseNumber;
    private final Address homeAddress;
    private final Optional<Address> workAddress;
    private final Optional<Address> quarantineAddress;
    private final Optional<ShnPeriod> shnPeriod;
    private final Optional<Name> nextOfKinName;
    private final Optional<Phone> nextOfKinPhone;
    private final Optional<Address> nextOfKinAddress;

    /**
     * Constructor for Person.
     *
     * All fields must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, CaseNumber caseNumber, Address homeAddress,
                  Optional<Address> workAddress, Optional<Address> quarantineAddress, Optional<ShnPeriod> shnPeriod,
                  Optional<Name> nextOfKinName, Optional<Phone> nextOfKinPhone, Optional<Address> nextOfKinAddress) {
        requireAllNonNull(name, phone, email, caseNumber, homeAddress, workAddress, quarantineAddress,
                shnPeriod, nextOfKinName, nextOfKinPhone, nextOfKinAddress);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.caseNumber = caseNumber;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.quarantineAddress = quarantineAddress;
        this.shnPeriod = shnPeriod;
        this.nextOfKinName = nextOfKinName;
        this.nextOfKinPhone = nextOfKinPhone;
        this.nextOfKinAddress = nextOfKinAddress;
    }

    /**
     * Short constructor for Person with no optional attributes.
     */
    public Person(Name name, Phone phone, Email email, CaseNumber caseNumber, Address homeAddress) {
        this(name, phone, email, caseNumber, homeAddress, Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty());
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public CaseNumber getCaseNumber() {
        return caseNumber;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public Optional<Address> getWorkAddress() {
        return workAddress;
    }

    public Optional<Address> getQuarantineAddress() {
        return quarantineAddress;
    }

    public Optional<ShnPeriod> getShnPeriod() {
        return shnPeriod;
    }

    public Optional<Name> getNextOfKinName() {
        return nextOfKinName;
    }

    public Optional<Phone> getNextOfKinPhone() {
        return nextOfKinPhone;
    }

    public Optional<Address> getNextOfKinAddress() {
        return nextOfKinAddress;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getCaseNumber().equals(getCaseNumber())
                && otherPerson.getHomeAddress().equals(getHomeAddress())
                && otherPerson.getWorkAddress().equals(getWorkAddress())
                && otherPerson.getQuarantineAddress().equals(getQuarantineAddress())
                && otherPerson.getShnPeriod().equals(getShnPeriod())
                && otherPerson.getNextOfKinName().equals(getNextOfKinName())
                && otherPerson.getNextOfKinPhone().equals(getNextOfKinPhone())
                && otherPerson.getNextOfKinAddress().equals(getNextOfKinAddress());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, caseNumber, homeAddress, workAddress, quarantineAddress, shnPeriod,
            nextOfKinName, nextOfKinPhone, nextOfKinAddress);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Case Number: ")
                .append(getCaseNumber())
                .append("; Home Address: ")
                .append(getHomeAddress())
                .append("; Work Address: ")
                .append(getWorkAddress())
                .append("; Quarantine Address: ")
                .append(getQuarantineAddress())
                .append("; SHN Period: ")
                .append(getShnPeriod())
                .append("; Next of Kin Name: ")
                .append(getNextOfKinName())
                .append("; Next of Kin Phone: ")
                .append(getNextOfKinPhone())
                .append("; Next of Kin Address: ")
                .append(getNextOfKinAddress());
        return builder.toString();
    }

}
