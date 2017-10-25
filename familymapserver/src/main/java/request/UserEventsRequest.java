package request;

/**
 * Created by dc1992 on 10/23/17.
 */

public class UserEventsRequest
{
    /** the authorization token of the user*/
    private String authToken;

    /**
     * creates a UserEventsRequest object
     *
     * @param authToken the authorization token for the user
     */
    public UserEventsRequest(String authToken)
    {
        this.authToken = authToken;
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
}
