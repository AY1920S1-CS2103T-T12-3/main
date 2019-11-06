package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.date.AthletickDate;
import seedu.address.model.performance.CalendarCompatibleRecord;
import seedu.address.model.performance.Event;
import seedu.address.model.performance.Record;
import seedu.address.model.person.Person;
import seedu.address.model.training.AttendanceEntry;
import seedu.address.model.training.Training;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();
    ReadOnlyAddressBook getAddressBookDeepCopy();
    void undo();
    void redo();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    Person selectPerson();

    void storePerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Reorders the address book in alphabetical order according to person's name.
     */
    void sortAddressBookByName();

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Adds {@code training} to the Attendance class.
     */
    void addTraining(Training training);

    /**
     * Replaces all occurences of person at {@code target} with {@code editedPerson} in training records.
     */
    void editPersonTrainingRecords(Person target, Person editedPerson);

    /**
     * Removes training on {@code date}
     */
    void deleteTrainingOnDate(AthletickDate date);

    /**
     * Gets a list of AttendanceEntry on {@code date}, where each entry indicates whether a person was present.
     * @param date Date of training.
     * @return List of AttendanceEntry, where each entry indicates whether a person was present for training on date.
     */
    List<AttendanceEntry> getTrainingAttendanceListOnDate(AthletickDate date);

    /**
     * Returns a list of AttendanceRateEntry, where each entry indicates the attendance rate of a person.
     */
    List<AttendanceRateEntry> getAttendanceRateOfAll();

    /**
     * Returns the Attendance.
     */
    Attendance getAttendance();

    /**
     * Resets all data in Attendance.
     */
    void resetAttendance();
    /**
     * Checks with Attendance if there was a Training on {@code date}.
     * @param date Date of training.
     * @return Boolean indicating if there was a training on {@code date}.
     */
    boolean hasTrainingOnDate(AthletickDate date);

    /**
     * Replaces performance data with the data in {@code performance}.
     */
    void setPerformance(ReadOnlyPerformance performance);

    void addEvent(Event event);

    boolean hasEvent(Event event);

    Event getEvent(String eventName);

    /**
     * Deletes the given event.
     * The event must exist in performance.
     */
    void deleteEvent(Event target);

    ReadOnlyPerformance getPerformance();

    void addRecord(String eventName, Person person, Record record);

    void deleteRecord(String eventName, Person person, AthletickDate date);

    HashMap<Event, List<CalendarCompatibleRecord>> getCalendarCompatiblePerformance(AthletickDate date);

    boolean hasPerformanceOn(AthletickDate date);

    ArrayList<Event> getAthleteEvents(Person athlete);

}
