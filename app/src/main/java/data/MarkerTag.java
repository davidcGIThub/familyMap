package data;

import model.Event;
import model.Person;

/**
 * Created by david on 12/11/17.
 */

public class MarkerTag
{
    public Person person;
    public Event event;

    public MarkerTag(Person person,Event event)
    {
        this.person = person;
        this.event = event;
    }
}
