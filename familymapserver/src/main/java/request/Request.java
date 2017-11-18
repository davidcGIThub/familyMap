package request;

/**
 * Created by david on 11/17/17.
 */

public class Request
{
    /**
    *   the authorization token for the user and session
     */
    private String authToken;

    public Request()
    {
        authToken = null;
    }

    /**
     * gets the authorization token
     *
     * @return authToken
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * sets the authorization token
     *
     * @param authToken the authorization token for this user and session
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

}
