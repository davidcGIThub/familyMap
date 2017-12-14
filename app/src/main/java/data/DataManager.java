package data;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;
import android.graphics.Color;
import model.Event;
import model.Person;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NONE;

/**
 * Created by david on 11/17/17.
 */

public class DataManager {
    private static DataManager instance = null;
    public Context ORIGINAL_CONTEXT;
    public Event[] allEvents;
    public Person[] allPersons;
    public Event[] events;
    public Person[] persons;
    public String username;
    public String password;
    public String authToken;
    public String serverHost;
    public String serverPort;
    public String userPersonID;
    public int marriageLineColor;
    public int familyLineColor;
    public int lifeEventsColor;
    public boolean marriageLinesOn;
    public boolean familyLinesOn;
    public boolean lifeLinesOn;
    public int mapType;
    public boolean malesFiltered;
    public boolean femalesFiltered;
    public boolean fatherFiltered;
    public boolean motherFiltered;
    public boolean baptismFiltered;
    public boolean birthFiltered;
    public boolean marriageFiltered;
    public boolean deathFiltered;



    public DataManager()
    {
        serverHost = null;
        serverPort = null;
        marriageLineColor = Color.YELLOW;
        familyLineColor = Color.GREEN;
        lifeEventsColor = Color.BLUE;
        mapType = MAP_TYPE_NORMAL;
        marriageLinesOn = false;
        familyLinesOn = false;
        lifeLinesOn = false;
        malesFiltered = false;
        femalesFiltered = false;
        fatherFiltered = false;
        motherFiltered = false;
        baptismFiltered = false;
        birthFiltered = false;
        marriageFiltered = false;
        deathFiltered = false;
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

    public Event getEvent(String eventID)
    {
        Event foundEvent = null;

        for(int i = 0; i < events.length; i++)
        {
            if(events[i].getEventID().equals(eventID))
            {
                foundEvent = events[i];
            }
        }
        return foundEvent;
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

        ArrayList<Person> family= new ArrayList<Person>();

        if(!person.getFather().equals("null"))
        {
            family.add(getPerson(person.getFather()));
        }
        if(!person.getMother().equals("null"))
        {
            family.add(getPerson(person.getMother()));
        }
        if(!person.getSpouse().equals("null"))
        {
            family.add(getPerson(person.getSpouse()));
        }

        for(int i = 0; i < persons.length; i++)
        {
            if(persons[i].getFather().equals(person.getPersonID()) || persons[i].getMother().equals(person.getPersonID()))
            {
               family.add(persons[i]);
            }
        }

        Person[] familyReturned = new Person[family.size()];
        family.toArray(familyReturned);

        return familyReturned;
    }

    public Event getRelationsEarliestEvent(Event event, String relation)
    {
        Person person = getPerson(event.getPersonID());
        String relationID;
        switch(relation)
        {
            case("Father"):
                relationID = person.getFather();
                break;
            case("Mother"):
                relationID = person.getMother();
                break;
            case("Spouse"):
                relationID = person.getSpouse();
                break;
            default:
                relationID = person.getPersonID();
        }

        Event eventRelation = null;
        if(!relationID.equals("null"))
        {
            for(int i = 0; i < events.length; i++) {
                if (events[i].getPersonID().equals(relationID))
                {
                    if(eventRelation != null && events[i].getYear() < event.getYear())
                    {
                        eventRelation = events[i];
                    }
                    else if (eventRelation == null)
                    {
                        eventRelation = events[i];
                    }
                }
            }
        }
        return eventRelation;
    }

    public void refresh()
    {

        events = null;
        persons = null;
        authToken = null;
        userPersonID = null;
        serverHost = null;
        serverPort = null;
        password = null;
        username = null;
        marriageLineColor = Color.YELLOW;
        familyLineColor = Color.GREEN;
        lifeEventsColor = Color.BLUE;
        mapType = MAP_TYPE_NORMAL;
        marriageLinesOn = false;
        familyLinesOn = false;
        lifeLinesOn = false;
        marriageLinesOn = false;
        familyLinesOn = false;
        lifeLinesOn = false;
        malesFiltered = false;
        femalesFiltered = false;
        fatherFiltered = false;
        motherFiltered = false;
        baptismFiltered = false;
        birthFiltered = false;
        marriageFiltered = false;
        deathFiltered = false;

    }

    public void resync()
    {
        events = null;
        persons = null;
        authToken = null;
        userPersonID = null;
    }

    public int getColor(String type)
    {
        int color;
        switch (type) {
            case ("Black"):
                color = Color.BLACK;
                break;
            case ("Blue"):
                color = Color.BLUE;
                break;
            case ("Green"):
                color = Color.GREEN;
                break;
            case ("Red"):
                color = Color.RED;
                break;
            case ("Yellow"):
                color = Color.YELLOW;;
                break;
            case ("White"):
                color = Color.WHITE;;
                break;
            default:
                color = Color.BLACK;;
                break;
        }
        return color;
    }

    public void setMapType(String type)
    {
        DataManager dman = DataManager.getInstance();
        switch (type) {
            case ("Normal"):
                mapType = MAP_TYPE_NORMAL;
                break;
            case ("Hybrid"):
                mapType = MAP_TYPE_HYBRID;
                break;
            case ("Satellite"):
                mapType = MAP_TYPE_SATELLITE;
                break;
            case ("Terrain"):
                mapType = MAP_TYPE_TERRAIN;
                break;
            case ("None"):
                mapType = MAP_TYPE_NONE;
                break;
            default:
                mapType = MAP_TYPE_NORMAL;
                break;
        }
    }

    public Person[] searchPersons(String text)
    {
        ArrayList<Person> peopleFound = new ArrayList<Person>();
        for(int i = 0; i < persons.length; i++)
        {

            if(persons[i].getFirstName().toLowerCase().contains(text.toLowerCase()) || persons[i].getLastName().toLowerCase().contains(text.toLowerCase()))
            {
                peopleFound.add(persons[i]);
            }
        }
        Person[] personsFound = new Person[peopleFound.size()];
        peopleFound.toArray(personsFound);
        return personsFound;
    }

    public Event[] searchEvents(String text)
    {
        ArrayList<Event> eventsFound = new ArrayList<Event>();
        for(int i = 0; i < events.length; i++)
        {
            String country = events[i].getCountry().toLowerCase();
            String city = events[i].getCity().toLowerCase();
            String year = Integer.toString(events[i].getYear());
            String type = events[i].getEventType().toLowerCase();


            if(country.contains(text.toLowerCase()) || city.contains(text.toLowerCase()) || year.contains(text.toLowerCase()) || type.contains(text.toLowerCase()))
            {
                eventsFound.add(events[i]);
            }
        }
        Event[] arrEventsFound = new Event[eventsFound.size()];
        eventsFound.toArray(arrEventsFound);
        return arrEventsFound;
    }

    public void filter()
    {
        events = allEvents;
        if(malesFiltered)
        {
            filterGender("m");
        }
        if(femalesFiltered)
        {
            filterGender("f");
        }
        if(fatherFiltered)
        {
            filterFatherSide();
        }
        if(motherFiltered)
        {
            filterMotherSide();
        }
        if(baptismFiltered)
        {
            filterEventType("Baptism");
        }
        if(birthFiltered)
        {
            filterEventType("Birth");
        }
        if(marriageFiltered)
        {
            filterEventType("Marriage");
        }
        if(deathFiltered)
        {
            filterEventType("Death");
        }

    }


    public void filterEventType(String eventType)
    {
        ArrayList<Event> filteredEvents = new ArrayList<Event>();
        for(int i = 0; i < events.length; i++)
        {
            if(!events[i].getEventType().toLowerCase().equals(eventType.toLowerCase()))
            {
                filteredEvents.add(events[i]);
            }
        }
        Event[] temp = new Event[filteredEvents.size()];
        filteredEvents.toArray(temp);
        events = temp;
    }

    public void filterGender(String gender)
    {
        ArrayList<Event> filteredEvents = new ArrayList<Event>();
        for(int i = 0; i < events.length; i++)
        {
            Person per = getPerson(events[i].getPersonID());
            if(!per.getGender().equals(gender))
            {
                filteredEvents.add(events[i]);
            }
        }
        Event[] temp = new Event[filteredEvents.size()];
        filteredEvents.toArray(temp);
        events = temp;
    }

    public void filterFatherSide()
    {
        Person user = getPerson(userPersonID);
        filterPersonEvents(user.getFather());
        filterAncestors(user.getFather());
    }

    public void filterMotherSide()
    {
        Person user = getPerson(userPersonID);
        filterPersonEvents(user.getMother());
        filterAncestors(user.getMother());
    }

    public void filterAncestors(String personID)
    {
        Person per = getPerson(personID);
        if(!per.getFather().equals("null"))
        {
            filterPersonEvents(per.getFather());
            filterAncestors(per.getFather());
        }
        if(!per.getMother().equals("null"))
        {
            filterPersonEvents(per.getMother());
            filterAncestors(per.getMother());
        }

    }


    public void filterPersonEvents(String personID)
    {
        ArrayList<Event> filteredEvents = new ArrayList<Event>();
        for(int i = 0; i < events.length; i++)
        {

            if(!events[i].getPersonID().equals(personID))
            {
                filteredEvents.add(events[i]);
            }
        }
        Event[] temp = new Event[filteredEvents.size()];
        filteredEvents.toArray(temp);
        events = temp;

    }



}
