package service;

import request.UserEventsRequest;
import result.UserEventsResult;

/**
 * Created by dc1992 on 10/13/17.
 */

public class ReturnUserEventsService
{
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
        return result;
    }
}

           //"UserEvents Service Error: Invalid authorization token";
            // "UserEvents Service Error: Internal Server Error";
            // "No Errors";
            // "UserEvents Service Error: Error Unknown, misuse of setErrorResponse";
