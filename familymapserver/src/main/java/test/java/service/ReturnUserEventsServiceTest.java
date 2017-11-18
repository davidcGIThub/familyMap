package test.java.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import dao.DaoException;
import dao.DaoManager;
import model.AuthToken;
import model.Event;
import model.Person;
import model.User;
import request.ClearRequest;
import request.RegisterRequest;
import request.UserEventsRequest;
import result.UserEventsResult;
import service.ClearService;
import service.RegisterService;
import service.ReturnUserEventsService;

import static org.junit.Assert.assertEquals;

/**
 * Created by david on 11/3/17.
 */
public class ReturnUserEventsServiceTest {
    DaoManager man;
    UserEventsRequest eventsRequest;
    ReturnUserEventsService eventsService;
    @Before
    public void setUp() throws Exception
    {
        try
        {
            ClearRequest clearRequest = new ClearRequest();
            ClearService clearService = new ClearService();
            clearService.serve(clearRequest);

            man = new DaoManager();
            RegisterRequest registerRequest = new RegisterRequest("username1","password1","email1","firstname1","lastName1","m");
            RegisterService registerService = new RegisterService();
            registerService.serve(registerRequest);

            User u1 = new User("username0", "password0", "email0", "personID0");
            User u2 = new User("username2", "password2", "email2", "personID2");
            User[] u = new User[]{u1, u2};
            Person p0 = new Person("personID0", "username0", "firstName0", "lastName0", "m", "father0", "mother0", "spouse0");
            Person p1 = new Person("personID1", "username1", "firstName1", "lastName1", "m", "father1", "mother1", "spouse1");
            Person p2 = new Person("personID2", "username2", "firstName2", "lastName2", "f", "father2", "mother2", "spouse2");
            Person p3 = new Person("personID3", "username2", "firstName3", "lastName3", "f", "father0", "mother3", "spouse3");
            Person[] p = new Person[]{p0, p1, p2, p3};
            Event e0 = new Event("eventID0", "username0", "personID0", 0, 0, "country0", "city0", "Birth", 0);
            Event e1 = new Event("eventID1", "username0", "personID1", 1, 1, "country1", "city1", "Baptism", 1);
            Event e2 = new Event("eventID2", "username0", "personID1", 2, 2, "country2", "city2", "Baptism", 2);
            Event e3 = new Event("eventID3", "username2", "personID3", 3, 3, "country3", "city3", "Marriage", 3);
            Event e4 = new Event("eventID4", "username2", "personID2", 4, 4, "country4", "city4", "Marriage", 4);
            Event e5 = new Event("eventID5", "username2", "personID2", 5, 5, "country5", "city5", "Marriage", 5);
            Event e6 = new Event("eventID6", "username2", "personID3", 6, 6, "country6", "city6", "Death", 6);
            Event[] e = new Event[]{e0, e1, e2, e3, e4, e5, e6};
            Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
            String timeCurrent = currentTimeStamp.toString();
            //test
            AuthToken a1 = new AuthToken("tok1","username0",timeCurrent);
            AuthToken a2 = new AuthToken("tok2","username2",timeCurrent);
            man.aDao.addToken(a1);
            man.aDao.addToken(a2);
            man.eDao.importEvents(e);
            man.uDao.importUsers(u);
            man.pDao.importPersons(p);
            eventsRequest = new UserEventsRequest("tok1");
            eventsService = new ReturnUserEventsService();
        }
        catch(DaoException except)
        {
            System.out.println(except.getFunction());
        }

    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void serve() throws Exception
    {
        UserEventsResult eventsResult = eventsService.serve(eventsRequest);
        assertEquals(null, eventsResult.getErrorResponse());
        assertEquals(3, eventsResult.getEvents().length);

    }

}