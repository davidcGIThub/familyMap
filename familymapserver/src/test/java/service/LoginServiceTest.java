package service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DaoException;
import dao.DaoManager;
import model.User;
import request.ClearRequest;
import request.LoginRequest;
import result.LoginResult;

import static org.junit.Assert.*;

/**
 * Created by david on 11/3/17.
 */
public class LoginServiceTest
{
    LoginRequest loginRequest;
    LoginService loginService;
    private DaoManager man;

    @Before
    public void setUp() throws Exception
    {
        ClearRequest clearRequest = new ClearRequest();
        ClearService clearService = new ClearService();
        clearService.serve(clearRequest);
        try
        {
            man = new DaoManager();
            User u = new User("username1", "password1", "email1", "personID1");
            man.uDao.addUser(u);
        }
        catch(DaoException e)
        {
            System.out.println(e.getFunction());
        }

        loginRequest = new LoginRequest("username1","password1");
        loginService = new LoginService();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void serve() throws Exception
    {
        LoginResult loginResult = loginService.serve(loginRequest);
        assertEquals(null, loginResult.getErrorResponse());

    }

}