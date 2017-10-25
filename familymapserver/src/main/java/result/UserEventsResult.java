package result;

import model.Event;

/**
 * Created by dc1992 on 10/13/17.
 */

public class UserEventsResult
{
    /** an array of events that that are associated with the user*/
    private Event[] events;
    /** the error response printed to the user*/
    private String errorResponse;

    /**
     * creates a UserEventsResult object, and creates the error response message
     *
     * @param events the events of the user who requested them
     * @param errorResponse the error response message
     */
    public UserEventsResult(Event[] events, String errorResponse)
    {
        this.events = events;
        this.errorResponse = errorResponse;
    }

    /**
     * sets the events
     *
     * @param events all the events of the user
     */
    public void setEvents(Event[] events)
    {
        this.events = events;
    }

    /**
     * gets all of the events of the user
     *
     * @return events
     */
    public Event[] getEvents()
    {
        return events;
    }

    /**
     * gets the error response message
     *
     * @return errorResponse
     */
    public String getErrorResponse()
    {
        return errorResponse;
    }
}
