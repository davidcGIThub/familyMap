package request;

/**
 * Created by dc1992 on 10/23/17.
 */

public class FamilyRequest
{
    /** the authorization token for the user and session*/
    private String authToken;
    /** the username of the user requesting their family*/
    private String username;

    /**
     * creates a FamilyRequest Object
     * @param authToken
     */
    public FamilyRequest(String authToken, String username)
    {
        this.authToken = authToken;
        this.username = username;
    }

    /**
     * gets the authorization token for the family Request
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
     * @param authToken the authorization token for the user and session
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
