package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.CaseNumber;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_HOME_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_CASE_NUMBER = "456";

    private Name name;
    private Phone phone;
    private Email email;
    private Address homeAddress;
    private CaseNumber caseNumber;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        homeAddress = new Address(DEFAULT_HOME_ADDRESS);
        caseNumber = new CaseNumber(DEFAULT_CASE_NUMBER);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        homeAddress = personToCopy.getHomeAddress();
        caseNumber = personToCopy.getCaseNumber();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withHomeAddress(String homeAddress) {
        this.homeAddress = new Address(homeAddress);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code CaseNumber} of the {@code Person} that we are building.
     */
    public PersonBuilder withCaseNumber(String caseNumber) {
        this.caseNumber = new CaseNumber(caseNumber);
        return this;
    }

    /**
     * Creates {@code Person} with attributes corresponding to those set by the builder.
     *
     * @return built custom {@code Person}
     */
    public Person build() {
        return new Person(name, phone, email, homeAddress, Optional.empty(), Optional.empty(), Optional.empty(),
                caseNumber, Optional.empty(), Optional.empty(), Optional.empty(), tags);
    }

}
