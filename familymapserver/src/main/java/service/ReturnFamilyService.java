package service;

import dao.DaoException;
import dao.DaoManager;
import model.Person;
import result.FamilyResult;
import request.FamilyRequest;
import result.PersonResult;

/**
 * Created by dc1992 on 10/13/17.
 */

public class ReturnFamilyService
{
    /** the Dao manager that will be used for this service, containing a connection*/
    private DaoManager man;
    /** the error response that will be input to the result*/
    private String errorResponse;

    public ReturnFamilyService()
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
     * Returns all events for all family members of the current user. The current user is determined from the provided authorization token
     *
     * @param request FamilyRequest object that contains the auhtorization token for the user session
     *
     * @return FamilyResult object wich is an array of the person objects
     */
    public FamilyResult serve(FamilyRequest request)
    {
        String authToken = request.getAuthToken();
        String username = request.getUsername();;
        Person[] persons = null;

        if (errorResponse.equals("No Errors"))
        {
            try
            {
                if (!man.aDao.checkAuthorization(authToken,username))
                {
                    errorResponse = "Return Family Service Error: Invalid authorization";
                }
                else
                {
                    persons = man.pDao.getUserPersons(username);
                }
            }
            catch(DaoException e)
            {
                errorResponse = ("Internal Server Error: " + e.getFunction());
            }
        }
        FamilyResult result = new FamilyResult(persons,errorResponse);
        return result;
    }
}


