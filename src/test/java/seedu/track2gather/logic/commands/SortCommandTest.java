package seedu.track2gather.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.track2gather.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.track2gather.logic.commands.SortCommand.COMPARATOR_PERSON_CASE_NUMBER;
import static seedu.track2gather.logic.commands.SortCommand.COMPARATOR_PERSON_NAME;
import static seedu.track2gather.logic.commands.SortCommand.COMPARATOR_PERSON_SHN_PERIOD_END;
import static seedu.track2gather.logic.commands.SortCommand.COMPARATOR_PERSON_SHN_PERIOD_START;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_SHN_PERIOD_END;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_SHN_PERIOD_START;
import static seedu.track2gather.testutil.TypicalPersons.getTypicalTrack2Gather;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.track2gather.logic.commands.SortCommand.Direction;
import seedu.track2gather.logic.parser.Prefix;
import seedu.track2gather.model.Model;
import seedu.track2gather.model.ModelManager;
import seedu.track2gather.model.UserPrefs;
import seedu.track2gather.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code SortCommand}.
 */
public class SortCommandTest {

    private Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());

    @Test
    public void execute_emptyTrack2Gather_success() {
        List<Prefix> prefixes = List.of(PREFIX_NAME);
        List<Direction> directions = List.of(Direction.ASCENDING);
        SortCommand sortCommand = new SortCommand(prefixes, directions);

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS,
                PREFIX_NAME.toString() + Direction.ASCENDING);

        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_singlePrefix_success() {
        // name, ascending
        List<Prefix> prefixes = List.of(PREFIX_NAME);
        List<Direction> directions = List.of(Direction.ASCENDING);
        Comparator<Person> comparator = COMPARATOR_PERSON_NAME;

        SortCommand sortCommand = new SortCommand(prefixes, directions);
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS,
                PREFIX_NAME.toString() + Direction.ASCENDING);
        Model expectedModel = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        // case number, ascending
        prefixes = List.of(PREFIX_CASE_NUMBER);
        directions = List.of(Direction.ASCENDING);
        comparator = COMPARATOR_PERSON_CASE_NUMBER;

        sortCommand = new SortCommand(prefixes, directions);
        expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS,
                PREFIX_CASE_NUMBER.toString() + Direction.ASCENDING);
        expectedModel = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        // shn period start date, ascending
        prefixes = List.of(PREFIX_SHN_PERIOD_START);
        directions = List.of(Direction.ASCENDING);
        comparator = COMPARATOR_PERSON_SHN_PERIOD_START;

        sortCommand = new SortCommand(prefixes, directions);
        expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS,
                PREFIX_SHN_PERIOD_START.toString() + Direction.ASCENDING);
        expectedModel = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        // shn period end date, ascending
        prefixes = List.of(PREFIX_SHN_PERIOD_END);
        directions = List.of(Direction.ASCENDING);
        comparator = COMPARATOR_PERSON_SHN_PERIOD_END;

        sortCommand = new SortCommand(prefixes, directions);
        expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS,
                PREFIX_SHN_PERIOD_END.toString() + Direction.ASCENDING);
        expectedModel = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        // name, descending
        prefixes = List.of(PREFIX_NAME);
        directions = List.of(Direction.DESCENDING);
        comparator = COMPARATOR_PERSON_NAME.reversed();

        sortCommand = new SortCommand(prefixes, directions);
        expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, PREFIX_NAME.toString() + Direction.DESCENDING);
        expectedModel = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        // case number, descending
        prefixes = List.of(PREFIX_CASE_NUMBER);
        directions = List.of(Direction.DESCENDING);
        comparator = COMPARATOR_PERSON_CASE_NUMBER.reversed();

        sortCommand = new SortCommand(prefixes, directions);
        expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS,
                PREFIX_CASE_NUMBER.toString() + Direction.DESCENDING);
        expectedModel = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        // shn period start date, descending
        prefixes = List.of(PREFIX_SHN_PERIOD_START);
        directions = List.of(Direction.DESCENDING);
        comparator = COMPARATOR_PERSON_SHN_PERIOD_START.reversed();

        sortCommand = new SortCommand(prefixes, directions);
        expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS,
                PREFIX_SHN_PERIOD_START.toString() + Direction.DESCENDING);
        expectedModel = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        // shn period end date, descending
        prefixes = List.of(PREFIX_SHN_PERIOD_END);
        directions = List.of(Direction.DESCENDING);
        comparator = COMPARATOR_PERSON_SHN_PERIOD_END.reversed();

        sortCommand = new SortCommand(prefixes, directions);
        expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS,
                PREFIX_SHN_PERIOD_END.toString() + Direction.DESCENDING);
        expectedModel = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multiplePrefix_success() {
        // name ascending then case number ascending
        List<Prefix> prefixes = List.of(PREFIX_NAME, PREFIX_CASE_NUMBER);
        List<Direction> directions = List.of(Direction.ASCENDING, Direction.ASCENDING);
        Comparator<Person> comparator = COMPARATOR_PERSON_CASE_NUMBER.thenComparing(COMPARATOR_PERSON_NAME);

        SortCommand sortCommand = new SortCommand(prefixes, directions);
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS,
                PREFIX_NAME.toString() + Direction.ASCENDING + " " + PREFIX_CASE_NUMBER + Direction.ASCENDING);
        Model expectedModel = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        // case number ascending then name descending
        prefixes = List.of(PREFIX_CASE_NUMBER, PREFIX_NAME);
        directions = List.of(Direction.ASCENDING, Direction.DESCENDING);
        comparator = COMPARATOR_PERSON_NAME.reversed().thenComparing(COMPARATOR_PERSON_CASE_NUMBER);

        sortCommand = new SortCommand(prefixes, directions);
        expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS,
                PREFIX_CASE_NUMBER.toString() + Direction.ASCENDING + " " + PREFIX_NAME + Direction.DESCENDING);
        expectedModel = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        // case number descending then name descending
        prefixes = List.of(PREFIX_CASE_NUMBER, PREFIX_NAME);
        directions = List.of(Direction.DESCENDING, Direction.DESCENDING);
        comparator = COMPARATOR_PERSON_NAME.reversed().thenComparing(COMPARATOR_PERSON_CASE_NUMBER.reversed());

        sortCommand = new SortCommand(prefixes, directions);
        expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS,
                PREFIX_CASE_NUMBER.toString() + Direction.DESCENDING + " " + PREFIX_NAME + Direction.DESCENDING);
        expectedModel = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        List<Prefix> firstPrefixes = List.of(PREFIX_NAME, PREFIX_CASE_NUMBER);
        List<Prefix> secondPrefixes = List.of(PREFIX_CASE_NUMBER, PREFIX_NAME);
        List<Direction> firstDirections = List.of(Direction.ASCENDING, Direction.ASCENDING);
        List<Direction> secondDirections = List.of(Direction.ASCENDING, Direction.DESCENDING);

        SortCommand sortFirstCommand = new SortCommand(firstPrefixes, firstDirections);
        SortCommand sortSecondCommand = new SortCommand(secondPrefixes, firstDirections);
        SortCommand sortThirdCommand = new SortCommand(firstPrefixes, secondDirections);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortCommand sortFirstCommandCopy = new SortCommand(firstPrefixes, firstDirections);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different prefixes -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));

        // different directions -> returns false
        assertFalse(sortFirstCommand.equals(sortThirdCommand));
    }

}
