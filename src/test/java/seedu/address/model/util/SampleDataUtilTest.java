package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;

public class SampleDataUtilTest {
    @Test
    public void nonZeroElementArrayCreated() {
        // The sample data should not be created with 0 Persons
        assertTrue(SampleDataUtil.getSamplePersons().length > 0);
    }

    @Test
    public void nonNullPersonArray() {
        // The sample data should only contain Person Objects
        Person[] persons = SampleDataUtil.getSamplePersons();
        for (Person person : persons) {
            assertNotNull(person);
        }
    }
}
