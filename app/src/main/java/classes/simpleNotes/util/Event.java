package classes.simpleNotes.util;

/**
 * Empty event for using in reactive streams as an event signal.
 */
public class Event {
    /**
     * @return new event.
     */
    public static Event event() {
        return new Event();
    }

    private Event() {
    }
}
