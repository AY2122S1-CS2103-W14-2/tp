package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label caseNumber;
    @FXML
    private Label homeAddress;
    @FXML
    private Label workAddress;
    @FXML
    private Label quarantineAddress;
    @FXML
    private Label shnPeriod;
    @FXML
    private Label nextOfKinName;
    @FXML
    private Label nextOfKinPhone;
    @FXML
    private Label nextOfKinAddress;
    @FXML
    private FlowPane tags;
    @FXML
    private HBox phoneHBox;
    @FXML
    private HBox homeHBox;
    @FXML
    private HBox emailHBox;
    @FXML
    private HBox workAddressHBox;
    @FXML
    private HBox quarantineAddressHBox;
    @FXML
    private HBox shnPeriodHBox;
    @FXML
    private VBox nextOfKinBlock;
    @FXML
    private HBox nextOfKinNameHBox;
    @FXML
    private HBox nextOfKinPhoneHBox;
    @FXML
    private HBox nextOfKinAddressHBox;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);
        caseNumber.setText(person.getCaseNumber().value);
        homeAddress.setText(person.getHomeAddress().value);
        workAddress.setText(person.getWorkAddress().map(Object::toString).orElse(""));
        quarantineAddress.setText(person.getQuarantineAddress().map(Object::toString).orElse(""));
        shnPeriod.setText(person.getShnPeriod().map(Object::toString).orElse(""));
        nextOfKinName.setText(person.getNextOfKinName().map(Object::toString).orElse(""));
        nextOfKinPhone.setText(person.getNextOfKinPhone().map(Object::toString).orElse(""));
        nextOfKinAddress.setText(person.getNextOfKinAddress().map(Object::toString).orElse(""));
    }

    /**
     * Removes the specified {@code Node} from display.
     * @param node The {@code Node} to hide from display.
     */
    private void hideNode(Node node) {
        node.setManaged(false);
        node.setVisible(false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
