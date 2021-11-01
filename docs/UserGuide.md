---
layout: page
title: User Guide
---

Track2Gather is a **desktop app for contact tracing personnel at the [Ministry of Health (MOH)](https://www.moh.gov.sg/), optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `track2gather.jar` from [here](https://github.com/AY2122S1-CS2103-W14-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your Track2Gather.

4. To start the app, double-click the file or enter `java -jar track2gather.jar` into the terminal in the _home folder_.

5. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

6. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`add`** : Adds a person to the persons list.

    * **`delete`** : Deletes person(s) at the specified index(s).

    * **`edit`** : Edits the details of a person at the specified index.
   
    * **`find`** : Finds person(s) by name, phone, case number, SHN start date or SHN end date.

    * **`tshift`** : Shifts all persons' SHN end dates by the specified number of days.
   
    * **`list`** : Shows a list of all persons.

    * **`sort`** : Sorts all persons in the persons list.

    * **`clear`** : Deletes all persons with SHN periods that are completed at time of command call.

    * **`session`** : Starts a new SHN enforcement session with all persons set to 'not called'.

    * **`schedule`** : Shows a list of all persons who have not been called in the current SHN enforcement session.

    * **`scall`** : Updates a person as successfully called in the current SHN enforcement session.

    * **`fcall`** : Updates that a failed call was made to a person in the current SHN enforcement session.
    
    * **`help`** : Shows a message explaining how to access the help page.

    * **`exit`** : Exits the app.

Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [wa/WORK_ADDRESS]` can be used as `n/John Doe wa/Home` or as `n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Adding a person: `add`

Adds a person to the persons list for tracking.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL cn/CASE_NUMBER ha/HOME_ADDRESS [wa/WORK_ADDRESS] [qa/QUARANTINE_ADDRESS] [sh/SHN_PERIOD] [kn/NEXT_OF_KIN_NAME] [kp/NEXT_OF_KIN_PHONE] [ka/NEXT_OF_KIN_ADDRESS]`
* There cannot be multiple persons with the same case number

Examples:
* `add n/Alex p/98765432 e/alex@email.com cn/600204 ha/123 Orchard Road #01-100 800123`
* `add n/Jane p/12345678 e/jane@email.com cn/600204 ha/123 Changi Road #01-100 700123 wa/50 Jurong Road 120050 qa/12 Harbourfront Ring 123012 sh/2021-01-01 2021-01-14 kn/Peter kp/90011234 ka/73 Yishun Drive #10-301 310073`

### Editing a person : `edit`

Edits the details of the person identified by the index number used in the displayed person list. Existing values will be overwritten by the input values.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [cn/CASE_NUMBER] [ha/HOME_ADDRESS] [wa/WORK_ADDRESS] [qa/QUARANTINE_ADDRESS] [sh/SHN_PERIOD] [kn/NEXT_OF_KIN_NAME] [kp/NEXT_OF_KIN_PHONE] [ka/NEXT_OF_KIN_ADDRESS]`
* Edits the person at the specified `INDEX`
* The index **must be a positive integer** (e.g. 1, 2, 3, ..)
* The index **must not exceed the total number of persons** in the persons list
* There cannot be multiple persons with the same case number
* At least one field to edit must be provided

Examples:
* `edit 1 n/Henry Hugh` edits the name of the first person in the list to be `Henry Hugh`

### Finding persons by a field: `find`

Finds person(s) based on the field specified by the user and displays them as a list with index numbers.

Format: `find FIELD_PREFIX KEYWORD [MORE_KEYWORDS]`
* Field must be one of the following: `n/`, `p/`, `cn/`, `sh/start:` or `sh/end:`
* For find by name (`n/`),
  * search is case-insensitive. e.g `hans` will match `Hans`
  * full words will be matched e.g. Han will not match Hans
  * the order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* For find by phone number (`p/`), phone numbers that start with the specified number will be matched e.g. `123` and `1234` will match `12345678`
* For find by case number (`cn/`),
  * search will only match if case number is equal, e.g. `123` will match `123` but will not match `1234`
  * case number must be entered in the valid format
* For find by SHN start date (`sh/start:`),
  * search will only match if SHN start date is equal, e.g. `2021-01-01` will match `2021-01-01`
  * SHN start date must be entered in valid ISO-8601 date format
* For find by SHN end date (`sh/end:`), 
  * search will only match if SHN end date is equal, e.g. `2021-01-02` will match `2021-01-02`
  * SHN end date must be entered in valid ISO-8601 date format
* Only one of the following can be searched at a time: name, phone number, case number, SHN start date and SHN end date
* Persons matching at least one keyword will be returned (i.e. `OR` search). e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find n/John` will match the following names: `john` and `John Doe` 
* `find n/alex david` will match the following names: `Alex Yeoh` and `David Li` 
* `find p/123` will match the following phone numbers: `12345678` and `12387654` 
* `find cn/1` will match the following case numbers: `111` and `111` 
* `find sh/start:2021-01-01` will match the following SHN start dates: `2021-01-01` and `2021-01-02` 
* `find sh/end:2021-01-02` will match the following SHN end dates: `2021-01-01` and `2021-01-02` 

### Finding persons by name: `tshift`

Shifts all person's SHN end dates by the specified number of days.

Format: `tshift [PLUS_MINUS_SIGN]DAYS`
* `DAYS` should be a number between `1` and `90` inclusive
* Postpones the SHN end date if the evaluated value is positive
* Brings forward the SHN end date if the value is negative
* The SHN end dates will only be brought forward up to and including a day after the person's SHN start date

Examples:
* `tshift 3` postpones all SHN end dates by 3 days. This is identical to `tshift +3`
* `tshift -3` brings forward all SHN end dates by up to 3 days

### Deleting a person : `delete`

Deletes the person(s) identified by the specified index number(s) used in the displayed persons list.

Format: `delete INDEX [MORE_INDICES]`

* Deletes the person(s) at the specified `INDEX`(s).
* The index(s) **must be a positive integer** (e.g. 1, 2, 3, ..)
* The index(s) **must not exceed the total number of persons** in the contacts list
* The index(s) **can be given in any order** (e.g. `delete 1 4 5`, `delete 5 1 4`)

Examples:
* `sort n/` followed by `delete 2` deletes the 2nd person in the persons list when sorted by name. 
* `sort cn/` followed by `delete 1 4 5` deletes the 1st, 4th and 5th persons in the persons list when sorted by case
  number.
* `find n/Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command, if any.

### Listing all persons : `list`

Shows a list of all persons.

Format: `list`

### Sorting all persons : `sort`

Sorts the persons list based on the specified field prefixes.

Format: `sort [n/DIRECTION] [cn/DIRECTION] [sh/start:DIRECTION] [sh/end:DIRECTION]`

* Sorts the persons list from the first to the last specified field prefix.
* At least one field prefix must be specified.
* Direction "asc" indicates ascending order and "dsc" indicates descending order.
* Specifying the sort direction is optional. By default, field prefixes are sorted in ascending order.

Examples:
* `sort n/` sorts the persons list by name (in ascending order by default).
* `sort sh/end:dsc` sorts the persons list by end date of SHN period in descending order.
* `sort sh/start: cn/asc` sorts the persons list by start date of SHN period (in ascending order by default), then by case number in ascending order.

### Clearing all persons : `clear`

Deletes all persons with SHN periods that are completed at time of command call from the app.

Format: `clear`

### SHN enforcement mode
SHN enforcement mode contains a special group of features that enables users to track the call statuses of persons in Track2Gather. 
The purpose of this mode is to facilitate the calling of persons to enforce their SHN. 

The intended use of this mode is to:
* create a new calling session every day (henceforth referred to as 'SHN enforcement session').
* mark successful/failed attempts to contact the persons in order to track their behaviour throughout their SHN.

In SHN enforcement mode, all people who have been called for the current SHN enforcement session will be *filtered out of the schedule and hidden from the interface.*

There are 4 features relevant to this mode: `session`, `schedule`, `scall`, and `fcall`.
* `session` and `schedule` activate SHN enforcement mode.
* `scall` and `fcall` are intended to work best in SHN enforcement mode, but can be used outside of enforcement mode.

#### How the information is reflected on the interface
Under `Call Status` for each person, there are two components:
* Call status indicator
  * Represents whether a person has been called in the current SHN enforcement session.
  * Displayed as 'Called:' followed by a red cross or green tick.
  * Red cross indicates that a person has not been called for the current SHN enforcement session.
  * Green tick indicates that a person has been called for the current SHN enforcement session, regardless of whether the call was successful.
* Non-compliance counter 
  * Represents the number of failed attempts that have been made to call the person 
  * Displayed as 'Failed: X time(s)' where X is the number of failed call attempts 
  * The number of failed call attempts recorded is cumulative over the entire period of Track2Gather usage. It does not reset to zero when a new SHN enforcement session is created.

#### Important notes
* `add`, `edit`, `find`, `tshift` and `list` commands deactivate enforcement mode.
* At any point in time, there is only 1 ongoing SHN enforcement session.
* Previous sessions cannot be accessed.

### Starting a new SHN enforcement session : `session`

Starts a new SHN enforcement session by resetting the call status indicators of all persons to red crosses.

Format: `session`

* Displays the full list of persons, in which the call status indicator for all persons will display a red cross.
* Activates SHN enforcement mode.

### Showing the current SHN enforcement session : `schedule`

Displays a filtered list of all persons who have not been called in the current SHN enforcement session.

Format: `schedule`

* Displays the list of persons who have not been called in the current SHN enforcement session i.e. those whose call status indicators are red crosses.
* Activates SHN enforcement mode.

### Updating a person as successfully called : `scall`

Updates a person as successfully called in the current SHN enforcement session.

Format: `scall INDEX`

* Updates the person at the specified `INDEX` as called.
* The index **must be a positive integer** (e.g. 1, 2, 3, ..)
* The index **must not exceed the total number of persons** in the contacts list
* If Track2Gather is in enforcement mode, the person at the specified index will be removed from the schedule and thus the display.
  * Note that the person at the specified index will have its call status indicator changed to a green tick.
* If Track2Gather is not in enforcement mode, the person at the specified index will have its call status indicator changed to a green tick.

Example when not in SHN enforcement mode:
* `scall 1` updates the first person in the list by changing the person's call status indicator to a green tick.

Example when in SHN enforcement mode:
* `scall 1` removes the first person in the list from the schedule and thus the display.
    * Note that the first person's call status indicator is also updated (as in the above example), except the person can now only be viewed outside SHN enforcement mode.

### Updating a person as unsuccessfully called : `fcall`

Updates that a failed call was made to a person in the current SHN enforcement session.

Format: `fcall INDEX`
* Updates the person at the specified `INDEX` as called, and increments the person's non-compliance counter by 1.
* The index **must be a positive integer** (e.g. 1, 2, 3, ..)
* The index **must not exceed the total number of persons** in the contacts list
* If Track2Gather is in SHN enforcement mode, the person at the specified index will be removed from the schedule and thus the display.
  * Note that the person at the specified index will have its call status indicator changed to a green tick and its non-compliance counter incremented by 1.
* If Track2Gather is not in SHN enforcement mode, the person at the specified index will have its call status indicator changed to a green tick, and non-compliance counter incremented by 1.

Example when not in SHN enforcement mode:
* `fcall 1` updates the first person in the list by:
  * changing the person's call status indicator to a green tick.
  * incrementing the person's non-compliance counter by 1.

Example when in SHN enforcement mode:
* `fcall 1` removes the first person in the list from the schedule and thus the display.
    * Note that the first person's call status indicator and non-compliance counter are also updated (as in the above example), except the person can now only be viewed outside SHN enforcement mode.
    
### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Track2Gather data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Track2Gather data are saved as JSON files `[JAR file location]/data/track2gather.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Track2Gather will discard all data and start with an empty data file at the next run.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

Coming soon! 

--------------------------------------------------------------------------------------------------------------------

## Command Summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL cn/CASE_NUMBER ha/HOME_ADDRESS [wa/WORK_ADDRESS] [qa/QUARANTINE_ADDRESS] [sh/ADD_SHN_PERIOD] [kn/NEXT_OF_KIN_NAME] [kp/NEXT_OF_KIN_PHONE] [ka/NEXT_OF_KIN_ADDRESS]`<br> e.g., `add n/Jane p/12345678 e/jane@email.com cn/600204 ha/123 Changi Road #01-100 700123 wa/50 Jurong Road 120050 qa/12 Harbourfront Ring 123012 sh/2021-01-01 2021-01-14 kn/Peter kp/90011234 ka/73 Yishun Drive #10-301 310073`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [cn/CASE_NUMBER] [ha/HOME_ADDRESS] [wa/WORK_ADDRESS] [qa/QUARANTINE_ADDRESS] [sh/ADD_SHN_PERIOD] [kn/NEXT_OF_KIN_NAME] [kp/NEXT_OF_KIN_PHONE] [ka/NEXT_OF_KIN_ADDRESS]`<br> e.g., `edit 1 n/Henry Hugh`
**Find** | `find FIELD_PREFIX KEYWORD [MORE_KEYWORDS]`<br> e.g., `find n/James Jake` `find p/123` `find cn/111` `find sh/start:2000-01-01` `find sh/end: 2000-01-02`
**TShift** | `tshift [PLUS_MINUS_SIGN]DAYS`<br> e.g., `tshift 3`
**Delete** | `delete INDEX [MORE_INDICES]`<br> e.g., `delete 3` `delete 1 4`
**List** | `list`
**Sort** | `sort [n/DIRECTION] [cn/DIRECTION] [sh/start:DIRECTION] [sh/end:DIRECTION]`<br> e.g., `sort n/` `sort sort/end:dsc` `sort sh/start: cn/asc`
**Clear** | `clear`
**Schedule** | `schedule`
**SCall** | `scall INDEX`<br> e.g., `scall 3`
**FCall** | `fcall INDEX`<br> e.g., `fcall 3`
**Session** | `session`
**Help** | `help`
**Exit** | `exit`
