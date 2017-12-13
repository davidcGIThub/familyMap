package data;

import java.util.ArrayList;

import model.Event;
import model.Person;

/**
 * Created by david on 11/17/17.
 */

public class DataManager {
    private static DataManager instance = null;
    public Event[] events;
    public Person[] persons;
    public String authToken;
    public String serverHost;
    public String serverPort;
    public String userPersonID;

    public DataManager()
    {
        serverHost = null;
        serverPort = null;
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public Person getPerson(String personID)
    {
        Person person = null;
        for(int i = 0; i < persons.length; i++)
        {
            if(persons[i].getPersonID().equals(personID))
            {
                person = persons[i];
                break;
            }
        }
        return person;
    }

    public Event[] getEvents(String personID)
    {
        ArrayList<Event> eventsFound= new ArrayList<Event>();
        Event[] eventsReturned;

        for(int i = 0; i < events.length; i++)
        {
            if(events[i].getPersonID().equals(personID))
            {
                eventsFound.add(events[i]);
            }
        }

        eventsReturned = new Event[eventsFound.size()];
        eventsFound.toArray(eventsReturned);

        return eventsReturned;
    }

    public Person[] getFamily(Person person)
    {

        ArrayList<Person> children= new ArrayList<Person>();
        Person child = null;


        for(int i = 0; i < persons.length; i++)
        {
            if(persons[i].getFather().equals(person.getPersonID()) || persons[i].getMother().equals(person.getPersonID()))
            {
               child = persons[i];
            }
        }

        Person[] family = new Person[4];
        if(person.getFather() != null)
        {
            family[0] = getPerson(person.getFather());
        }
        if(person.getMother() != null)
        {
            family[1] = getPerson(person.getMother());
        }
        if(person.getSpouse() != null)
        {
            family[2] = getPerson(person.getSpouse());
        }
        if(child != null)
        {
            family[3] = child;
        }

        return family;
    }

}
