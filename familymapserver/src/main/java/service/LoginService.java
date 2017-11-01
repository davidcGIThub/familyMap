package service;

import request.LoginRequest;
import result.LoginResult;

/**
 * Created by dc1992 on 10/12/17.
 */

public class LoginService
{
    /**
     * logs in the user and reutrns an authorization token
     *
     * @param request LoginRequest object containing username and password
     * @return
     */
    public LoginResult serve(LoginRequest request)
    {
        LoginResult result = null;
        return result;
    }
}

        //"Login Service Error: Invalid request property";
        //"Login Service Error: Missing request property";
        //"Login Service Error: Internal Server Error";
        //"No Errors";
        //"Login Service Error: Error Unknown, misuse of setErrorResponse";
