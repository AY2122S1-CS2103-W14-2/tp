---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `Track2GatherParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `Track2GatherParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `Track2GatherParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

**Note:** Command objects which have simpler syntax, e.g. `ClearCommand`, may be created directly within the `Track2GatherParser` class without the use of a `XYZCommandParser` class.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="1000" />


The `Model` component,

* stores the contacts list data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _sorted_ and _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both contacts list data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `Track2GatherStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.Track2Gather.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedTrack2Gather`. It extends `Track2Gather` with an undo/redo history, stored internally as an `Track2GatherStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedTrack2Gather#commit()` — Saves the current Track2Gather state in its history.
* `VersionedTrack2Gather#undo()` — Restores the previous Track2Gather state from its history.
* `VersionedTrack2Gather#redo()` — Restores a previously undone Track2Gather state from its history.

These operations are exposed in the `Model` interface as `Model#commitTrack2Gather()`, `Model#undoTrack2Gather()` and `Model#redoTrack2Gather()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedTrack2Gather` will be initialized with the initial Track2Gather state, and the `currentStatePointer` pointing to that single Track2Gather state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the contacts list. The `delete` command calls `Model#commitTrack2Gather()`, causing the modified state of the contacts list after the `delete 5` command executes to be saved in the `Track2GatherStateList`, and the `currentStatePointer` is shifted to the newly inserted Track2Gather state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitTrack2Gather()`, causing another modified Track2Gather state to be saved into the `Track2GatherStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitTrack2Gather()`, so the Track2Gather state will not be saved into the `Track2GatherStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoTrack2Gather()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous Track2Gather state, and restores Track2Gather to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial Track2Gather state, then there are no previous Track2Gather states to restore. The `undo` command uses `Model#canUndoTrack2Gather()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoTrack2Gather()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores Track2Gather to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `Track2GatherStateList.size() - 1`, pointing to the latest Track2Gather state, then there are no undone Track2Gather states to restore. The `redo` command uses `Model#canRedoTrack2Gather()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the contacts list, such as `list`, will usually not call `Model#commitTrack2Gather()`, `Model#undoTrack2Gather()` or `Model#redoTrack2Gather()`. Thus, the `Track2GatherStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitTrack2Gather()`. Since the `currentStatePointer` is not pointing at the end of the `Track2GatherStateList`, all Track2Gather states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire contacts list.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**: contact tracing personnel at MOH

* has a need to maintain a lot of contact information
* prefer desktop apps over other types
* prefer CLI over GUI


**Value proposition**: The app will manage up to a few thousand contacts, providing basic features for contact tracing personnel to organise and search through them by personal information (limited to English names and Singaporean contact numbers and addresses), case contacts and quarantine period information.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                  | I want to …​                   | So that I can…​                                                         |
| -------- | ---------------------------------------- | ------------------------------ | ----------------------------------------------------------------------- |
| `* * *`  | new user                                 | see usage instructions         | refer to instructions when I forget how to use the app                  |
| `* * *`  | user                                     | update contact's contact details and SHN periods without re-adding that contact  | avoid re-entering existing data |
| `* * *`  | user                                     | find person(s) by name, phone number, case number, SHN start date or SHN end date          | locate details of person(s) containing keyword(s) in a specified field without having to go through the entire list  |
| `* * *`  | user                                     | save my contacts               | keep track of their details between sessions                            |
| `* *`    | experienced user                         | directly adjust my save files  | bypass the CLI for simple bulk tasks                                    |
| `* * *`  | user                                     | add my contact’s next-of-kin’s information | contact the patient’s next-of-kin should an emergency arise |
| `* *`    | user                                     | quickly batch-remove contacts whose SHN has been completed | clear outdated contacts                     |
| `* *`    | user                                     | delete multiple contacts at once | more easily clean my contacts                                         |
| `* *`    | user                                     | batch-update SHN periods       | extend or shorten the SHN end dates according to government mandates    |
| `* *`    | user                                     | sort contacts by fields        | more easily browse through the contacts                                 |
| `* *`    | user                                     | track call statuses            | enforce the contacts' SHN                                               |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Track2Gather` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Access help page**

