package service;

import dao.DaoException;
import dao.DaoManager;
import model.Person;
import request.FamilyRequest;
import result.FamilyResult;

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
            errorResponse = "No Errors";
        }
        catch (DaoException e)
        {
            errorResponse = ("Internal Server Error: " + e.getFunction());
        }
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
        String username = null;
        Person[] persons = null;

        if (errorResponse.equals("No Errors"))
        {
            try
            {
                if(man.aDao.checkAuthorization(authToken))
                {
                    username = man.aDao.getUsername(authToken);
                    persons = man.pDao.getUserPersons(username);
                }
                else
                {
                    errorResponse = "Return Family Service Error: Invalid authorization";
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


