---
layout: page
title: User Guide
---

**HackNet** is a desktop application used for people to manage contact details of potential team members to work on a technical project or hackathon with. It has a simple Graphical User Interface (GUI) which displays details of your potential teammates, and works mainly via a Command Line Interface (GUI) through text commands.

* [Quick Start](#quick-start)
* [Features](#features)
  * Viewing help: help
  * Adding a person: add
    * Name
    * Technical Skills
    * Contact Information
    * Github Profile
    * Team
  * Filter for past teammates: filterteam
  * Add/remove contact as potential teammate: team/unteam
  * Listing all persons: list
  * Deleting a person: delete
  * Saving the data
* FAQ
* Command summary

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `HackNet.jar` from [here](https://github.com/se-edu/addressbook-level3/releases) (coming soon).

1. Copy the file to the folder you want to use as the _home folder_ for HackNet.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com g/johndoe123` : Adds a contact named `John Doe` to HackNet.

   * **`delete`**` 3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/Team]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/Team]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* For skill field, Skill name have to be followed by a underscore `_` and Skill proficiency level that ranges from 0 to 100 with 0 being a low proficiency level and 100.
  e.g. `[s/SKILLNAME_SKILLPROFICENCY]…​` as `[s/Java_90]`

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to HackNet.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL g/GITHUB_USERNAME [t/Team]…​ [s/SKILLNAME_SKILLPROFICENCY]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of teams (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com g/johndoe123`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com g/betsycoder p/1234567 t/entrepeneur s/java_80`

### Filter for past teammates : `filterteam`

Shows a list of past teammates in HackNet.

Format: `list`

### Listing all persons : `list`

Shows a list of all persons in HackNet.

Format: `list`

### Editing a person : `edit`

Edits an existing person in HackNet.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [g/GITHUB_USERNAME] [t/Team]…​ [s/SKILLNAME_SKILLPROFICENCY]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing teams, the existing teams of the person will be removed i.e adding of teams is not cumulative.
* You can remove all the person’s teams by typing `t/` without
    specifying any teams after it. Same concept applies with the skill field with prefix `s/`

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing teams.

### Locating persons by name: `find`

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
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from HackNet.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Locating persons by skills: `filter`

Finds persons that have the specified skills.

Format: `filter KEYWORD`

* The search is case-insensitive. e.g `java` will match `Java`
* Only full words will be matched e.g. `C` will not match `C#`

Examples:
* `find C` returns `Alex Yeoh`, `Bernice Yu` and `Irfan Ibrahim`
* `filter java` returns `David Li`<br>
  ![result for 'filter C'](images/filterCResult.png)

### Sorting persons by skill proficiency: `sort`

Sort persons in descending level of proficiency of specified skill.

Format: `sort SKILL`

* Only persons with the specified `SKILL` will be listed
* The search is case-insensitive. e.g `java` will match `Java`
* Only full words will be matched e.g. `C` will not match `C#`
* The list will be shown in descending level of skill proficiency

Examples:
* `sort Python` shows persons with `Python` as a skill in descending order of proficiency <br>

### Add/remove potential teammates : `team/unteam`

Format:
* `team INDEX`
* `unteam INDEX`
  * Adds/removes the person at `INDEX`as potential teammate
  * The index refers to the index number shown in the displayed person list
  * The index **must be a positive integer** 1, 2, 3, …​
  * Redundant adding/removal (e.g. adding someone who is already a potential teammate) is not allowed

Examples:
* `team 1` adds the first person as a potential teammate

![result for 'team 1'](images/teamCommandResult.png)

### Show all potential teammates : `show`

Shows the list of all persons marked as potential teammates

Format: `show`

### Clearing all entries : `clear`

Clears all entries from HackNet.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Hacknet data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HackNet data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, HackNet will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

### Future updates

Input validation for skills<br>
update skills<br>
filter by multiple skills<br>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HackNet home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL g/GITHUB_USERNAME [t/Team]…​ [s/SKILLNAME_SKILLPROFICENCY]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com g/jameshooo t/friend t/colleague s/java_70`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [g/GITHUB_USERNAME] [t/Team]…​ [s/SKILLNAME_SKILLPROFICENCY]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Make Team** | `team`, `unteam` <br> e.g., `team1`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Filter** | `filter SKILL`<br> e.g. `filter c`
**Filter Past Teammates** | `filterteam` 
**Sort** | `sort SKILL`<br> e.g., `sort Python`
**List** | `list`
**Show potential teammates** | `show`
**Help** | `help`
