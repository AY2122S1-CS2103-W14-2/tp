package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

public class PersonCardTest {
    @Test
    public void ensureHidden() {
        // Ensure Nodes are hidden when the field is not present
        com.sun.javafx.application.PlatformImpl.startup(() -> {
            PersonCard personCard = new PersonCard(ALICE, 1);
            assertFalse(personCard.getWorkAddressHBox().isVisible());
            assertFalse(personCard.getQuarantineAddressHBox().isVisible());
            assertFalse(personCard.getShnPeriodHBox().isVisible());
            assertFalse(personCard.getNextOfKinNameHBox().isVisible());
            assertFalse(personCard.getNextOfKinPhoneHBox().isVisible());
            assertFalse(personCard.getNextOfKinAddressHBox().isVisible());
            assertFalse(personCard.getNextOfKinBlock().isVisible());
        });
    }
}
