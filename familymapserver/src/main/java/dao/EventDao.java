package dao;
import java.sql.*;

import model.Event;

/**
 * Created by dc1992 on 10/12/17.
 */

public class EventDao
{
    Connection c;

    /**
     * creates and EventDao object
     *
     */
    public EventDao(Connection c)
    {
        this.c = c;
    }

    /**
     *creates and event in the database
     *
     * @param event event object being created
     */
    public void createEvent(Event event)
    {

    }

    /**
     * imports an array of events into the database
     *
     * @param events array of events
     */
    public void importEvents(Event[] events)
    {

    }

    /**
     * gets all of the events for the user
     *
     * @param username the username of the user
     * @return all of the events associated with that user
     */
    public  Event[] getUserEvents(String username)
    {
        Event[] events= null;
        return events;
    }

    /**
     * gets all of the events associated with that person
     *
     * @param personID ID of the person
     * @return array of events
     */
    public Event[] getPersonEvents(String personID)
    {
        Event[] events = null;
        return events;
    }

    /**
     * gets the specified event
     *
     * @param eventID ID for that event
     * @return the event object
     */
    public Event getEvent(String eventID)
    {
        Event event = null;
        return event;
    }

    /**
     * deletes all of the events associated with that user
     *
     * @param username the username of the user
     */
    public void deleteUserEvents(String username)
    {

    }

    /**
     * deletes all of the events associated with that person
     *
     * @param personID the ID for that person
     */
    public void deletePersonEvents(String personID)
    {

    }

    /**
     * deletes the specified event
     *
     * @param eventID the id for that event
     */
    public void deleteEvent(String eventID)
    {

    }

    /**
     * deletes all of the events in the database
     */
    public void deleteAllEvents()
    {

    }

}
