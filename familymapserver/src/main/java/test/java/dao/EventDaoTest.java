package test.java.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DaoException;
import dao.DaoManager;
import model.Event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by david on 11/2/17.
 */
public class EventDaoTest
{
    private DaoManager man;
    Event e7;
    Event ee[];

    @Before
    public void setUp() throws Exception
    {
        System.out.println("EventDao Junit Testing:");
        man = new DaoManager();
    }

    @After
    public void tearDown() throws Exception
    {
        man.closeSqlSession();
    }

    @Test
    public void addGetEvent() throws Exception
    {
        Event e0 = new Event("eventID0", "descendant0", "personID0", 0, 0, "country0", "city0", "Birth", 0);
        man.eDao.addEvent(e0);
        assertEquals(man.eDao.getEvent("eventID0").getEventID(),"eventID0");
        man.deleteAll();
    }

    @Test
    public void importEvents() throws Exception
    {
        Event e1 = new Event("eventID1", "descendant1", "personID1", 1, 1, "country1", "city1", "Baptism", 1);
        Event e2 = new Event("eventID2", "descendant1", "personID2", 2, 2, "country2", "city2", "Baptism", 2);
        Event e3 = new Event("eventID3", "descendant1", "personID3", 3, 3, "country3", "city3", "Marriage", 3);
        Event e4 = new Event("eventID4", "descendant4", "personID4", 4, 4, "country4", "city4", "Marriage", 4);
        Event e5 = new Event("eventID5", "descendant4", "personID4", 5, 5, "country5", "city5", "Marriage", 5);
        Event e6 = new Event("eventID6", "descendant6", "personID6", 6, 6, "country6", "city6", "Death", 6);
        Event e[] = new Event[]{e1, e2, e3, e4, e5, e6};
        man.eDao.importEvents(e);
        assertEquals(man.eDao.getEvent("eventID1").getEventID(),"eventID1");
        assertEquals(man.eDao.getEvent("eventID2").getEventID(),"eventID2");
        assertEquals(man.eDao.getEvent("eventID3").getEventID(),"eventID3");
        assertEquals(man.eDao.getEvent("eventID4").getEventID(),"eventID4");
        assertEquals(man.eDao.getEvent("eventID5").getEventID(),"eventID5");
        assertEquals(man.eDao.getEvent("eventID6").getEventID(),"eventID6");
        man.deleteAll();

    }

    @Test
    public void getUserEvents() throws Exception
    {
        Event e0 = new Event("eventID0", "descendant0", "personID0", 0, 0, "country0", "city0", "Birth", 0);
        Event e1 = new Event("eventID1", "descendant1", "personID1", 1, 1, "country1", "city1", "Baptism", 1);
        Event e2 = new Event("eventID2", "descendant1", "personID2", 2, 2, "country2", "city2", "Baptism", 2);
        Event e3 = new Event("eventID3", "descendant1", "personID3", 3, 3, "country3", "city3", "Marriage", 3);
        Event e4 = new Event("eventID4", "descendant4", "personID4", 4, 4, "country4", "city4", "Marriage", 4);
        Event e5 = new Event("eventID5", "descendant4", "personID4", 5, 5, "country5", "city5", "Marriage", 5);
        Event e6 = new Event("eventID6", "descendant6", "personID6", 6, 6, "country6", "city6", "Death", 6);
        Event e[] = new Event[]{e0, e1, e2, e3, e4, e5, e6};
        man.eDao.importEvents(e);

        ee = man.eDao.getUserEvents("descendant1");
        assertEquals(ee.length, 3);
        man.deleteAll();
    }

