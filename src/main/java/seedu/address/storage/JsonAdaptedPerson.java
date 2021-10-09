package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String homeAddress;
    private final Object workAddress;
    private final Object quarantineAddress;
    private final Object shnPeriod;
    private final Object caseNumber;
    private final Object nextOfKinName;
    private final Object nextOfKinPhone;
    private final Object nextOfKinAddress;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @Deprecated
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String homeAddress,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this(name, phone, email, homeAddress, null, null, null, null, null, null, null, tagged);
    }

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    // TODO: JSON property for "address" to be renamed to "homeAddressed" when integrating
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String homeAddress,
                             @JsonProperty("workAddress") Object workAddress,
                             @JsonProperty("quarantineAddress") Object quarantineAddress,
                             @JsonProperty("shnPeriod") Object shnPeriod,
                             @JsonProperty("caseNumber") Object caseNumber,
                             @JsonProperty("nextOfKinName") Object nextOfKinName,
                             @JsonProperty("nextOfKinPhone") Object nextOfKinPhone,
                             @JsonProperty("nextOfKinAddress") Object nextOfKinAddress,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
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
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        homeAddress = source.getHomeAddress().value;
        workAddress = source.getWorkAddress();
        quarantineAddress = source.getQuarantineAddress();
        shnPeriod = source.getShnPeriod();
        caseNumber = source.getCaseNumber();
        nextOfKinName = source.getNextOfKinName();
        nextOfKinPhone = source.getNextOfKinPhone();
        nextOfKinAddress = source.getNextOfKinAddress();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

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

        if (homeAddress == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(homeAddress)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelHomeAddress = new Address(homeAddress);

        final Object modelQuarantineAddress = quarantineAddress;
        final Object modelWorkAddress = workAddress;
        final Object modelShnPeriod = shnPeriod;
        final Object modelCaseNumber = caseNumber;
        final Object modelNextOfKinName = nextOfKinName;
        final Object modelNextOfKinPhone = nextOfKinPhone;
        final Object modelNextOfKinAddress = nextOfKinAddress;

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPhone, modelEmail, modelHomeAddress, modelQuarantineAddress, modelWorkAddress, modelShnPeriod, modelCaseNumber, modelNextOfKinName, modelNextOfKinPhone, modelNextOfKinAddress, modelTags);
    }

}
