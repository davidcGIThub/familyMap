package result;

import model.Person;

/**
 * Created by dc1992 on 10/13/17.
 */

public class FamilyResult extends Result
{
    /** an array of all the ancestors for the user*/
    Person[] persons;
    /** the error response printed to he user*/
    String errorResponse;


    /**
     * creates an FamilyResult object, and creates the error response
     *
     * @param persons the array of person objects being retrieved
     * @param errorResponse the type of error
     */
    public FamilyResult(Person[] persons, String errorResponse)
    {
        this.persons = persons;
        setErrorResponse(errorResponse);
        if(errorResponse.equals("No Errors"))
        {
            this.errorResponse = null;
        }
        else
        {
            this.persons = null;
        }
    }


    /**
     *sets the Person array
     *
     * @param persons array of persons being added
     */
    public void setPersons(Person[] persons)
    {
        this.persons = persons;
    }

    /**
     * gets the the array of Persons being added
     *
     * @return persons
     */
    public Person[] getPersons()
    {
        return persons;
    }

    /**
     * gets the error response
     *
     * @return errorResponse
     */
    public String getErrorResponse()
    {
        return errorResponse;
    }

    /**
     * sets the error response dependant on the error specified
     * @param errorResponse the type of error
     */
    public void setErrorResponse(String errorResponse)
    {
        this.errorResponse = errorResponse;
    }
}
