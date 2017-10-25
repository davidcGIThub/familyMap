package service;

import request.RegisterRequest;
import result.RegisterResult;

/**
 * Created by dc1992 on 10/12/17.
 */

public class RegisterService
{
    /**
     * creates a new user accoount, generates 4 generations of ancestor data for the new user, logs the user in, and reutrns and
     * authorization token
     *
     * @param request RegisterRequest object containing all of the user information
     * @return RegisterResult object containing the authtoken, personID and username
     */
    public RegisterResult serve(RegisterRequest request)
    {
        RegisterResult result;
        return result;
    }
}
