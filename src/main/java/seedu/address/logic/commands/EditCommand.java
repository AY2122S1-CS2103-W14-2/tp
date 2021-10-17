package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOME_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.CaseNumber;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.ShnPeriod;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_CASE_NUMBER + "CASE NUMBER] "
            + "[" + PREFIX_HOME_ADDRESS + "HOME ADDRESS] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        CaseNumber updatedCaseNumber = editPersonDescriptor.getCaseNumber().orElse(personToEdit.getCaseNumber());
        Address updatedHomeAddress = editPersonDescriptor.getHomeAddress().orElse(personToEdit.getHomeAddress());
        Optional<Address> workAddress = editPersonDescriptor.getWorkAddress();
        if (workAddress.isEmpty()) {
            workAddress = personToEdit.getWorkAddress();
        }
        Optional<Address> quarantineAddress = editPersonDescriptor.getQuarantineAddress();
        if (quarantineAddress.isEmpty()) {
            quarantineAddress = personToEdit.getQuarantineAddress();
        }
        Optional<ShnPeriod> shnPeriod = editPersonDescriptor.getShnPeriod();
        if (shnPeriod.isEmpty()) {
            shnPeriod = personToEdit.getShnPeriod();
        }
        Optional<Name> nextOfKinName = editPersonDescriptor.getNextOfKinName();
        if (nextOfKinName.isEmpty()) {
            nextOfKinName = personToEdit.getNextOfKinName();
        }
        Optional<Phone> nextOfKinPhone = editPersonDescriptor.getNextOfKinPhone();
        if (nextOfKinPhone.isEmpty()) {
            nextOfKinPhone = personToEdit.getNextOfKinPhone();
        }
        Optional<Address> nextOfKinAddress = editPersonDescriptor.getNextOfKinAddress();
        if (nextOfKinAddress.isEmpty()) {
            nextOfKinAddress = personToEdit.getNextOfKinAddress();
        }
        return new Person(updatedName, updatedPhone, updatedEmail, updatedCaseNumber, updatedHomeAddress,
                workAddress, quarantineAddress, shnPeriod, nextOfKinName, nextOfKinPhone, nextOfKinAddress);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private CaseNumber caseNumber;
        private Address homeAddress;
        private Address workAddress;
        private Address quarantineAddress;
        private ShnPeriod shnPeriod;
        private Name nextOfKinName;
        private Phone nextOfKinPhone;
        private Address nextOfKinAddress;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setCaseNumber(toCopy.caseNumber);
            setHomeAddress(toCopy.homeAddress);
            setWorkAddress(toCopy.workAddress);
            setQuarantineAddress(toCopy.quarantineAddress);
            setShnPeriod(toCopy.shnPeriod);
            setNextOfKinName(toCopy.nextOfKinName);
            setNextOfKinPhone(toCopy.nextOfKinPhone);
            setNextOfKinAddress(toCopy.nextOfKinAddress);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, caseNumber, homeAddress,
                    workAddress, quarantineAddress, shnPeriod, nextOfKinName, nextOfKinPhone, nextOfKinAddress);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setCaseNumber(CaseNumber caseNumber) {
            this.caseNumber = caseNumber;
        }

        public Optional<CaseNumber> getCaseNumber() {
            return Optional.ofNullable(caseNumber);
        }

        public void setHomeAddress(Address homeAddress) {
            this.homeAddress = homeAddress;
        }

        public Optional<Address> getHomeAddress() {
            return Optional.ofNullable(homeAddress);
        }

        public void setWorkAddress(Address workAddress) {
            this.workAddress = workAddress;
        }

        public Optional<Address> getWorkAddress() {
            return Optional.ofNullable(workAddress);
        }

        public void setQuarantineAddress(Address quarantineAddress) {
            this.quarantineAddress = quarantineAddress;
        }

        public Optional<Address> getQuarantineAddress() {
            return Optional.ofNullable(quarantineAddress);
        }

        public void setShnPeriod(ShnPeriod shnPeriod) {
            this.shnPeriod = shnPeriod;
        }

        public Optional<ShnPeriod> getShnPeriod() {
            return Optional.ofNullable(shnPeriod);
        }

        public void setNextOfKinName(Name nextOfKinName) {
            this.nextOfKinName = nextOfKinName;
        }

        public Optional<Name> getNextOfKinName() {
            return Optional.ofNullable(nextOfKinName);
        }

        public void setNextOfKinPhone(Phone nextOfKinPhone) {
            this.nextOfKinPhone = nextOfKinPhone;
        }

        public Optional<Phone> getNextOfKinPhone() {
            return Optional.ofNullable(nextOfKinPhone);
        }

        public void setNextOfKinAddress(Address nextOfKinAddress) {
            this.nextOfKinAddress = nextOfKinAddress;
        }

        public Optional<Address> getNextOfKinAddress() {
            return Optional.ofNullable(nextOfKinAddress);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getCaseNumber().equals(e.getCaseNumber())
                    && getHomeAddress().equals(e.getHomeAddress())
                    && getWorkAddress().equals(e.getWorkAddress())
                    && getQuarantineAddress().equals(e.getQuarantineAddress())
                    && getShnPeriod().equals(e.getShnPeriod())
                    && getNextOfKinName().equals(e.getNextOfKinName())
                    && getNextOfKinPhone().equals(e.getNextOfKinPhone())
                    && getNextOfKinAddress().equals(e.getNextOfKinAddress());
        }
    }
}
