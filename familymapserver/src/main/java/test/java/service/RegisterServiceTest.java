package test.java.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DaoException;
import dao.DaoManager;
import request.RegisterRequest;
import result.RegisterResult;
import service.RegisterService;

import static org.junit.Assert.assertEquals;

/**
 * Created by david on 11/3/17.
 */
public class RegisterServiceTest
{
    private RegisterRequest registerRequest;
    private RegisterRequest registerRequest2;
    private RegisterService registerService;
    private RegisterService registerService2;
    DaoManager man;
    @Before
    public void setUp() throws Exception
    {
        try
        {
            man = new DaoManager();
            man.deleteAll();
        }
        catch(DaoException e)
        {
            System.out.println(e.getFunction());
        }

        registerRequest = new RegisterRequest("username1","password1","email1","firstname1","firstname2","m");
        registerRequest2 = new RegisterRequest("username1","password2","email2","firstname2","firstname2","f");
        registerService = new RegisterService();
        registerService2 = new RegisterService();
    }

    @After
    public void tearDown() throws Exception
    {


    }

    @Test
    public void serve() throws Exception
    {
        RegisterResult registerResult = registerService.serve(registerRequest);
        assertEquals(null,registerResult.getErrorResponse());
        assertEquals("Register Service Error: Username already taken by another user",registerService2.serve(registerRequest2).getErrorResponse());

        try
        {
            assertEquals(man.uDao.getUser("username1").getEmail(),"email1"); // check if the user was added
        }
        catch(DaoException e)
        {
            System.out.println(e.getFunction());
        }

    }

}