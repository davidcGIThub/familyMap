package test.java.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DaoException;
import dao.DaoManager;
import model.User;
import request.LoginRequest;
import result.LoginResult;
import service.LoginService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by david on 11/3/17.
 */
public class LoginServiceTest
{
    LoginRequest loginRequest;
    LoginService loginService;
    LoginRequest loginRequest2;
    LoginService loginService2;
    LoginRequest loginRequest3;
    LoginService loginService3;
    private DaoManager man;

    @Before
    public void setUp() throws Exception
    {
        try
        {
            man = new DaoManager();
            man.deleteAll();
            User u = new User("username1", "password1", "email1", "personID1");
            man.uDao.addUser(u);
        }
        catch(DaoException e)
        {
            System.out.println(e.getFunction());
        }

        loginRequest = new LoginRequest("username1","password1");
        loginService = new LoginService();
        loginRequest2 = new LoginRequest("username1","wrongPassword");
        loginService2 = new LoginService();
        loginRequest3 = new LoginRequest("unregisteredUser","password");
        loginService3 = new LoginService();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void serve() throws Exception
    {
        LoginResult loginResult = loginService.serve(loginRequest);
        assertEquals(null, loginResult.getErrorResponse()); //general check to see if there are no errors

        LoginResult loginResult2 = loginService2.serve(loginRequest2);
        assertEquals("Login Service Error: Invalid username or Password", loginResult2.getErrorResponse()); //check to see if the password was wrong

        LoginResult loginResult3 = loginService3.serve(loginRequest3);
        assertEquals("Login Service Error: Invalid username or Password", loginResult3.getErrorResponse()); //check to see if unregistered user logged in

        try
        {
            assertEquals(man.uDao.getUser("username1").getEmail(),"email1"); // check if the user was added
            String authToken = loginResult.getAuthToken();
            assertTrue(man.aDao.checkAuthorization(authToken)); //check if the token was added

            String authToken2 = loginResult3.getAuthToken();
            assertTrue(!man.aDao.checkAuthorization(authToken2)); //check if the token was added, shouldn't have been

        }
        catch(DaoException e)
        {
            System.out.println(e.getFunction());
        }

    }

}