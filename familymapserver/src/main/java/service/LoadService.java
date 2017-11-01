package service;

import request.LoadRequest;
import result.LoadResult;

/**
 * Created by dc1992 on 10/13/17.
 */

public class LoadService
{
    /**
     * Clears all data from the database and then loads the posted user, person, and event into the database
     *
     * @param request LoadRequest object containing the user, person and event data
     * @return LoadResult object stating if the service was successful
     */
    public LoadResult serve(LoadRequest request)
    {
        LoadResult result = null;
        return result;
    }
}

// "Load Service Error: Invalid request data - missing values";
// "Load Service Error: Invalid request data - invalid values";
// "Load Service Error: Internal Server Error";
// "No Errors";
// "Load Service Error: Error Unknown, misuse of setErrorResponse";
