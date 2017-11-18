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
        errorResponse = null;
        try
        {
            man = new DaoManager();
            this.errorResponse = "No Errors";
        }
        catch (DaoException e)
        {
            this.errorResponse = ("Internal Server Error: " + e.getFunction());
        }
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
        String username = null;
        String personID = request.getPersonID();
        Person person = null;
        if (this.errorResponse.equals("No Errors"))
        {
            try
            {
                if(man.aDao.checkAuthorization(authToken))
                {
                    username = man.aDao.getUsername(authToken);
                    if(!man.pDao.getPerson(personID).getDescendant().equals(username))
                    {
                        this.errorResponse = "Return Person Service Error: Requested person does not belong to this user";
                    }
                    else
                    {
                        person = man.pDao.getPerson(personID);
                    }
                }
                else
                {
                    this.errorResponse = "Return Person Service Error: Invalid authorization";
                }
            }
            catch(DaoException e)
            {
                this.errorResponse = ("Internal Server Error: " + e.getFunction());
            }
        }
        PersonResult result = new PersonResult(person,this.errorResponse);
        return result;
    }
}
