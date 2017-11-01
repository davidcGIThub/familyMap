package model;

/**
 * Created by dc1992 on 10/12/17.
 */

public class Event
{
    /** unique ID for this event */
    private String eventID;
    /** the descendant of the person, or associated username*/
    private String descendant;
    /** the person ID associated with this event*/
    private String personID;
    /** the latitude that this event occured*/
    private double latitude;
    /** the longitude of where this event occured*/
    private double longitude;
    /** the country that this event occured*/
    private String country;
    /** the city that this event occured*/
    private String city;
    /** the year that this event occured*/
    private int year;
    /** the type of event*/
    private String eventType;

    /**
     * creates an event object
     *
     * @param eventID       event ID being set
     * @param descendant    descedant  being set
     * @param personID      person ID  being set
     * @param latitude      latitude being set
     * @param longitude     longitude being set
     * @param country       country being set
     * @param city          city being set
     * @param year          year being set
     * @param eventType     event type being set
     */

    public Event(String eventID, String descendant, String personID, double latitude, double longitude, String country, String city, String eventType, int year)
    {
        this.eventID = eventID;
        this.descendant = descendant;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.year = year;
        this.eventType = eventType;
    }


    /**
     * sets the unique event ID for this event
     *
     * @param eventID
     */
    public void setEventID(String eventID)
    {
        this.eventID = eventID;
    }

    /**
     * sets the associated username that this event belongs to
     *
     * @param descendant
     */
    public void setDescendant(String descendant)
    {
        this.descendant = descendant;
    }

    /**
     * sets the associated person ID for this event
     *
     * @param personID
     */
    public void setPersonID(String personID)
    {
        this.personID = personID;
    }

    /**
     * sets the latitude for which this event occured
     *
     * @param latitude
     */
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    /**
     * sets the longitude for this event
     *
     * @param longitude
     */
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    /**
     *sets the country for this event
     *
     * @param country
     */
    public void setCountry(String country)
    {
        this.country = country;
    }

    /**
     *sets the city for this event
     *
     * @param city
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     *sets the year for this event
     * @param year
     */
    public void setYear(int year)
    {
        this.year = year;
    }

    /**
     * sets the event type for this event
     * @param eventType
     */
    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }


    /**
     * gets the event ID for this event
     *
     * @return event ID
     */
    public String getEventID()
    {
        return eventID;
    }

    /**
     *gets the descendant username
     *
     * @return desendant
     */
    public String getDescendant()
    {
        return descendant;
    }

    /**
     *gets the person ID assocaited with this event
     *
     * @return person ID
     */
    public String getPersonID()
    {
        return personID;
    }

    /**
     * gets the latitude for this event
     *
     * @return latitude
     */
    public double getLatitude()
    {
        return latitude;
    }

    /**
     * gets the longitude for this event
     *
     * @return longitude
     */
    public double getLongitude()
    {
        return longitude;
    }

    /**
     * gets the country for this event
     *
     * @return country
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * gets the city for this event
     *
     * @return city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * gets the year for this event
     *
     * @return year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * gets the event type
     *
     * @return event type
     */
    public String getEventType()
    {
        return eventType;
    }
}
