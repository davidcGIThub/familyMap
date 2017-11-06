package result;

import model.Person;

/**
 * Created by dc1992 on 10/13/17.
 */

public class PersonResult
{
    /** the username associated with this person*/
    private Person person;
    /** the error response printed to the user*/
    private String errorResponse;

    /**
     * creates a PersonResult object, and creates the error Response message
     *
     * @param person person object being returned
     * @param errorResponse the type of error
     */
    public PersonResult(Person person, String errorResponse)
    {
        this.person = person;
        setErrorResponse(errorResponse);
        if(errorResponse.equals("No Errors"))
        {
            this.errorResponse = null;
        }
        else
        {
            this.person = null;
        }
    }

    /**
     * sets the person Object
     *
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * gets the Person
     *
     * @return person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * gets the error response message
     *
     * @return errorResponse
     */
    public String getErrorResponse() {
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
