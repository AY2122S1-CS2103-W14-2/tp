---
layout: page
title: User Guide
---

Track2Gather is a **desktop app for contact tracing personnel at the [Ministry of Health (MOH)](https://www.moh.gov.sg/), optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `track2gather.jar` from [here](https://github.com/AY2122S1-CS2103-W14-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Track2Gather.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:
    
    * **`help`** : Shows a message explaining how to access the help page.

    *  **`add`** : Adds a person to the address book.

    *  **`sort`** : Sorts the contacts list, then displays the sorted contacts list.

    *  **`find`** : Find a person by name.

    *  **`delete`** : Deletes a person at the specified index.

    * **`clear`** : Deletes all contacts.

    * **`exit`** : Exits the app.

Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help` [coming soon]

Shows a message explaining how to access the help page.

Format: `help`

### Adding a person: `add`

Adds a person to the contacts list.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL cn/CASE_NUMBER ha/HOME_ADDRESS [wa/WORK_ADDRESS] [qa/QUARANTINE_ADDRESS] [sh/SHN_PERIOD] [kn/NEXT_OF_KIN_NAME] [kp/NEXT_OF_KIN_PHONE] [ka/NEXT_OF_KIN_ADDRESS]`

Examples:
- `add n/Alex p/98765432 e/alex@email.com cn/600204 ha/123 Orchard Road #01-100 800123`
- `add n/Jane p/12345678 e/jane@email.com cn/600204 ha/123 Changi Road #01-100 700123 wa/50 Jurong Road 120050 qa/12 Harbourfront Ring 123012 sh/2021-01-01 2021-01-14 kn/Peter kp/90011234 ka/73 Yishun Drive #10-301 310073`

### Sorting contacts by name: `sort` [coming soon]

Sorts the contacts list, then displays the sorted contacts list.

Format: `sort [-FLAG1 -FLAG2 ...]`

Use `-n` to sort by name, `-l` to sort by location.

* The flags are optional, contacts will be sorted by name as a default.
* The sort will be stable, following the given order of flags if any.

### Finding persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>

### Deleting a person : `delete` [coming soon]

Deletes the contact at the specified index.

Format: `delete INDEX`

* Deletes the contact at the specified `INDEX`.
* The index **must be a positive integer** (e.g. 1, 2, 3, ..)
* The index **must not exceed the total number of contacts** in the address book

Examples:

* `sort` followed by `delete 2` deletes the 2nd person in the contacts list when sorted by name. 
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Track2Gather data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Track2Gather data are saved as a JSON file `[JAR file location]/data/track2gather.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Track2Gather will discard all data and start with an empty data file at the next run.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

Coming soon! 

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL cn/CASE_NUMBER ha/HOME_ADDRESS [wa/WORK_ADDRESS] [qa/QUARANTINE_ADDRESS] [sh/ADD_SHN_PERIOD] [kn/NEXT_OF_KIN_NAME] [kp/NEXT_OF_KIN_PHONE] [ka/NEXT_OF_KIN_ADDRESS]`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Exit** | `exit`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Help** | `help`
**Sort** | `sort [-FLAG1 -FLAG2 ...]`
