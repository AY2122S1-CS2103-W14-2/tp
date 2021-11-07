--- 
layout: page
title: Jennifer's Project Portfolio Page
---

### Project: Track2Gather

Track2Gather v1.4 is a desktop application that will manage up to a few thousand contacts, providing basic features for
contact tracing personnel to organise and search through them via personal information, case numbers, and
Stay-Home-Notice periods. The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to find by case number, phone number, SHN start date and SHN end date,
  in addition to name.
  * What it does: allows users to find by exactly ONE field (either name, case number, phone number, SHN start date,
    or SHN end date) at a time.
  * Justification: this feature allows user to find by a wider range of person fields, instead of just name.
  * The model classes implemented for this feature are `PhoneStartsWithKeywordsPredicate`,
    `CaseNumberEqualsKeywordsPredicate`, `ShnPeriodStartEqualsKeywordsPredicate` and `ShnPeriodEndEqualsKeywordsPredicate`.
  * Additional note: The behaviour of `find` differs based on the field specified by the user. Restrictions on
    user input and more specific usage details can be found in the User Guide.


* **New Feature**: Implemented tests for enforcement mode commands (`session`, `schedule`, `scall`, `fcall`)
  and their parsers.
  * What it does: increase code coverage and allows detection of potential bugs.
  * Justification: as these features are new, tests were written to detect possible bugs.


* **New Feature**: Enhanced Track2Gather to allow addition of optional next-of-kin information (name, address
  and phone number).
  * What it does: allows users to add a person's next-of-kin information, if available.
  * Justification: next-of-kin information serves as a form of emergency contact. It is optional since not everyone
    has a next-of-kin.


* **Code contributed**:
  [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=jennibearduit&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=jennibearduit&tabRepo=AY2122S1-CS2103-W14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


{::nomarkdown}<div style="page-break-after: always;"></div>{:/}


* **Project management**:
  * Resolved issues
  * Reviewed and merged PRs


* **Enhancements to existing features**:
  * Implemented find by case number, phone number, SHN start date and SHN end date, in addition to name.
    (Pull request [\#86](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/86)
    and [\#130](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/130))
  * Wrote tests for the following:
    * `FindCommand`, `FindCommandParser` and all the predicates
      (Pull request [\#86](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/86))
    * Enforcement mode commands (`session`, `schedule`, `scall`, `fcall`) and their parsers
      (Pull request[\#225](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/225))
  * Enhanced Track2Gather to allow addition of next-of-kin information to persons
  (Pull request [\#58](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/58))


* **Documentation**:
  * User Guide:
    * Updated documentation for `find` command
      (Pull request [\#119](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/119))
    * Reviewed and gave suggestions to pull request
      [\#219](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/219) and
      [\#229](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/229),
      which addressed UG bugs detected during PE-D
  * Developer Guide:
    * Drafted documentation for `add`, `list`, `delete` and `clear`
    * Added implementation details of `find` command
      (Pull request [\#119](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/119))


* **Community**:
  * Pull requests reviewed:
    [\#22](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/22)
    [\#26](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/26)
    [\#42](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/42)
    [\#48](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/48)
    [\#68](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/68)
    [\#88](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/88)
    [\#91](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/91)
    [\#100](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/100)
    [\#105](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/105)
    [\#115](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/115)
    [\#123](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/123)
    [\#127](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/127)
    [\#210](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/210)
    [\#218](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/218)
    [\#219](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/219)
    [\#229](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/229)
  * Reported bugs and suggestions for other teams in the class:
    [\#1](https://github.com/AY2122S1-CS2103T-T11-3/tp/issues/165)
    [\#2](https://github.com/AY2122S1-CS2103T-T11-3/tp/issues/164)
    [\#3](https://github.com/AY2122S1-CS2103T-T11-3/tp/issues/163)
    [\#4](https://github.com/AY2122S1-CS2103T-T11-3/tp/issues/162)
    [\#5](https://github.com/AY2122S1-CS2103T-T11-3/tp/issues/161)
    [\#6](https://github.com/AY2122S1-CS2103T-T11-3/tp/issues/160)
    [\#7](https://github.com/AY2122S1-CS2103T-T11-3/tp/issues/159)
    [\#8](https://github.com/AY2122S1-CS2103T-T11-3/tp/issues/158)
    [\#9](https://github.com/AY2122S1-CS2103T-T11-3/tp/issues/157)
  