**MSS:**

1.  User requests for help.
2.  Track2Gather shows a message with a link to the user guide.
3.  User accesses the user guide.
4.  User closes the message.

    Use case ends.

**Extensions**

* 2a. User closes the message.
    
    Use case ends.


**Use case: UC02 - Edit details of an existing contact**

**MSS:**

1.  User requests to list persons.
2.  Track2Gather shows a list of persons.
3.  User requests to edit the details of a specific person in the list.
4.  Track2Gather edits the person.

    Use case ends.

**Extensions**

* 2a. The list is empty.
    
    Use case ends.
* 3a. The given index is invalid.
  * 3a1. Track2Gather shows an error message.
      
      Use case resumes at step 2.
* 3b. The given fields to edit are invalid.
  * 3b1. Track2Gather shows an error message.
      
      Use case resumes at step 2.


**Use case: UC03 - Find contacts by name, phone number, case number, SHN start date or SHN end date**

**MSS:**

1. User requests to list persons.
2. Track2Gather shows a list of persons.
3. User requests to find person(s) containing one or more keywords in a specific field.
4. Track2Gather shows the person(s) containing keyword(s) in the field.

    Use case ends.

**Extensions**

* 2a. The list is empty.

    Use case ends.
* 3a. The given user input is invalid.
  * 3a1. Track2Gather shows the correct format for sorting.

    Use case resumes at step 2.
* 3b. No person found.
  * 3b2. Track2Gather tells the user that no person is found.

    Use case resumes at step 2.
  
**Use case: UC04 - Add a new contact**

**MSS:**

1. User chooses to add a new contact.
2. Track2Gather requests for details of contact.
3. User enters the requested details.
4. Track2Gather creates and shows the new contact with given details.

    Use case ends.

**Extensions**

* 3a. User details were given in the wrong format.
    * 3a1. Track2Gather shows the correct format for adding contacts.  
      Use case resumes from step 2.

**Use case: UC05 - Delete person(s)**

**MSS:**

1. User requests to list persons.
2. Track2Gather shows a list of persons.
3. User identifies specific person(s) to delete from the list.
4. Track2Gather deletes the person(s).

    Use case ends.

**Extensions**

* 2a. The list is empty.
    
    Use case ends.
* 3a. The given index(s) is invalid or is in the wrong format.
  * 3a1. Track2Gather shows the correct format for deleting.
        
    Use case resumes from step 2.

**Use case: UC06 - Clear persons with completed SHN periods**

**MSS:**

1. User requests to list persons.
2. Track2Gather shows a list of persons.
3. User chooses to clear persons.
4. Track2Gather clears all persons with completed SHN periods.

    Use case ends.

**Extensions**

* 2a. The list is empty.
    
    Use case ends.
* 3a. There are no persons with completed SHN periods.

  Use case ends.

**Use case: UC07 - Batch-update SHN periods**

**MSS:**

1. User requests to list persons.
2. Track2Gather shows a list of persons.
3. User chooses to batch-update the SHN periods.
4. Track2Gather requests for the required user input to process the update.
5. User inputs the parameters.
6. Track2Gather updates all person's SHN period according to the input.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.
* 5a. The given user input is invalid. 
  * 5a1. Track2Gather shows the correct format for batch-update of SHN periods.

  Use case resumes from step 4.

**Use case: UC08 - Sort contacts by name and case number**

**MSS:**

1. User requests to list persons.
2. Track2Gather shows a list of persons.
3. User identifies fields to be sorted and their respective sort orders.
4. Track2Gather displays the new sorted list.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.
* 3a. The given user input is invalid.
  * 3a1. Track2Gather shows the correct format for sorting contacts.

  Use case resumes from step 2.

**Use case: UC09 - Enforce SHN**

**MSS:**

1.  User requests to start a new SHN enforcement session.
2.	Track2Gather updates all persons as non-called in the new session.
3.  User requests to update call statuses for specified person(s).
4.	Track2Gather updates the list to remove all successfully called persons.

