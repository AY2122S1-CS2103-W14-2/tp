package seedu.address.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.CaseNumber;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.ShnPeriod;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String caseNumber;
    private final String homeAddress;
    private final String workAddress;
    private final String quarantineAddress;
    private final String shnPeriod;
    private final String nextOfKinName;
    private final String nextOfKinPhone;
    private final String nextOfKinAddress;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("caseNumber") String caseNumber,
                             @JsonProperty("homeAddress") String homeAddress,
                             @JsonProperty("workAddress") String workAddress,
                             @JsonProperty("quarantineAddress") String quarantineAddress,
                             @JsonProperty("shnPeriod") String shnPeriod,
                             @JsonProperty("nextOfKinName") String nextOfKinName,
                             @JsonProperty("nextOfKinPhone") String nextOfKinPhone,
                             @JsonProperty("nextOfKinAddress") String nextOfKinAddress) {
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
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        caseNumber = source.getCaseNumber().value;
        homeAddress = source.getHomeAddress().value;
        workAddress = source.getWorkAddress().map(Object::toString).orElse(null);
        quarantineAddress = source.getQuarantineAddress().map(Object::toString).orElse(null);
        shnPeriod = source.getShnPeriod().map(Object::toString).orElse(null);
        nextOfKinName = source.getNextOfKinName().map(Object::toString).orElse(null);
        nextOfKinPhone = source.getNextOfKinPhone().map(Object::toString).orElse(null);
        nextOfKinAddress = source.getNextOfKinAddress().map(Object::toString).orElse(null);
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (caseNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CaseNumber.class.getSimpleName()));
        }
        if (!CaseNumber.isValidCaseNumber(caseNumber)) {
            throw new IllegalValueException(CaseNumber.MESSAGE_CONSTRAINTS);
        }
        final CaseNumber modelCaseNumber = new CaseNumber(caseNumber);

        if (homeAddress == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(homeAddress)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelHomeAddress = new Address(homeAddress);

        if (workAddress != null && !Address.isValidAddress(workAddress)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Optional<Address> modelWorkAddress = Optional.ofNullable(workAddress).map(Address::new);

        if (quarantineAddress != null && !Address.isValidAddress(quarantineAddress)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Optional<Address> modelQuarantineAddress = Optional.ofNullable(quarantineAddress).map(Address::new);

        if (shnPeriod != null && !ShnPeriod.isValidShnPeriodString(shnPeriod)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Optional<ShnPeriod> modelShnPeriod = Optional.ofNullable(shnPeriod).map(ShnPeriod::new);

        if (nextOfKinName != null && !Name.isValidName(nextOfKinName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Optional<Name> modelNextOfKinName = Optional.ofNullable(nextOfKinName).map(Name::new);

        if (nextOfKinPhone != null && !Phone.isValidPhone(nextOfKinPhone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Optional<Phone> modelNextOfKinPhone = Optional.ofNullable(nextOfKinPhone).map(Phone::new);

        if (nextOfKinAddress != null && !Address.isValidAddress(nextOfKinAddress)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Optional<Address> modelNextOfKinAddress = Optional.ofNullable(nextOfKinAddress).map(Address::new);

        return new Person(modelName, modelPhone, modelEmail, modelCaseNumber, modelHomeAddress, modelWorkAddress,
                modelQuarantineAddress, modelShnPeriod, modelNextOfKinName, modelNextOfKinPhone, modelNextOfKinAddress);
    }

}
