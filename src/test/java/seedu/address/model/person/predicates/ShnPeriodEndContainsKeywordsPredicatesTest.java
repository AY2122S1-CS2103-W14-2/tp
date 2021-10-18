package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class ShnPeriodEndContainsKeywordsPredicatesTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("2000-01-01");
        List<String> secondPredicateKeywordList = Arrays.asList("2000-01-01", "2000-01-02");

        ShnPeriodEndContainsKeywordsPredicate firstPredicate =
                new ShnPeriodEndContainsKeywordsPredicate(firstPredicateKeywordList);
        ShnPeriodEndContainsKeywordsPredicate secondPredicate =
                new ShnPeriodEndContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ShnPeriodEndContainsKeywordsPredicate firstPredicateCopy =
                new ShnPeriodEndContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_shnPeriodEndContainsKeywords_returnsTrue() {
        // One keyword
        ShnPeriodEndContainsKeywordsPredicate predicate =
                new ShnPeriodEndContainsKeywordsPredicate(Collections.singletonList("01-02"));
        assertTrue(predicate.test(new PersonBuilder().withShnPeriod("2000-01-01 => 2000-01-02").build()));

        // Multiple keywords
        predicate = new ShnPeriodEndContainsKeywordsPredicate(Arrays.asList("2000", "01-02"));
        assertTrue(predicate.test(new PersonBuilder().withShnPeriod("2000-01-01 => 2000-01-02").build()));

        // Only one matching keyword
        predicate = new ShnPeriodEndContainsKeywordsPredicate(Arrays.asList("1999", "01-02"));
        assertTrue(predicate.test(new PersonBuilder().withShnPeriod("2000-01-01 => 2000-01-02").build()));
    }

    @Test
    public void test_shnPeriodEndDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ShnPeriodEndContainsKeywordsPredicate predicate =
                new ShnPeriodEndContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withShnPeriod("2000-01-01 => 2000-01-02").build()));

        // Non-matching keyword
        predicate = new ShnPeriodEndContainsKeywordsPredicate(Arrays.asList("1999"));
        assertFalse(predicate.test(new PersonBuilder().withShnPeriod("2000-01-01 => 2000-01-02").build()));

        // Keyword matches with shn period start
        predicate = new ShnPeriodEndContainsKeywordsPredicate(Arrays.asList("2000-01-01"));
        assertFalse(predicate.test(new PersonBuilder().withShnPeriod("2000-01-01 => 2000-01-02").build()));

        // Keywords match name, phone, email, case number,  address, shn period (start), but does not match
        // shn period (end)
        predicate = new ShnPeriodEndContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com",
                "601110", "Main", "Street", "2000-01-01"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345").withEmail("alice@email.com")
                .withCaseNumber("601110").withHomeAddress("Main Street").withShnPeriod("2000-01-01 => 2000-01-02")
                .build()));
    }
}
