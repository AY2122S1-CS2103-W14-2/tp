package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.tag.Tag;

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
    private final Address homeAddress;
    private final Optional<Address> workAddress;
    private final Optional<Address> quarantineAddress;
    private final Optional<ShnPeriod> shnPeriod;
    private final CaseNumber caseNumber;
    private final Optional<Name> nextOfKinName;
    private final Optional<Phone> nextOfKinPhone;
    private final Optional<Address> nextOfKinAddress;
    // TODO: To remove when integrating changes to Add command.
    @Deprecated
    private final Set<Tag> tags = new HashSet<>();

    /**
     * The following fields must be present and not null: name, phone, email, home_address, tags.
     */
    public Person(Name name, Phone phone, Email email, Address homeAddress, Optional<Address> workAddress,
                  Optional<Address> quarantineAddress, Optional<ShnPeriod> shnPeriod, CaseNumber caseNumber,
                  Optional<Name> nextOfKinName, Optional<Phone> nextOfKinPhone, Optional<Address> nextOfKinAddress,
                  Set<Tag> tags) {
        requireAllNonNull(name, phone, email, homeAddress, workAddress, quarantineAddress,
                nextOfKinName, nextOfKinPhone, nextOfKinAddress, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.quarantineAddress = quarantineAddress;
        this.shnPeriod = shnPeriod;
        this.caseNumber = caseNumber;
        this.nextOfKinName = nextOfKinName;
        this.nextOfKinPhone = nextOfKinPhone;
        this.nextOfKinAddress = nextOfKinAddress;
        this.tags.addAll(tags);
    }

    /**
     * Deprecated constructor.
     */
    @Deprecated
    public Person(Name name, Phone phone, Email email, Address homeAddress, Set<Tag> tags) {
        this(name, phone, email, homeAddress, Optional.empty(), Optional.empty(), Optional.empty(),
                null, Optional.empty(), Optional.empty(), Optional.empty(), tags);
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

    public CaseNumber getCaseNumber() {
        return caseNumber;
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
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @Deprecated
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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
                && otherPerson.getHomeAddress().equals(getHomeAddress())
                // TODO: Add equality checks when integrating changes to Add command.
                // && otherPerson.getWorkAddress().equals(getWorkAddress())
                // && otherPerson.getQuarantineAddress().equals(getQuarantineAddress())
                // && otherPerson.getShnPeriod().equals(getShnPeriod())
                && otherPerson.getCaseNumber().equals(getCaseNumber())
                // && otherPerson.getNextOfKinName().equals(getNextOfKinName())
                // && otherPerson.getNextOfKinPhone().equals(getNextOfKinPhone())
                // && otherPerson.getNextOfKinAddress().equals(getNextOfKinAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, homeAddress, workAddress, quarantineAddress, shnPeriod, caseNumber,
            nextOfKinName, nextOfKinPhone, nextOfKinAddress, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Home Address: ")
                .append(getHomeAddress())
                .append("; Work Address: ")
                .append(getWorkAddress())
                .append("; Quarantine Address: ")
                .append(getQuarantineAddress())
                .append("; SHN Period: ")
                .append(getShnPeriod())
                .append("; Case Number: ")
                .append(getCaseNumber())
                .append("; Next of Kin Name: ")
                .append(getNextOfKinName())
                .append("; Next of Kin Phone: ")
                .append(getNextOfKinPhone())
                .append("; Next of Kin Address: ")
                .append(getNextOfKinAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
