package service;

import dao.DaoException;
import dao.DaoManager;
import model.AuthToken;
import model.Event;
import result.EventResult;
import request.EventRequest;

/**
 * Created by dc1992 on 10/13/17.
 */

public class ReturnEventService
{
    /** the Dao manager that will be used for this service, containing a connection*/
    private DaoManager man;
    /** the error response that will be input to the result*/
    private String errorResponse;

    public ReturnEventService()
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
     * Returns the single event object with the specified ID
     *
     * @param request The ReturnEventRequest object containing the the authorization token of the user, and the eventID for the specified event
     * @return the EventResult object containing the event data
     */
    public EventResult serve(EventRequest request)
    {
        String authToken = request.getAuthToken();
        String username = null;
        String eventID = request.getEventID();
        Event event = null;

        if (errorResponse.equals("No Errors"))
        {
            try
            {
                if(man.aDao.checkAuthorization(authToken))
                {
                    username = man.aDao.getUsername(authToken);
                    if(!man.eDao.getEvent(eventID).getDescendant().equals(username))
                    {
                        errorResponse = "Return Event Service Error: Requested event does not belong to this user";
                    }
                    else
                    {
                        event = man.eDao.getEvent(eventID);
                    }
                }
                else
                {
                    errorResponse = "Return Event Service Error: Invalid authorization";
                }
            }
            catch(DaoException e)
            {
                errorResponse = ("Internal Server Error: " + e.getFunction());
            }
        }
        EventResult result = new EventResult(event,errorResponse);
        return result;

    }
}


