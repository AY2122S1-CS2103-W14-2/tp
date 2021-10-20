# Purpose
Hi! Overfitted here. I am writing this document in response to the large number of hanging dependencies created within
the first week of the project. This document aims to outline the not so obvious items that should be updated whenever
you create or modify a feature.

# Table of Contents
[Person Attributes](#attributes)
[Features](#features)

<a name="attributes"/>
## Person Attributes
Attributes are each to be represented as an object in the person object in [person library]("../src/main/java/seedu.address/model/person".)
The purpose of this is to make tests easier, apply regex validation and to provide compile-time validation in our 
commands.

When creating an attribute, you minimally require:
1. the value itself
2. Regex validation (for example, no whitespaces at the beginning or end), in case the parser breaks
   a. use `seedu.address.commons.util.AppUtil.checkArgument`, using a custom `MESSAGE_CONSTRAINTS` as the error message.
3. null check in the constructor using `java.util.Objects.requireNonNull`
4. equals
5. hashCode

Then add tests in [person test directory]("../src/main/java/seedu.address/model/person") for your new class.
You can reference CaseNumberTest.java for a minimal test, and AddressTest.java for string checks.

To integrate the attribute with the rest of the system, do the following:
1. Add your attribute prefix to [cliparser]("../src/main/java/seedu/address/logic/parser/CliSyntax.java")
2. Document your attribute prefix in AddCommand's MESSAGE_USAGE warning
3. Add parsing for your prefix in [AddCommandParser.java]("src/main/java/seedu/address/logic/parser/AddCommandParser.java")
   a. arePrefixesPresent check, otherwise will throw java.util.NoSuchElementException instead of CommandException
4. Add whitespace trimming and validation checks in [ParserUtil.java]("..//home/naws/tp/src/main/java/seedu/address/logic/parser/ParserUtil.java")
5. Add your attribute to Person class [Person.java]("..//home/naws/tp/src/main/java/seedu/address/model/person/Person.java"),
   a. make sure that the Person constructor in the returned command works correctly.
   b. add attribute to `equals` and `toString` function
   c. add attribute getter
6. Add your attribute to the [Person test]("../src/test/java/seedu/address/model/person/PersonTest.java")
7. Add your attribute to JsonAdaptedPerson to allow for storage
8. Add your attribute to [JsonAdaptedPersonTest]("../src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java")
9. Add sample attributes to AMY and BOB test data in CommandTestUtil.java + valid prefixed (desc) command for attribute
   a. Amy for addTest
   b. Bob for editTest
10. Add sample attributes to PersonBuilder
    a. class property
    b. with<Attribute> method
    c. build() call to constructor
11. Add sample attributes to TypicalPersons.java
12. Add your attribute to `execute_storageThrowsIoException_throwsCommandException` test in LogicManagerTest.java
13. Add your attribute to AddCommandParserTest.java
    a. Add valid prefixed command (desc constant) to existing tests
    b. Add your own test using VALID_<ATTRIBUTE>_BOB instead of DESC command
14. Add your attribute to PersonUtil.java
15. Add your attribute to test data json files
    


### MINIMAL ACCEPTANCE
FOR ASSIGNEES AND PR MAKERS: Do not merge the PR unless it has the following passing tests:
1. Attribute tests from [person test directory]("../src/main/java/seedu.address/model/person")
2. Regex parsing and assertions, with a valid MESSAGE_USAGE warning
3. AddCommandParserTest.java prefix-less test
4. AddCommandParserTest.java invalid value test

<a name="features"/>
## Feature standardisation
We decided to standardise the app as having a single-view. As such, all functionality should operate within the 
constraints as follows:
1. All functions need to be CLI-only
2. To select which person is to be acted upon, we should use the index of the person in the list
3. Persons relevant to the view should be filtered by applying a predicate on an attribute of Person attribute class.

## Implementing the feature
### Filtering view
1. View filters take the form of predicates which should be created in 
   [Model.java]("../src/main/java/seedu/address/model/Model.java")
2. Refer to [List Command]("../src/main/java/seedu/address/logic/commands/ListCommand.java") for how to implement
   the filter
   
### Adding the command
1. 
