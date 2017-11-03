package service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import dao.DaoException;
import dao.DaoManager;
import model.Person;
import model.User;
import request.ClearRequest;
import request.FillRequest;
import result.FillResult;

import static org.junit.Assert.*;

/**
 * Created by david on 11/3/17.
 */
public class FillServiceTest
{
    FillRequest fillRequest;
    FillService fillService;
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
            Person p = new Person("personID1", "username1", "firstname1", "lastname1", "m", null, null, null);
            man.uDao.addUser(u);
            man.pDao.addPerson(p);
        }
        catch(DaoException e)
        {
            System.out.println(e.getFunction());
        }

        fillRequest = new FillRequest(4,"username1");
        fillService = new FillService();
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void serve() throws Exception
    {
        FillResult fillResult = fillService.serve(fillRequest);
        String errorResponse = fillResult.getErrorResponse();
        assertEquals("No Errors",errorResponse);
    }

}