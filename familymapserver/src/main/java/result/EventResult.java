package result;

import model.Event;

/**
 * Created by dc1992 on 10/13/17.
 */

public class EventResult extends Result
{
    /** the event object being returned*/
    private Event event;
    /** the error response printed to the user*/
    private String errorResponse;

    /**
     * creates a EventResult object, and creates the error response
     *
     * @param event the event object
     * @param errorResponse the type of error
     */
    public EventResult(Event event, String errorResponse)
    {
        this.event = event;
        setErrorResponse(errorResponse);
        if(errorResponse.equals("No Errors"))
        {
            this.errorResponse = null;
        }
        else
        {
            this.event = null;
        }
    }

    /**
     *sets the event
     *
     * @param event event object
     */
    public void setEvent(Event event)
    {
        this.event = event;
    }

    /**
     * gets the event
     *
     * @return event
     */
    public Event getEvent()
    {
        return event;
    }

    /**
     *gets the error response message of the event result
     * @return
     */
    public String getErrorResponse()
    {
        return errorResponse;
    }

    /**
     * sets the errorResponse dependant on the error specified
     *
     * @param errorResponse the type of error
     */
    public void setErrorResponse(String errorResponse)
    {
        this.errorResponse = errorResponse;
    }
}
