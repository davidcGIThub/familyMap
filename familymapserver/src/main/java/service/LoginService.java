package service;

import java.sql.Timestamp;
import java.util.UUID;

import dao.DaoException;
import dao.DaoManager;
import model.AuthToken;
import model.User;
import request.LoginRequest;
import result.LoginResult;

/**
 * Created by dc1992 on 10/12/17.
 */

public class LoginService
{
    /** the Dao manager that will be used for this service, containing a connection*/
    private DaoManager man;
    /** errorResponse for the service result */
    String errorResponse;

    /**
     * creates a constructor object
     */
    public LoginService()
    {
        try
        {
            this.man = new DaoManager();
            errorResponse = "No Errors";
        }
        catch (DaoException e)
        {
            errorResponse = ("Internal Server Error: " + e.getFunction());
        }
    }

    /**
     * logs in the user and returns an authorization token
     *
     * @param request LoginRequest object containing username and password
     * @return
     */
    public LoginResult serve(LoginRequest request)
    {
        String password = request.getPassword();
        String username = request.getUsername();
        String personID = null;
        String authToken = null;
        LoginResult result;
        try
        {
            if (password == null || username == null)
            {
                errorResponse = "Login Service Error: Missing Request Property";
            }
            else if (man.uDao.checkNameAndPassword(username, password))
            {
                //get the personID
                User u = man.uDao.getUser(username);
                personID = u.getPersonID();
                //create an authorization token
                UUID uuid = UUID.randomUUID();
                authToken = uuid.toString();
                Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
                String timeStamp = currentTimeStamp.toString();
                AuthToken tok = new AuthToken(authToken,username,timeStamp);
                man.aDao.addToken(tok);
                //error response
                errorResponse =  "No Errors";
            }
            else
            {
                errorResponse = "Login Service Error: Invalid username or Password";
            }
        }
        catch(DaoException e)
        {
            errorResponse = ("Internal Server Error: " + e.getFunction());
        }

        result = new LoginResult(authToken, username, personID, errorResponse);
        return result;
    }
}

