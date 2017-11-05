package service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import request.ClearRequest;
import request.RegisterRequest;

import static org.junit.Assert.*;

/**
 * Created by david on 11/3/17.
 */
public class RegisterServiceTest
{
    private RegisterRequest registerRequest;
    RegisterService registerService;
    @Before
    public void setUp() throws Exception
    {
        ClearRequest clearRequest = new ClearRequest();
        ClearService clearService = new ClearService();
        clearService.serve(clearRequest);

        registerRequest = new RegisterRequest("username1","password1","email1","firstname1","firstname2","m");
        registerService = new RegisterService();
    }

    @After
    public void tearDown() throws Exception
    {
        assertEquals("No Errors",registerService.serve(registerRequest).getErrorResponse());
    }

    @Test
    public void serve() throws Exception
    {

    }

}