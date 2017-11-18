package test.java.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DaoException;
import dao.DaoManager;
import model.Event;
import model.Person;
import model.User;
import request.FillRequest;
import result.FillResult;
import service.FillService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        try
        {
            man = new DaoManager();
            man.deleteAll();
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
        assertEquals(null,errorResponse);
        try
        {
            //test to see if an array of events or persons were filled
            Event[] events = man.eDao.getUserEvents("username1");
            Person[] persons = man.pDao.getUserPersons("username1");
            assertTrue(events.length > 0);
            assertTrue(persons.length > 0 );
        }
        catch(DaoException e)
        {
            System.out.println(e.getFunction());
        }

    }

}