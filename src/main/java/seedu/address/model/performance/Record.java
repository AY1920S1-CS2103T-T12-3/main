package seedu.address.model.performance;

/**
 * Under each event, each member will have a PerformanceEntry that contains their performance timing and the date
 * that they took
 */
public class Record {
    private String date;
    private String time;

    /**
     * Creates a Record for a member under an event.
     * @param date Date this entry was taken.
     * @param time Timing of this entry.
     */
    public Record(String date, String time) {
        this.date = date;
        this.time = time;
    }

    /**
     * Retrieves the timing of this PerformanceEntry.
     * @return Timing of this PerformanceEntry.
     */
    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
