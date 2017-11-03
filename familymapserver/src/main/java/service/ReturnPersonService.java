package service;

import dao.DaoException;
import dao.DaoManager;
import model.Person;
import request.PersonRequest;
import result.PersonResult;

/**
 * Created by dc1992 on 10/13/17.
 */

public class ReturnPersonService
{
    /** the Dao manager that will be used for this service, containing a connection*/
    private DaoManager man;
    /** the error response that will be input to the result*/
    private String errorResponse;

    public ReturnPersonService()
    {
        try
        {
            man = new DaoManager();
        }
        catch (DaoException e)
        {
            errorResponse = ("Internal Server Error: " + e.getFunction());
        }
        errorResponse = "No Errors";
    }


    /**
     * Returns the person requested by the current user.
     *
     * @param request PersonRequest object that contains the authorization token for the user session, username, and the specified person ID
     *
     * @return PersonResult object that contains the person model
     */
    public PersonResult serve(PersonRequest request)
    {
        String authToken = request.getAuthToken();
        String username = request.getUsername();
        String personID = request.getPersonID();
        Person person = null;

        if (errorResponse.equals("No Errors"))
        {
            try
            {
                if (!man.aDao.checkAuthorization(authToken,username))
                {
                    errorResponse = "Return Person Service Error: Invalid authorization";
                }
                else if (!man.pDao.getPerson(personID).getDescendant().equals(username))
                {
                    errorResponse = "Return Person Service Error: Requested person does not belong to this user";
                }
                else
                {
                    person = man.pDao.getPerson(personID);
                }
            }
            catch(DaoException e)
            {
                errorResponse = ("Internal Server Error: " + e.getFunction());
            }
        }
        PersonResult result = new PersonResult(person,errorResponse);
        return result;
    }
}