Use case ends.

**Extensions**

* 3a. The given user input is invalid.
  * 3a1. Track2Gather shows the correct format for updating call statuses.

  Use case resumes from step 2.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.


*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Graphical User Interface (GUI)**: Type of user interface through which users interact with electronic devices via visual indicator representations
* **Command Line Interface (CLI)**: Type of user interface through which users interact with a system or application by typing in text (commands)
* **Stay Home Notice (SHN)**: The notice involves a stipulated period consisting of a start and end date a person would have to remain in their place of residence or dedicated facility.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

3. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   2. Test case: `delete 1`<br>
      Expected: First person is deleted from the list. Details of the deleted person shown in the status message.

   3. Test case: `delete 1 2`<br>
      Expected: First and second persons are deleted from the list. Details of the deleted persons shown in the status message.

   4. Test case: `delete 3 1 2`<br>
      Expected: First, second and third persons are deleted from the list. Details of the deleted persons shown in the status message.

   5. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   6. Other incorrect delete commands to try: `delete`, `delete x` (where x is larger than the list size), `...` <br>
      Expected: Similar to previous.

2. Deleting a person while search results are being shown
   1. Prerequisites: Find specific person(s) using the `find` command.

   2. Test case: `delete 1`<br>
      Expected: First person displayed in the search results is deleted from the application. Details of the deleted person shown in the status message.

   3. Test case: `delete 1 2`<br>
      Expected: First and second persons displayed in the search results are deleted from the application. Details of the deleted persons shown in the status message.

   4. Test case: `delete 3 1 2`<br>
      Expected: First, second and third persons displayed in the search results are deleted from the application. Details of the deleted persons shown in the status message.

   5. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   6. Other incorrect delete commands to try: `delete`, `delete x` (where x is larger than the list size), `...` <br>
      Expected: Similar to previous.

2. _{ more test cases …​ }_

### Editing a Person

1. Editing a person while all persons are being shown
   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   2. Test case: `edit 1 n/John Doe`<br>
      Expected: First person in the list has their name edited to `John Doe`. Details of the edited person shown in the
      status message.

   3. Test case: `edit 1 n/John Doe cn/12`<br>
      Expected: First person in the list has their name edited to `John Doe` and their case number edited to `12`.
      Details of the edited person shown in the status message.

   4. Test case: `edit 1`<br>
      Expected: No persons' contact details will be edited. Error details shown in the status message. Status bar
      remains the same.

   5. Test case: `edit 1 INVALID_PREFIX/EXAMPLE`<br>
      Expected: No persons' contact details will be edited. Error details shown in the status message. Status bar
      remains the same.

   6. Test case: `edit 1 VALID_PREFIX/INVALID_INPUT`<br>
      Expected: No persons' contact details will be edited. Error details shown in the status message. Status bar
      remains the same.

   7. _{ more test cases …​ }_

### Clearing person(s) with completed SHN periods

1. Clearing person(s) while all persons are being shown
   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   2. Test case: `clear`<br>
      Expected: All persons with completed SHN periods are deleted from the list. Success message is shown.
    
   3. Test case: `clear x` (where x is any character)<br>
      Expected: Similar to previous. All trailing characters or whitespaces are ignored.

2. _{ more test cases …​ }_

### Batch-updating SHN periods

1. Batch-updating SHN periods while all persons are being shown
   1. Prerequisites: List all persons using the `list` command. Multiple persons with an SHN period.

   2. Test case: `tshift 3` or `tshift +3`<br>
   Expected: All person's SHN end dates will be postponed by `3` days. Details of the batch-update will be displayed as the status message.

   3. Test case: `tshift -3`<br>
      Expected: All person's SHN end dates will be brought forwards by `3` days. Details of the batch-update will be displayed as the status message.

   4. Test case: `tshift 91`<br>
      Expected: Unable to shift beyond the limit of `90` days. No person's SHN end dates will be shifted. Error details shown in the status message.

2. _{ more test cases …​ }_

### Sorting contacts

