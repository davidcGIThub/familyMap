package request;

/**
 * Created by dc1992 on 10/23/17.
 */

public class EventRequest
{
    /** the event ID of the event requested*/
    private String eventID;
    /** the authorization token for the user and session*/
    private String authToken;
    /** the username of the user making the request*/
    private String username;

    /**creates an eventRequest object*/
    public EventRequest(String eventID, String authToken,String username)
    {
        this.eventID = eventID;
        this.authToken = authToken;
        this.username = username;
    }

    /**
     * gets the event ID
     *
     * @return
     */

    public String getEventID()
    {
        return eventID;
    }

    /**
     * sets the event ID
     *
     * @param eventID ID for the event
     */
    public void setEventID(String eventID)
    {
        this.eventID = eventID;
    }

    /**
     * gets the authorization token
     *
     * @return authToken
     */
    public String getAuthToken()
    {
        return authToken;
    }

    /**
     * sets the authorization token
     *
     * @param authToken the authorization token for this user and session
     */
    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }

    /**
     * gets the username
     *
     * @return username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * sets the username
     *
     * @param username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
}
