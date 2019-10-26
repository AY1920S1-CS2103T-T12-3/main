package seedu.address.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.model.person.Person;
import seedu.address.model.training.Training;
/**
 * Represents the attendance of a person
 * Guarantees: a static list of trainings
 */
public class Attendance {

    private List<Training> trainings;

    public Attendance() {
        this.trainings = new ArrayList<>();
    }

    public Attendance(List<Training> trainings) {
        this.trainings = trainings;
    }
    public void addTraining(Training training) {
        this.trainings.add(training);
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    /**
     * Returns the attendance rate of a given person name
     */
    public String viewPersonAttendance(Person person) {
        int attended = 0;
        int total = 0;
        double result;
        for (Training training: trainings) {
            if (training.hasPerson(person)) {
                if(training.hasPersonAttended(person)) {
                    attended++;
                }
                total++;
            }
        }
        result = ((double) attended / total);
        return String.format("%d/%d (%.2f%%)", attended, total, result * 100);
    }
    /**
     * Prints out the list of athlete with their attendance in the given date
     */
    public void selectTraining(String date) {
        for (Training training: trainings) {
            if (date.equals(training.getDate())) {
                HashMap<Person, Boolean> attendanceOfTraining = training.getTrainingAttendance();
                for (Map.Entry<Person, Boolean> set : attendanceOfTraining.entrySet()) {
                    Person name = set.getKey();
                    Boolean hasAttended = set.getValue();
                    System.out.println(name + " : " + hasAttended);
                }
                break;
            }
        }
    }
    /**
     * Returns a list of person who have attended the training at the given date
     */
    public List<Person> getAttended(String date) {
        List<Person> listOfAttended = new ArrayList<>();
        for (Training training: trainings) {
            if (date.equals(training.getDate())) {
                listOfAttended = training.getAttended();
                break;
            }
        }
        return listOfAttended;
    }
}
