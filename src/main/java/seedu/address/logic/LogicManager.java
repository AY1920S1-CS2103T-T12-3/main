package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteTrainingCommand;
import seedu.address.logic.commands.TrainingCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Attendance;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.history.HistoryManager;
import seedu.address.model.performance.Event;
import seedu.address.model.person.Person;
import seedu.address.model.training.Training;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);
        if (command instanceof DeleteTrainingCommand || command instanceof TrainingCommand) {
            HistoryManager.getTrainingLists().push(model.getTrainingsDeepCopy(Attendance.getTrainings()));
        }
        HistoryManager.getCommands().push(command);
        HistoryManager.getAddressBooks().push(model.getAddressBookDeepCopy());

        try {
            storage.saveAddressBook(model.getAddressBook());
            storage.saveEvents(model.getPerformance());
            storage.saveAttendance(model.getAttendance());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Person getPerson() {
        return model.selectPerson();
    }

    @Override
    public String getPersonAttendance() {
        return model.getAttendance().getPersonAttendanceRateString(getPerson());
    }

    @Override
    public ArrayList<Event> getAthleteEvents() {
        return model.getAthleteEvents(getPerson());
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
