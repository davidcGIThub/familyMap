package result;

/**
 * Created by dc1992 on 10/13/17.
 */

public class RegisterResult extends Result
{
    /** authorization token of user being registered*/
    private String authToken;
    /** username of person being registered*/
    private String userName;
    /** person Id of the user's person object*/
    private String personID;
    /** the error response printed to the user*/
    private String errorResponse;


    /**
     * creates a RegisterResult object, and creates the error Response message
     *
     * @param authToken the authorization token for the person registering
     * @param userName the username of the person registering
     * @param personID the person ID for the user
     * @param errorResponse the type of error
     */
    public RegisterResult(String authToken, String userName, String personID, String errorResponse)
    {
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
        setErrorResponse(errorResponse);
        if(errorResponse.equals("No Errors"))
        {
            this.errorResponse = null;
        }
        else
        {
            this.authToken = null;
            this.userName = null;
            this.personID = null;
        }
    }

    /**
     * sets the authorization token
     *
     * @param authToken the authorization token for the person registering
     */
    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }

    /**
     * sets the user name
     *
     * @param userName the username of the person registering
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * sets the person ID
     *
     * @param personID the person ID for the user
     */
    public void setPersonID(String personID)
    {
        this.personID = personID;
    }


    /**
     * gets the authorization token for the person registering
     *
     * @return authToken
     */
    public String getAuthToken()
    {
        return authToken;
    }

    /**
     * gets the username of the person registering
     *
     * @return username
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * gets the person ID for the user registering
     *
     * @return personID
     */
    public String getPersonID()
    {
        return personID;
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
     * sets the error response dependant on the error specified
     *
     * @param errorResponse the type of error
     */
    public void setErrorResponse(String errorResponse)
    {
        this.errorResponse = errorResponse;
    }
}
