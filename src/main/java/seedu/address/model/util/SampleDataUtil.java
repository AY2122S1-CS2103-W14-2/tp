package seedu.address.model.util;

import java.util.Optional;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.CaseNumber;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.ShnPeriod;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new CaseNumber("1"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    Optional.of(new Address("70 Pasir Panjang Rd, #03-71")),
                    Optional.of(new Address("Marina Bay Sands, 10 Bayfront Avenue")),
                    Optional.of(new ShnPeriod("2021-10-11 => 2021-10-24")),
                    Optional.of(new Name("Alexander Yeoh")), Optional.of(new Phone("92347283")),
                    Optional.of(new Address("Blk 30 Geylang Street 29, #06-40"))),
            new Person(new Name("Bernice Teo"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new CaseNumber("2"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    Optional.empty(), Optional.empty(),
                    Optional.of(new ShnPeriod("2021-10-02 => 2021-10-07")),
                    Optional.empty(), Optional.empty(), Optional.empty()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new CaseNumber("3"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), Optional.empty(),
                    Optional.of(new Address("NUS Prince George's Park Residences")),
                    Optional.of(new ShnPeriod("2021-09-29 => 2021-10-05")),
                    Optional.of(new Name("Christopher Oliveiro")), Optional.of(new Phone("98290023")),
                    Optional.of(new Address("Blk 26 Ang Mo Kio Street 74, #06-23"))),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new CaseNumber("4"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    Optional.empty(), Optional.empty(), Optional.empty(),
                    Optional.empty(), Optional.of(new Phone("83546783")), Optional.empty()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new CaseNumber("5"), new Address("Blk 47 Tampines Street 20, #17-35")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new CaseNumber("6"), new Address("Blk 45 Aljunied Street 85, #11-31")),
            new Person(new Name("Jane Ang"), new Phone("95958462"), new Email("jane@email.com"),
                    new CaseNumber("600204"), new Address("123 Changi Road #01-100 700123"),
                    Optional.of(new Address("50 Jurong Road 120050")),
                    Optional.of(new Address("12 Harbourfront Ring 123012")),
                    Optional.of(new ShnPeriod("2021-01-01 => 2021-01-14")),
                    Optional.of(new Name("Peter Ang")), Optional.of(new Phone("90011234")),
                    Optional.of(new Address("73 Yishun Drive #10-301 310073")))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

}
