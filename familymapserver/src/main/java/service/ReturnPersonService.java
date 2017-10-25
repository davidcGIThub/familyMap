package service;

import request.PersonRequest;
import result.PersonResult;

/**
 * Created by dc1992 on 10/13/17.
 */

public class ReturnPersonService
{
    /**
     * Returns all family members of the current user. The current user is determined from the provided auth token
     *
     * @param request PersonRequest object that contains the authorization token for the user session, and the specified person ID
     *
     * @return PersonResult object that contains an array of the person objects
     */
    public PersonResult serve(PersonRequest request)
    {
        PersonResult result;
        return result;
    }
}
