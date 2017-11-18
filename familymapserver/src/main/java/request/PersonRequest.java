package request;

/**
 * Created by dc1992 on 10/23/17.
 */

public class PersonRequest extends Request
{
    /**
     * the person ID of the person requested
     */
    private String personID;

    /**
     * Creates a Person Request object
     *
     * @param authToken authorization token of the user
     * @param personID  person ID of the person requested
     */
    public PersonRequest(String personID, String authToken) {
        setAuthToken(authToken);
        this.personID = personID;
    }

    /**
     * gets the person ID of the person requested
     *
     * @return personID
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * sets the person ID
     *
     * @param personID the person ID of the person requested
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
