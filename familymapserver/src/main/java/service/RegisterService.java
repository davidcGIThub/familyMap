package service;

import java.util.UUID;

import dao.DaoException;
import dao.DaoManager;
import model.Person;
import model.User;
import request.FillRequest;
import request.LoginRequest;
import request.RegisterRequest;
import result.LoginResult;
import result.RegisterResult;

/**
 * Created by dc1992 on 10/12/17.
 */

public class RegisterService
{

    /** the Dao manager that will be used for this service, containing a connection*/
    private DaoManager man;
    /** errorResponse for the service result */
    String errorResponse;

    /**
     * creates a constructor object
     */
    public RegisterService()
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
     * creates a new user accoount, generates 4 generations of ancestor data for the new user, logs the user in, and reutrns and
     * authorization token
     *
     * @param request RegisterRequest object containing all of the user information
     * @return RegisterResult object containing the authtoken, personID and username
     */
    public RegisterResult serve(RegisterRequest request)
    {
        String username = request.getUsername();
        String password = request.getPassword();
        String email = request.getEmail();
        String firstName = request.getFirstname();
        String lastName = request.getLastname();
        String gender = request.getGender();
        String authToken = null;
        String personID = null;
        RegisterResult result = null;
        if (!errorResponse.equals("No Errors"))
        {

        }
        if (username == null || password == null || email == null || firstName == null || lastName == null || gender == null)
        {
            errorResponse = "Register Service Error: Missing Request Property";
        }
        else if (!(gender.equals("m") || gender.equals("f")))
        {
            errorResponse = "Register Service Error: Invalid Request Property - gender must equal 'm' or 'f'";
        }
        else
        {
            try
            {

                if (man.uDao.checkContains("Username", username))
                {
                    errorResponse = "Register Service Error: Username already taken by another user";
                }
                else
                {
                    UUID uuid = UUID.randomUUID();
                    personID = uuid.toString();
                    User u = new User(username,password,email,personID);
                    man.uDao.addUser(u);
                    Person p = new Person(personID,username,firstName,lastName,gender,null,null,null);
                    man.pDao.addPerson(p);
                    LoginRequest loginReq = new LoginRequest(username,password);
                    LoginService loginServe = new LoginService();
                    LoginResult loginRes = loginServe.serve(loginReq);
                    authToken = loginRes.getAuthToken();
                    FillRequest fillReq = new FillRequest(4,username);
                    FillService fillServe = new FillService();
                    fillServe.serve(fillReq);
                }
            }
            catch (DaoException e)
            {
                errorResponse = ("Internal Server Error: " + e.getFunction());
            }
        }
        result = new RegisterResult(authToken,username,personID,errorResponse);
        return result;
    }


}

    //"Register Service Error: Invalid request property";
    //"Register Service Error: Missing request property";
    //"Register Service Error: Username already taken by another user";
    //"Register Service Error: Internal Server Error";
    //"No Errors";
    //"Register Service Error: Error Unknown, misuse of setErrorResponse";

