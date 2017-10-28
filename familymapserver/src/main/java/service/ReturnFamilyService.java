package service;

import result.FamilyResult;
import request.FamilyRequest;
/**
 * Created by dc1992 on 10/13/17.
 */

public class ReturnFamilyService
{
    /**
     * Returns all events for all family members of the current user. The current user is determined from the provided authorization token
     *
     * @param request FamilyRequst object that contains the auhtorization token for the user session
     *
     * @return FamilyResult object wich is an array of the person objects
     */
    public FamilyResult serve(FamilyRequest request)
    {
        FamilyResult result = null;
        return result;
    }
}
