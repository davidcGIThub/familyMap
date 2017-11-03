package service;

import dao.DaoException;
import dao.DaoManager;
import model.Event;
import request.UserEventsRequest;
import result.EventResult;
import result.UserEventsResult;

/**
 * Created by dc1992 on 10/13/17.
 */

public class ReturnUserEventsService
{

    /** the Dao manager that will be used for this service, containing a connection*/
    private DaoManager man;
    /** the error response that will be input to the result*/
    private String errorResponse;

    public ReturnUserEventsService()
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
     * @param  request UserEventRequest object that contains the authorization token for the current user session
     *
     * @return the UserEventObject that contains all of the events of the user
     */
    public UserEventsResult serve(UserEventsRequest request)
    {
        UserEventsResult result = null;
        String authToken = request.getAuthToken();
        String username = request.getUsername();
        Event[] events = null;

        if (errorResponse.equals("No Errors"))
        {
            try
            {
                if (!man.aDao.checkAuthorization(authToken,username))
                {
                    errorResponse = "Return User Event Service Error: Invalid authorization";
                }
                else
                {
                    events = man.eDao.getUserEvents(username);
                }
            }
            catch(DaoException e)
            {
                errorResponse = ("Internal Server Error: " + e.getFunction());
            }
        }
        result = new UserEventsResult(events,errorResponse);
        return result;
    }
}

           //"UserEvents Service Error: Invalid authorization token";
            // "UserEvents Service Error: Internal Server Error";
            // "No Errors";
            // "UserEvents Service Error: Error Unknown, misuse of setErrorResponse";
