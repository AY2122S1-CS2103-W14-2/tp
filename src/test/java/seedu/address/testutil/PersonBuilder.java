package seedu.address.testutil;

import java.util.Optional;

import seedu.address.model.person.Address;
import seedu.address.model.person.CaseNumber;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.ShnPeriod;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_CASE_NUMBER = "456";
    public static final String DEFAULT_HOME_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private CaseNumber caseNumber;
    private Address homeAddress;
    private Optional<Address> workAddress;
    private Optional<Address> quarantineAddress;
    private Optional<ShnPeriod> shnPeriod;
    private Optional<Name> nextOfKinName;
    private Optional<Phone> nextOfKinPhone;
    private Optional<Address> nextOfKinAddress;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        caseNumber = new CaseNumber(DEFAULT_CASE_NUMBER);
        homeAddress = new Address(DEFAULT_HOME_ADDRESS);
        workAddress = Optional.empty();
        quarantineAddress = Optional.empty();
        shnPeriod = Optional.empty();
        nextOfKinName = Optional.empty();
        nextOfKinPhone = Optional.empty();
        nextOfKinAddress = Optional.empty();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        caseNumber = personToCopy.getCaseNumber();
        homeAddress = personToCopy.getHomeAddress();
        workAddress = personToCopy.getWorkAddress();
        quarantineAddress = personToCopy.getQuarantineAddress();
        shnPeriod = personToCopy.getShnPeriod();
        nextOfKinName = personToCopy.getNextOfKinName();
        nextOfKinPhone = personToCopy.getNextOfKinPhone();
        nextOfKinAddress = personToCopy.getNextOfKinAddress();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
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
     * Sets the {@code homeAddress} of the {@code Person} that we are building.
     */
    public PersonBuilder withHomeAddress(String homeAddress) {
        this.homeAddress = new Address(homeAddress);
        return this;
    }

    /**
     * Sets the {@code workAddress} of the {@code Person} that we are building.
     */
    public PersonBuilder withWorkAddress(String workAddress) {
        this.workAddress = Optional.ofNullable(workAddress).map(Address::new);
        return this;
    }

    /**
     * Sets the {@code quarantineAddress} of the {@code Person} that we are building.
     */
    public PersonBuilder withQuarantineAddress(String quarantineAddress) {
        this.quarantineAddress = Optional.ofNullable(quarantineAddress).map(Address::new);
        return this;
    }

    /**
     * Sets the {@code shnPeriod} of the {@code Person} that we are building.
     */
    public PersonBuilder withShnPeriod(String shnPeriod) {
        this.shnPeriod = Optional.ofNullable(shnPeriod).map(ShnPeriod::new);
        return this;
    }

    /**
     * Sets the {@code nextOfKinName} of the {@code Person} that we are building.
     */
    public PersonBuilder withNextOfKinName(String nextOfKinName) {
        this.nextOfKinName = Optional.ofNullable(nextOfKinName).map(Name::new);
        return this;
    }

    /**
     * Sets the {@code nextOfKinPhone} of the {@code Person} that we are building.
     */
    public PersonBuilder withNextOfKinPhone(String nextOfKinPhone) {
        this.nextOfKinPhone = Optional.ofNullable(nextOfKinPhone).map(Phone::new);
        return this;
    }

    /**
     * Sets the {@code nextOfKinAddress} of the {@code Person} that we are building.
     */
    public PersonBuilder withNextOfKinAddress(String nextOfKinAddress) {
        this.nextOfKinAddress = Optional.ofNullable(nextOfKinAddress).map(Address::new);
        return this;
    }

    /**
     * Creates {@code Person} with attributes corresponding to those set by the builder.
     *
     * @return built custom {@code Person}
     */
    public Person build() {
        return new Person(name, phone, email, caseNumber, homeAddress, workAddress, quarantineAddress,
                shnPeriod, nextOfKinName, nextOfKinPhone, nextOfKinAddress);
    }

}
