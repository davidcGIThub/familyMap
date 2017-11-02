package dao;
import java.sql.*;

import model.Event;

/**
 * Created by dc1992 on 10/12/17.
 */

public class EventDao
{
    private Connection c;

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
    public void addEvent(Event event) throws DaoException
    {
        Statement stmt = null;
        try
        {
            stmt = c.createStatement();
            String sql = "INSERT INTO Events (EventID, Descendant, PersonID, Latitude, Longitude, Country, City, EventType, Year) " +
                    "VALUES ('" + event.getEventID() + "', '" + event.getDescendant() + "', '" + event.getPersonID()  +
                    "', '" + event.getLatitude() + "', '" + event.getLongitude() + "', '" + event.getCountry()  +
                    "', '" + event.getCity() + "', '" + event.getEventType() + "', '" + event.getYear() + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            //c.commit(); it is in autocommit mode
        }
        catch ( Exception e )
        {
            throw new DaoException("addEvent(): " + e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /**
     * imports an array of events into the database
     *
     * @param events array of events
     */
    public void importEvents(Event[] events) throws DaoException
    {   try
        {
              for (int i = 0; i < events.length; i++)
              {
                  this.addEvent(events[i]);
              }
        }
        catch(DaoException e)
        {
            throw new DaoException("importEvents() - " + e.getFunction());
        }
    }

    /**
     * gets all of the events for the user
     *
     * @param username the username of the user
     * @return all of the events associated with that user
     */

    public  Event[] getUserEvents(String username) throws DaoException
    {
        Event[] events= null;
        PreparedStatement prepared = null;
        try {
            prepared = c.prepareStatement("SELECT * FROM Events WHERE Descendant = ?;");
            prepared.setString(1, username);
            ResultSet rs = prepared.executeQuery();
            int rowcount = 0;
            while(rs.next())
            {
                rowcount++;
            }
            rs = prepared.executeQuery();
            events = new Event[rowcount];
            for(int i = 0; i < rowcount; i++)
            {
                rs.next();
                String eventID_ = rs.getString("EventID");
                String descendant_ = rs.getString("Descendant");
                String personID_ = rs.getString("PersonID");
                double latitude_ = rs.getDouble("Latitude");
                double longitude_ = rs.getDouble("Longitude");
                String country_ = rs.getString("Country");
                String city_ = rs.getString("City");
                String eventType_ = rs.getString("EventType");
                int year_ = rs.getInt("Year");
                Event event = new Event(eventID_, descendant_, personID_, latitude_, longitude_, country_, city_, eventType_, year_);
                events[i] = event;
            }
            rs.close();
            prepared.close();
        } catch ( Exception e ) {
            throw new DaoException("getUserEvents(): " + e.getClass().getName() + ": " + e.getMessage() );
        }
        return events;
    }

    /**
     * gets all of the events associated with that person
     *
     * @param personID ID of the person
     * @return array of events
     */
    public Event[] getPersonEvents(String personID) throws DaoException
    {
        Event[] events= null;
        PreparedStatement prepared = null;
        try {
            prepared = c.prepareStatement("SELECT * FROM Events WHERE PersonID = ?;");
            prepared.setString(1, personID);
            ResultSet rs = prepared.executeQuery();
            int rowcount = 0;
            while(rs.next())
            {
                rowcount++;
            }
            rs = prepared.executeQuery();
            events = new Event[rowcount];
            for(int i = 0; i < rowcount; i++)
            {
                rs.next();
                String eventID_ = rs.getString("EventID");
                String descendant_ = rs.getString("Descendant");
                String personID_ = rs.getString("PersonID");
                double latitude_ = rs.getDouble("Latitude");
                double longitude_ = rs.getDouble("Longitude");
                String country_ = rs.getString("Country");
                String city_ = rs.getString("City");
                String eventType_ = rs.getString("EventType");
                int year_ = rs.getInt("Year");
                Event event = new Event(eventID_, descendant_, personID_, latitude_, longitude_, country_, city_, eventType_, year_);
                events[i] = event;
            }
            rs.close();
            prepared.close();
        } catch ( Exception e ) {
            throw new DaoException("getPersonEvents(): " + e.getClass().getName() + ": " + e.getMessage() );
        }
        return events;
    }

    /**
     * gets the specified event
     *
     * @param eventID ID for that event
     * @return the event object
     */
    public Event getEvent(String eventID) throws DaoException
    {
        Event event = null;
        PreparedStatement prepared = null;
        try
        {
            prepared = c.prepareStatement("SELECT * FROM Events WHERE EventID = ?;");
            prepared.setString(1, eventID);
            ResultSet rs = prepared.executeQuery();
            String eventID_ = rs.getString("EventID");
            String descendant_ = rs.getString("Descendant");
            String personID_ = rs.getString("PersonID");
            double latitude_ = rs.getDouble("Latitude");
            double longitude_ = rs.getDouble("Longitude");
            String country_ = rs.getString("Country");
            String city_ = rs.getString("City");
            String eventType_ = rs.getString("EventType");
            int year_ = rs.getInt("Year");
            event = new Event(eventID_, descendant_, personID_, latitude_, longitude_, country_, city_, eventType_, year_);
            rs.close();
            prepared.close();
        }
        catch ( Exception e )
        {
            throw new DaoException("getEvent(): " + e.getClass().getName() + ": " + e.getMessage() );
        }
        return event;
    }

    /**
     * deletes all of the events associated with that user
     *
     * @param username the username of the user
     */
    public void deleteUserEvents(String username) throws DaoException
    {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE from Events where Descendant='" + username + "';";
            stmt.executeUpdate(sql);
            //c.commit(); autocommit mode
        }
        catch ( Exception e )
        {
            throw new DaoException("deleteUserEvents(): " + e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /**
     * deletes all of the events associated with that person
     *
     * @param personID the ID for that person
     */
    public void deletePersonEvents(String personID) throws DaoException
    {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE from Events where personID='" + personID + "';";
            stmt.executeUpdate(sql);
            //c.commit(); autocommit mode
        } catch ( Exception e ) {
            throw new DaoException("deletePersonEvents(): " + e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /**
     * deletes the specified event
     *
     * @param eventID the id for that event
     */
    public void deleteEvent(String eventID) throws DaoException
    {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE from Events where eventID='" + eventID + "';";
            stmt.executeUpdate(sql);
            //c.commit(); autocommit mode
        } catch ( Exception e ) {
            throw new DaoException("deleteEvent(): " + e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /**
     * deletes all of the events in the database
     */
    public void deleteAllEvents() throws DaoException
    {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE from Events;";
            stmt.executeUpdate(sql);
            //c.commit(); autocommit mode
        } catch ( Exception e ) {
            throw new DaoException("deleteAllEvents(): " + e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
