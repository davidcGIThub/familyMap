package data;

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

}
