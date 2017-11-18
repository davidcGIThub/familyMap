package request;

/**
 * Created by dc1992 on 10/23/17.
 */

public class EventRequest extends Request
{
    /**
     * the event ID of the event requested
     */
    private String eventID;

    /**
     * creates an eventRequest object
     */
    public EventRequest(String eventID, String authToken) {
        this.eventID = eventID;
        setAuthToken(authToken);
    }

    /**
     * gets the event ID
     *
     * @return
     */

    public String getEventID() {
        return eventID;
    }

    /**
     * sets the event ID
     *
     * @param eventID ID for the event
     */
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }


}