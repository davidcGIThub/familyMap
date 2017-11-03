package request;

/**
 * Created by dc1992 on 10/23/17.
 */

public class UserEventsRequest
{
    /** the authorization token of the user*/
    private String authToken;
    /** the username of the person requesting the events*/
    private String username;

    /**
     * creates a UserEventsRequest object
     *
     * @param authToken the authorization token for the user
     */
    public UserEventsRequest(String authToken, String username)
    {
        this.authToken = authToken;
        this.username = username;
    }

    /**
     * gets the authorization token of the user
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
     * @param authToken authorization token of the user being set
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
