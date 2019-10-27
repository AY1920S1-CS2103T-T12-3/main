package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPerformance.getTypicalPerformance;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Attendance;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.feature.Feature;
import seedu.address.testutil.FeatureBuilder;

public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalPerformance(), new Attendance(),
            new UserPrefs());
    private ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getPerformance(),
            new Attendance(), new UserPrefs());

    @Test
    public void execute_validFeature_success() {
        Feature feature = new FeatureBuilder().build();
        ViewCommand viewCommand = new ViewCommand(feature);
        String expectedMessage = viewCommand.MESSAGE_SUCCESS_1;

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidFeature_failure() {
        assertThrows(IllegalArgumentException.class, (
                ) -> new FeatureBuilder().withName("test").build());
    }
}
