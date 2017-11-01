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
     * @param errorResponse the type of error
     */
    public UserEventsResult(Event[] events, String errorResponse)
    {
        setErrorResponse(errorResponse);
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

    /**
     * sets the error response dependant on the type of error specified
     *
     * @param errorResponse type of error
     */
    public void setErrorResponse(String errorResponse)
    {
        this.errorResponse = errorResponse;
    }
}
