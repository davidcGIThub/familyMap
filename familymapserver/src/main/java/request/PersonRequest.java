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

    /**
     * Creates a Person Request object
     *
     * @param authToken authorization token of the user
     * @param personID person ID of the person requested
     */
    public PersonRequest(String authToken, String personID)
    {
        this.authToken = authToken;
        this.personID = personID;
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
}
