package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.Model;
import seedu.address.model.date.AthletickDate;
import seedu.address.model.feature.Feature;
import seedu.address.model.person.Person;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;
    private Feature feature;
    private Person person;
    private AthletickDate date;
    private Model model;
    private String eventName;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code
     * featureToDisplay}, and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Feature featureToDisplay) {
        this(feedbackToUser, false, false);
        this.feature = featureToDisplay;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code
     * featureToDisplay} and {@code model}, and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Feature featureToDisplay, Model model) {
        this(feedbackToUser, false, false);
        this.feature = featureToDisplay;
        this.model = model;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code
     * featureToDisplay}, and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Person selectedPerson) {
        this(feedbackToUser, false, false);
        this.person = selectedPerson;
    }

    public CommandResult(String feedbackToUser, AthletickDate date, Model model) {
        this(feedbackToUser, false, false);
        this.date = date;
        this.model = model;
    }

    public CommandResult(String feedbackToUser, AthletickDate date, Model model, String eventName) {
        this(feedbackToUser, false, false);
        this.date = date;
        this.model = model;
        this.eventName = eventName;
    }

    public Feature getFeature() {
        return feature;
    }

    public Person getPerson() {
        return person;
    }

    public AthletickDate getDate() {
        return date;
    }

    public Model getModel() {
        return model;
    }

    public String getEventName() {
        return eventName;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }



    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
