package seedu.address.ui;

import java.util.HashMap;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyPerformance;
import seedu.address.model.performance.Event;
import seedu.address.model.performance.Record;
import seedu.address.model.person.Person;

public class RecordsPanel extends UiPart<Region> {

    private static final String FXML = "RecordsPanel.fxml";
    private ReadOnlyPerformance performance;
    private Event event;

    @FXML
    private Label eventName;

    @FXML
    private VBox recordContents;

    public RecordsPanel(Model model, String eventName) {
        super(FXML);
        this.performance = model.getPerformance();
        for (Event event : performance.getPerformance()) {
            if (event.getName().equals(eventName.toLowerCase())) {
                this.event = event;
            }
        }
        this.eventName.setText(event.getName());
        populateRecords();
    }

    public void populateRecords() {
        HashMap<Person, List<Record>> hm = event.getRecords();
        hm.forEach((person, recordList) -> {
            AthleteRecords ar = new AthleteRecords(person, recordList);
            recordContents.getChildren().add(ar.getRoot());
        });
    }

}
