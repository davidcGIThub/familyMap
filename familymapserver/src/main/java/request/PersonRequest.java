package request;

/**
 * Created by dc1992 on 10/23/17.
 */

public class PersonRequest
{
    /** the authorization token of the user*/
    private String authToken;
    /** the person ID of the person requested*/
    private String personID;
    /** the username of the user requesting the person */
    private String username;

    /**
     * Creates a Person Request object
     *
     * @param authToken authorization token of the user
     * @param personID person ID of the person requested
     * @param username username of the user
     */
    public PersonRequest(String personID, String authToken, String username)
    {
        this.authToken = authToken;
        this.personID = personID;
        this.username = username;
    }

    /**
     *  gets the authorization token
     *
     * @return authToken
     */
    public String getAuthToken()
    {
        return authToken;
    }

    /**
     *  sets the authorization token
     *
     * @param authToken the authorization token of the user
     */
    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }

    /**
     * gets the person ID of the person requested
     *
     * @return personID
     */
    public String getPersonID()
    {
        return personID;
    }

    /**
     * sets the person ID
     *
     * @param personID the person ID of the person requested
     */
    public void setPersonID(String personID)
    {
        this.personID = personID;
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
