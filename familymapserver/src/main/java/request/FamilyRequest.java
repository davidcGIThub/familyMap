package request;

/**
 * Created by dc1992 on 10/23/17.
 */

public class FamilyRequest
{
    /** the authorization token for the user and session*/
    public String authToken;

    /**
     * creates a FamilyRequest Object
     * @param authToken
     */
    public FamilyRequest(String authToken)
    {
        this.authToken = authToken;
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
}
