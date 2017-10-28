package result;

import model.Person;

/**
 * Created by dc1992 on 10/13/17.
 */

public class FamilyResult
{
    /** an array of all the ancestors for the user*/
    Person[] persons;
    /** the error response printed to he user*/
    String errorResponse;


    /**
     * creates an FamilyResult object, and creates the error response
     *
     * @param persons the array of person objects being retrieved
     */
    public FamilyResult(Person[] persons)
    {
        this.persons = persons;
        this.errorResponse = null;
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
}