1. Sorting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   2. Test case: `sort n/`<br>
      Expected: The list of persons is sorted by name (in ascending order by default).

   3. Test case: `sort sh/start:dsc`<br>
      Expected: The list of persons is sorted by start date of SHN Period in descending order.

   4. Test case: `sort sh/end: cn/asc`<br>
      Expected: The list of persons is sorted by end date of SHN period (in ascending order by default), then by case number in ascending order.

   5. Test case: `sort`<br>
      Expected: The list is unchanged. Error details shown in the status message. Status bar remains the same.

   6. Other incorrect sort commands to try: `sort shn/start`, `sort p/` (since `p/` is an unsupported field prefix), `...` <br>
      Expected: Similar to previous.

2. _{ more test cases …​ }_

### Enforcing SHN

1. Starting a new SHN enforcement session

   1. Test case: `session`<br>
      Expected: All persons in the list are updated to be non-called.
       
   2. Test case: `session x` (where x is any character)<br>
      Expected: Similar to previous. All trailing characters or whitespaces are ignored.

2. Updating a person's call status to successful in the current SHN enforcement session

   1. Test case: `scall 1`<br>
      Expected: First person displayed in the search results is updated as successfully called. The case number of the updated person is shown in the status message.

   2. Test case: `scall 3`<br>
      Expected: Third person displayed in the search results is updated as successfully called. The case number of the updated person is shown in the status message.
      
   3. Test case: `scall 0`<br>
      Expected: No person is updated. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect scall commands to try: `scall`, `scall x` (where x is larger than the list size), `...` <br>
      Expected: Similar to previous.

3. Updating that a failed call was made to a person in the current SHN enforcement session

   1. Test case: `fcall 1`<br>
      Expected: First person displayed in the search results is updated as unsuccessfully called. The case number of the updated person is shown in the status message.

   2. Test case: `fcall 3`<br>
      Expected: Third person displayed in the search results is updated as unsuccessfully called. The case number of the updated person is shown in the status message.

   3. Test case: `fcall 0`<br>
      Expected: No person is updated. Error details shown in the status message. Status bar remains the same.
    
   4. Other incorrect fcall commands to try: `fcall`, `fcall x` (where x is larger than the list size), `...` <br>
      Expected: Similar to previous.

4. Showing a list of all persons who have not been called in the current SHN enforcement session

   1. Test case: `schedule`<br>
      Expected: The list is updated to display only persons who have not been called in the current SHN enforcement session.

   2. Test case: `schedule x` (where x is any character)<br>
      Expected: Similar to previous. All trailing characters or whitespaces are ignored.

### Saving data

1. Dealing with missing/corrupted data files
   1. Test case: Empty JSON file.  
      Expected: Sample Track2Gather person list will be generated with sample persons' information.
   2. Test case: Corrupted JSON file.  
      Expected: Sample Track2Gather person list will be generated with sample persons' information.

2. _{ more test cases …​ }_

### Finding persons by field

1. Find person(s) while all persons are being shown
   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   2. Test case: `find n/Alex Alice`
      Expected: Only persons whose name contains `Alex` or `Alice` will be shown.
   
   3. Test case: `find p/123 234`
      Expected: Only persons whose phone number starts with `123` or `234` will be shown.
   
   4. Test case: `find cn/1 2 3`
      Expected: Only persons with case number `1` `2` or `3` will be shown.
   
   5. Test case: `find cn/-1`
      Expected: Case number is not entered in the required format. Error details will be shown in the status message.
   
   6. Test case: `find sh/start: 2021-01-01 2021-01-02`
      Expected: Only persons with SHN start date of `2021-01-01` or `2021-01-02` will be shown.
   
   7. Test case: `find sh/start: 2021/01/01`
      Expected: Date is not entered in the required format. Error details will be shown in the status message.
   
   8. Test case: `find sh/end: 2021-01-01 2021-01-02`
      Expected: Only persons with SHN end date of `2021-01-01` or `2021-01-02` will be shown.
   
   9. Test case: `find sh/end: 2021-01-01 2021/01/02`
      Expected: One of the date keywords is not entered in the required format. Error details will be shown in the status message.

2. { more test cases... }
