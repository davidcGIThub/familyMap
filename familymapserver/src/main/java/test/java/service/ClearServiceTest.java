package test.java.service;

import org.junit.Before;
import org.junit.Test;

import dao.DaoException;
import dao.DaoManager;
import model.Event;
import model.Person;
import model.User;
import request.ClearRequest;
import service.ClearService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by david on 11/3/17.
 */
public class ClearServiceTest {

    private ClearRequest clearRequest;
    private ClearService clearService;
    DaoManager man;
    User[] u;
    Person[] p;
    Event[] e;

    @Before
    public void setUp() throws Exception
    {
        try
        {
            man = new DaoManager();
            man.deleteAll();
            User u1 = new User("username0", "password0", "email0", "personID0");
            User u2 = new User("username2", "password2", "email2", "personID2");
            u = new User[]{u1, u2};
            Person p0 = new Person("personID0", "username0", "firstName0", "lastName0", "m", "father0", "mother0", "spouse0");
            Person p1 = new Person("personID1", "username1", "firstName1", "lastName1", "m", "father1", "mother1", "spouse1");
            Person p2 = new Person("personID2", "username2", "firstName2", "lastName2", "f", "father2", "mother2", "spouse2");
            Person p3 = new Person("personID3", "username2", "firstName3", "lastName3", "f", "father0", "mother3", "spouse3");
            p = new Person[]{p0, p1, p2, p3};
            Event e0 = new Event("eventID0", "username0", "personID0", 0, 0, "country0", "city0", "Birth", 0);
            Event e1 = new Event("eventID1", "username0", "personID1", 1, 1, "country1", "city1", "Baptism", 1);
            Event e2 = new Event("eventID2", "username0", "personID1", 2, 2, "country2", "city2", "Baptism", 2);
            Event e3 = new Event("eventID3", "username2", "personID3", 3, 3, "country3", "city3", "Marriage", 3);
            Event e4 = new Event("eventID4", "username2", "personID2", 4, 4, "country4", "city4", "Marriage", 4);
            Event e5 = new Event("eventID5", "username2", "personID2", 5, 5, "country5", "city5", "Marriage", 5);
            Event e6 = new Event("eventID6", "username2", "personID3", 6, 6, "country6", "city6", "Death", 6);
            e = new Event[]{e0, e1, e2, e3, e4, e5, e6};
            man.eDao.importEvents(e);
            man.pDao.importPersons(p);
            man.uDao.importUsers(u);

        }
        catch(DaoException except)
        {
            System.out.println(except.getFunction());
        }

        clearRequest = new ClearRequest();
        clearService = new ClearService();
    }

    @Test
    public void serve() throws Exception
    {
        assertEquals(null, clearService.serve(clearRequest).getErrorResponse()); //general check to see if there are no errors
        try
        {   //check to see if everything has been removed
            Event[] e = man.eDao.getUserEvents("username0");
            Event[] e2 = man.eDao.getUserEvents("username2");
            Person[] p = man.pDao.getUserPersons("username2");
            Person[] p2 = man.pDao.getUserPersons("username1");
            Person[] p3 = man.pDao.getUserPersons("username0");
            assertTrue(p2.length == 0);
            assertTrue(p2.length == 0);
            assertTrue(p.length == 0);
            assertTrue(e.length == 0);
            assertTrue(e2.length == 0);
            assertTrue(!man.uDao.checkContains("Username","username0"));
            assertTrue(!man.uDao.checkContains("Username","username1"));
            assertTrue(!man.uDao.checkContains("Username","username2"));
        }
        catch (DaoException except)
        {
            System.out.println(except.getFunction());
        }
    }

}