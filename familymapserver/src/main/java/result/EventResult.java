package result;

/**
 * Created by dc1992 on 10/13/17.
 */

public class EventResult
{
    /** the username associated with this event*/
    private String descendant;
    /** the id for this event*/
    private String eventID;
    /** the person id of the person associated with this event*/
    private String personID;
    /** the latitude of where this event occured*/
    private double latitude;
    /** the longitude of where this event occured*/
    private double longitude;
    /** country where this event occured*/
    private String country;
    /** city where this event occured*/
    private String city;
    /** typer of event that occured*/
    private String eventType;
    /** year when this event occured*/
    private int year;
    /** the error response printed to the user*/
    private String errorResponse;

    /**
     * creates a EventResult object, and creates the error response
     *
     * @param descendant the descendant or username
     * @param eventID the ID for the event retrieved
     * @param personID the person ID associated with the event
     * @param latitude the latitude associated with the event
     * @param longitude the longitude associated with the event
     * @param country the country associated with the event
     * @param city the city associated with the event
     * @param eventType the event type associated with the event
     * @param year the year that the event occured
     * @param errorResponse the type of error
     */
    public EventResult(String descendant, String eventID, String personID, double latitude, double longitude, String country, String city, String eventType, int year, String errorResponse)
    {
        this.descendant = descendant;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
        setErrorResponse(errorResponse);
    }


    /**
     * sets the descendent or username for the event
     *
     * @param descendant username that will be associated with this event
     */
    public void setDescendant(String descendant)
    {
        this.descendant = descendant;
    }

    /**
     * sets the event id result
     *
     * @param eventID event id that will be set for this event
     */
    public void setEventID(String eventID)
    {
        this.eventID = eventID;
    }

    /**
     *sets the person id for the event result
     *
     * @param personID person id that will be associated with this event
     */
    public void setPersonID(String personID)
    {
        this.personID = personID;
    }

    /**
     *sets the latitude for event result
     *
     * @param latitude latitude that will be set for this event
     */
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    /**
     *sets the longitude for the event result
     *
     * @param longitude longitude that will be set for this event
     */
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    /**
     *sets the country for the event result
     *
     * @param country country that will be set for this event
     */
    public void setCountry(String country)
    {
        this.country = country;
    }

    /**
     * sets the city for the event result
     *
     * @param city city that will be sety for this event
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     *sets the eventType for the event result
     *
     * @param eventType type of event
     */
    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    /**
     *sets the year for the event result
     *
     * @param year year that event occured
     */
    public void setYear(int year)
    {
        this.year = year;
    }

    /**
     * gets the descendant of the event result
     *
     * @return
     */
    public String getDescendant()
    {
        return descendant;
    }

    /**
     *sets the event id of the event result
     *
     * @return
     */
    public String getEventID()
    {
        return eventID;
    }

    /**
     * get the person id of the event result
     *
     * @return
     */
    public String getPersonID()
    {
        return personID;
    }

    /**
     *gets the latitude of the event result
     * @return
     */
    public double getLatitude()
    {
        return latitude;
    }

    /**
     * gets the longitude of the event result
     *
     * @return
     */
    public double getLongitude()
    {
        return longitude;
    }

    /**
     * gets the country of the event result
     *
     * @return
     */
    public String getCountry()
    {
        return country;
    }

    /**
     *gets the city of the event result
     *
     * @return
     */
    public String getCity()
    {
        return city;
    }

    /**
     * gets the event type of the event result
     *
     * @return
     */
    public String getEventType()
    {
        return eventType;
    }

    /**
     * gets the year of the event result
     *
     * @return
     */
    public int getYear()
    {
        return year;
    }

    /**
     *gets the error response message of the event result
     * @return
     */
    public String getErrorResponse()
    {
        return errorResponse;
    }

    /**
     * sets the errorResponse dependant on the error specified
     *
     * @param errorResponse the type of error
     */
    public void setErrorResponse(String errorResponse)
    {
        this.errorResponse = errorResponse;
    }
}
