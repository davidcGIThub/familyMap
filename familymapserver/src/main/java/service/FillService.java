package service;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

import dao.DaoException;
import dao.DaoManager;
import jsonManager.Location;
import jsonManager.Locations;
import jsonManager.Names;
import model.Event;
import model.Person;
import model.User;
import request.FillRequest;
import result.FillResult;


/**
 * Created by dc1992 on 10/12/17.
 */

public class FillService
{
    /** Names object containing an array of female names */
    private Names fnames;
    /** Names object containing an array of male names */
    private Names mnames;
    /** Names object containing an array of surnames */
    private Names snames;
    /** Locations object containing an array of (Countries, Citys, and Lat, Long coordinates */
    private Locations locations;
    /** Temporary count of Persons that have been created in the fill service*/
    private int persons;
    /** Temporary count of Events that have been created in the fill service*/
    private int events;
    /** The number of generations that will be created*/
    private int generations;
    /** the Dao manager that will be used for this service, containing a connection*/
    private DaoManager man;
    /** the error response that will be input to the result*/
    private String errorResponse;
    /** username of the user making the request*/
    private String username;

    /**
     * creates FillService object, initializes data
     * retrieves the data to generate random names and locations
     */
    public FillService()
    {

        FileReader fileReader;
        try
        {
            Gson gson = new Gson();
            fileReader = new FileReader("json/fnames.json");
            this.fnames = gson.fromJson(fileReader, Names.class);
            fileReader = new FileReader("json/mnames.json");
            this.mnames = gson.fromJson(fileReader, Names.class);
            fileReader = new FileReader("json/snames.json");
            this.snames = gson.fromJson(fileReader, Names.class);
            fileReader = new FileReader("json/locations.json");
            this.locations = gson.fromJson(fileReader, Locations.class);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        this.persons = 0;
        this.events = 0;
        this.generations = 0;

        try
        {
            man = new DaoManager();
            errorResponse = "No Errors";
        }
        catch(DaoException e)
        {
            errorResponse = ("Internal Server Error: " + e.getFunction());
        }
    }

    /**
     * Generates data that will be added to the database for the specified user
     *
     * @param request fill request object containing the authorization token and number of generations for the user
     * @return FillResult object stating if the service was successful
     */
    public FillResult serve(FillRequest request)
    {
        this.username = request.getUsername();
        this.generations = request.getGenerations();
        FillResult result = null;
        if (errorResponse.equals("No Errors"))
        {
            if (!checkErrors(username, generations))
            {
                result = new FillResult(persons, events, errorResponse);
                return result;
            }
            int gen = 1;
            try
            {
                User u = man.uDao.getUser(username);
                Person p = man.pDao.getPerson(u.getPersonID()); //saves the person object about to be deleted
                man.pDao.deleteUserPersons(username); // first delete all information related to that user
                man.eDao.deleteUserEvents(username); // deletes all information related to that user
                man.pDao.addPerson(p);
                generateUserEvents(p.getPersonID());
                serve(gen,p);
            }
            catch (DaoException e)
            {
                errorResponse = ("Internal Server Error: " + e.getFunction());
            }
        }
        result = new FillResult(persons, events, errorResponse);
        return result;
    }

    public void serve(int gen, Person child)
    {
        if(gen <= generations)
        {
            Person[] parents = generateParents(child);
            Person father = parents[0];
            Person mother = parents[1];
            generateParentEvents(father.getPersonID(),child.getPersonID());
            generateParentEvents(mother.getPersonID(),child.getPersonID());
            serve(gen+1,father);
            serve(gen+1,mother);
        }
    }

    /**
     * generates Person objects that can be parents for child
     * adds the parents to the database, and returns them as an array
     * also increments the persons added by 2
     *
     * @param child the child of the parents being created
     */
    private Person[] generateParents(Person child)
    {
        //select descendant
        String descendant = username;
        //create father ID
        UUID uuid = UUID.randomUUID();
        String fatherID = uuid.toString();
        //create fathers first name
        Random generator = new Random();
        int randomIndex = generator.nextInt(mnames.data.length);
        String fatherName = mnames.data[randomIndex];
        //create fathers last name
        String fatherLastName = child.getLastName();
        //create mother ID
        uuid = UUID.randomUUID();
        String motherID = uuid.toString();
        //create mothers first name
        randomIndex = generator.nextInt(fnames.data.length);
        String motherName = fnames.data[randomIndex];
        //create mothers last name
        randomIndex = generator.nextInt(snames.data.length);
        String motherLastName = snames.data[randomIndex];
        //assign spouses
        String fatherSpouse = motherID;
        String motherSpouse = fatherID;
        //create person objects
        Person father = new Person(fatherID,username,fatherName,fatherLastName,"m",null,null,fatherSpouse);
        Person mother = new Person(motherID,username,motherName,motherLastName,"f",null,null,motherSpouse);
        Person[] parents = new Person[]{father,mother};
        //upload persons to database
        try
        {
            man.pDao.updateFamilyMember("Father",fatherID,child.getPersonID());
            man.pDao.updateFamilyMember("Mother",motherID,child.getPersonID());
            man.pDao.importPersons(parents);
            this.persons++;
        }
        catch(DaoException e)
        {
            errorResponse = ("Internal Server Error: " + e.getFunction());
        }

        return parents;
    }

    /**
     * generates events plausible for a parent with the given child
     * and adds them to the database,
     *
     * @param parentID the person ID of the parent
     * @param childID their child's birth year
     */
    private void generateParentEvents(String parentID, String childID)
    {
        try
        {
            //determine childs birth year
            int childBirthYear = 0;
            Event[] events = man.eDao.getPersonEvents(childID);
            for(int i = 0; i < events.length; i++)
            {
                if (events[i].getEventType().equals("Birth"))
                {
                    childBirthYear = events[i].getYear();
                }
            }
            //create parent birth year
            Random generator = new Random();
            int randomIndex = generator.nextInt(22);
            randomIndex = randomIndex + 18;
            int parentBirthYear = childBirthYear - randomIndex;
            //generate events
            generateEvent(parentID,"Birth",parentBirthYear);
            generateEvent(parentID,"Baptism",parentBirthYear);
            generateEvent(parentID,"Marriage",parentBirthYear);
            //determine if dead
            User u = man.uDao.getUser(username);
            if(childID.equals( u.getPersonID()))
            {
                randomIndex = generator.nextInt(10);
                if (randomIndex < 5)
                {
                    generateEvent(parentID,"Death",parentBirthYear);
                }
            }
            else
            {
                generateEvent(parentID,"Death",parentBirthYear);
            }
        }
        catch(DaoException e)
        {
            errorResponse = ("Internal Server Error: " + e.getFunction());
        }
    }

    /**
     * generates events for the person object of the user
     * @param personID
     */
    private void generateUserEvents(String personID)
    {
        // make birthday reasonable
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Random generator = new Random();
        int randomIndex = generator.nextInt(80);
        year = year - randomIndex;
        // make events
        generateEvent(personID, "Birth", year);
        generateEvent(personID,"Baptism", year);
        //generateEvent(personID,"Marriage",year);
    }

    /**
     * generates events for a person with specified person ID
     * also increments the count of the events
     *
     * @param personID the person ID for which the event is being created
     * @param eventType event type being created "Birth", "Baptism", "Marriage", "Death"
     * @param birthYear the year this person was born
     */
    private void generateEvent(String personID, String eventType, int birthYear)
    {
        int tempBirth = birthYear;
        Event event = null;
        int year = 0;
        //create eventID
        UUID uuid = UUID.randomUUID();
        String eventID = uuid.toString();
        //select descendant
        String descendant = username;
        //create location
        Random generator = new Random();
        int randomIndex = generator.nextInt(locations.data.length);
        Location location = locations.data[randomIndex];
        double longitude = location.longitude;
        double latitude = location.latitude;
        String country = location.country;
        String city = location.city;
        // create dates for events
        try
        {
            switch (eventType)
            {
                case "Birth":
                    year = 0;
                    break;
                case "Baptism":
                    randomIndex = generator.nextInt(8);
                    year = randomIndex + 1;
                    break;
                case "Marriage":
                    Person p = man.pDao.getPerson(personID);
                    String spouse = p.getSpouse();
                    randomIndex = generator.nextInt(40);
                    year = randomIndex + 18;
                    if (spouse != null)
                    {
                        Event[] events = man.eDao.getPersonEvents(spouse);
                        for (int i = 0; i < events.length; i++)
                        {
                            if (events[i].getEventType().equals("Marriage"))
                            {
                                longitude = events[i].getLongitude();
                                latitude = events[i].getLatitude();
                                country = events[i].getCountry();
                                city = events[i].getCity();
                                year = events[i].getYear();
                                tempBirth = 0;
                            }
                        }
                    }
                    break;
                case "Death":
                    randomIndex = generator.nextInt(40);
                    year = randomIndex + 60;
                    break;
                default:
                    System.err.println("Error: Invalid Event Type");
            }
            //initalize event
            event = new Event(eventID, descendant, personID, latitude, longitude, country, city, eventType, tempBirth + year);
            //add event to database
            man.eDao.addEvent(event);
            this.events++;
        }
        catch (DaoException e)
        {
            errorResponse = ("Internal Server Error: " + e.getFunction());
        }
    }

    /**
     * checks to see if inputs the the service are valid, and assigns the error response
     *
     * @param username the username requesting the service
     * @param gen number of generations that will be created, but be nonegative
     * @return valid
     */
    private boolean checkErrors(String username, int gen)
    {
        boolean valid = false;
        try
        {
            valid = man.uDao.checkContains("Username", username);
            if(!valid)
            {
                errorResponse = "Fill Service Error: Invalid username";
                return valid;
            }
        }
        catch(DaoException e)
        {
            errorResponse = ("Internal Server Error: " + e.getFunction());
        }
        if(gen < 0)
        {
            valid = false;
            errorResponse = "Fill Service Error: Invalid generations parameter";
        }
        return valid;
    }
}