    @Test
    public void getPersonEvents() throws Exception
    {
        Event e0 = new Event("eventID0", "descendant0", "personID0", 0, 0, "country0", "city0", "Birth", 0);
        Event e1 = new Event("eventID1", "descendant1", "personID1", 1, 1, "country1", "city1", "Baptism", 1);
        Event e2 = new Event("eventID2", "descendant1", "personID2", 2, 2, "country2", "city2", "Baptism", 2);
        Event e3 = new Event("eventID3", "descendant1", "personID3", 3, 3, "country3", "city3", "Marriage", 3);
        Event e4 = new Event("eventID4", "descendant4", "personID4", 4, 4, "country4", "city4", "Marriage", 4);
        Event e5 = new Event("eventID5", "descendant4", "personID4", 5, 5, "country5", "city5", "Marriage", 5);
        Event e6 = new Event("eventID6", "descendant6", "personID6", 6, 6, "country6", "city6", "Death", 6);
        Event e[] = new Event[]{e0, e1, e2, e3, e4, e5, e6};
        man.eDao.importEvents(e);

        ee = man.eDao.getPersonEvents("personID4");
        assertEquals(ee.length, 2);

        man.deleteAll();
    }

    @Test
    public void deleteUserEvents() throws Exception
    {
        Event e0 = new Event("eventID0", "descendant0", "personID0", 0, 0, "country0", "city0", "Birth", 0);
        Event e1 = new Event("eventID1", "descendant1", "personID1", 1, 1, "country1", "city1", "Baptism", 1);
        Event e2 = new Event("eventID2", "descendant1", "personID2", 2, 2, "country2", "city2", "Baptism", 2);
        Event e3 = new Event("eventID3", "descendant1", "personID3", 3, 3, "country3", "city3", "Marriage", 3);
        Event e4 = new Event("eventID4", "descendant4", "personID4", 4, 4, "country4", "city4", "Marriage", 4);
        Event e5 = new Event("eventID5", "descendant4", "personID4", 5, 5, "country5", "city5", "Marriage", 5);
        Event e6 = new Event("eventID6", "descendant6", "personID6", 6, 6, "country6", "city6", "Death", 6);
        Event e[] = new Event[]{e0, e1, e2, e3, e4, e5, e6};
        man.eDao.importEvents(e);

        man.eDao.deleteUserEvents("descendant1");
        assertEquals(man.eDao.getUserEvents("descendant1").length,0);

        man.deleteAll();
    }

    @Test
    public void deletePersonEvents() throws Exception
    {
        Event e0 = new Event("eventID0", "descendant0", "personID0", 0, 0, "country0", "city0", "Birth", 0);
        Event e1 = new Event("eventID1", "descendant1", "personID1", 1, 1, "country1", "city1", "Baptism", 1);
        Event e2 = new Event("eventID2", "descendant1", "personID2", 2, 2, "country2", "city2", "Baptism", 2);
        Event e3 = new Event("eventID3", "descendant1", "personID3", 3, 3, "country3", "city3", "Marriage", 3);
        Event e4 = new Event("eventID4", "descendant4", "personID4", 4, 4, "country4", "city4", "Marriage", 4);
        Event e5 = new Event("eventID5", "descendant4", "personID4", 5, 5, "country5", "city5", "Marriage", 5);
        Event e6 = new Event("eventID6", "descendant6", "personID6", 6, 6, "country6", "city6", "Death", 6);
        Event e[] = new Event[]{e0, e1, e2, e3, e4, e5, e6};
        man.eDao.importEvents(e);

        man.eDao.deletePersonEvents("personID4");
        assertEquals(man.eDao.getPersonEvents("personID4").length,0);

        man.deleteAll();
    }

    @Test
    public void deleteEvent() throws Exception
    {
        Event e1 = new Event("eventID1", "descendant1", "personID1", 1, 1, "country1", "city1", "Baptism", 1);
        man.eDao.addEvent(e1);
        man.eDao.deleteEvent("eventID6");
        try
        {
            man.eDao.getEvent("eventID6");
            assertTrue(false);
        }
        catch(DaoException e)
        {
            assertTrue(true);
        }
        man.deleteAll();
    }

    @Test
    public void deleteAllEvents() throws Exception
    {
        e7 = new Event("eventID7", "descendant7", "personID7", 7, 7, "country7", "city7", "Death", 7);
        man.eDao.addEvent(e7);
        man.eDao.deleteAllEvents();
        try
        {
            man.eDao.getEvent("eventID7");
            assertTrue(false);
        }
        catch(DaoException e)
        {
            assertTrue(true);
        }
        man.deleteAll();
    }
}