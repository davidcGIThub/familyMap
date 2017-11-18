package result;

/**
 * Created by dc1992 on 10/13/17.
 */

public class LoginResult extends Result
{
    /** the authorization token for the user*/
    private String authToken;
    /** userName of the user*/
    private String userName;
    /** the id for the person object of the user*/
    private String personID;
    /** the error response printed to the use*/
    private String errorResponse;


    /**
     * creates a LoginResult object, and creates the error Response
     *
     * @param authToken the authorization token for the user logging in
     * @param userName the userName of the person logging in
     * @param personID the person ID for the user logging in
     * @param errorResponse the type of error
     */
    public LoginResult(String authToken, String userName, String personID, String errorResponse)
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
     * sets the authorization token for the result object
     *
     * @param authToken the authorization token of the user
     */
    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }

    /**
     * sets the userName of for the result object
     *
     * @param username the userName of the user
     */
    public void setUsername(String username)
    {
        this.userName = username;
    }

    /**
     * sets the person Id for the results object
     *
     * @param personID the person ID of the user
     */
    public void setPersonID(String personID)
    {
        this.personID = personID;
    }

    /**
     * gets the authorization token
     *
     * @return authorization token
     */
    public String getAuthToken()
    {
        return authToken;
    }

    /**
     * gets the userName
     *
     * @return userName
     */
    public String getUsername()
    {
        return userName;
    }

    /**
     * gets the person ID
     *
     * @return personID
     */
    public String getPersonID()
    {
        return personID;
    }

    /**
     * gets the string Error Response
     *
     * @return errorResponse
     */
    public String getErrorResponse()
    {
        return errorResponse;
    }

    /**
     * sets the error response dependant on what is error is specified
     *
     * @param errorResponse the type of error
     */
    public void setErrorResponse(String errorResponse)
    {
        this.errorResponse = errorResponse;
    }
}
