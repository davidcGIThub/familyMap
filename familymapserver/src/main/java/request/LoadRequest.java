package request;

import model.Event;
import model.Person;
import model.User;

/**
 * Created by dc1992 on 10/13/17.
 */

public class LoadRequest extends Request
{
    /** an array of user objects that will be loaded into the database*/
    private User[] users;
    /** an array of person objects that will be loaded into the database*/
    private Person[] persons;
    /** an array of event objects that will be loaded into the database*/
    private Event[] events;

    /**
     *creates a Load Request object
     *
     * @param users  an array of user objects that will be loaded into the database
     * @param persons an array of person objects that will be loaded into the database
     * @param events an array of event objects that will be loaded into the database
     */
    public LoadRequest(User[] users, Person[] persons, Event[] events)
    {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }


    /**
     * gets the user objects that will be loaded into the database
     *
     * @return users
     */
    public User[] getUsers()
    {
        return users;
    }

    /**
     * sets the array of user objects that will be loaded into the database
     *
     * @param users an array of user objects
     */
    public void setUsers(User[] users)
    {
        this.users = users;
    }

    /**
     * gets the person objects that will be loaded into the database
     *
     * @return persons
     */
    public Person[] getPersons()
    {
        return persons;
    }

    /**
     * sets the array of person objects that will be loaded into the database
     *
     * @param persons an array of person objects
     */
    public void setPersons(Person[] persons)
    {
        this.persons = persons;
    }

    /**
     * gets the event objects that will be loaded into the database
     *
     * @return events
     */
    public Event[] getEvents()
    {
        return events;
    }

    /**
     * sets the event objects that will be loaded into the database
     *
     * @param events an array of event objects
     */
    public void setEvents(Event[] events)
    {
        this.events = events;
    }
}
