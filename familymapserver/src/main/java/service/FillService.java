package service;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import dao.DaoException;
import dao.DaoManager;
import jsonManager.Locations;
import jsonManager.Names;
import model.Person;
import model.User;
import result.FillResult;
import request.FillRequest;


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
            DaoManager man = new DaoManager();
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
        generations = request.getGenerations();
        int gen = 1;
        try
        {
            String username_ = request.getUsername();
            User u = man.uDao.getUser(username_);
            Person p = man.pDao.getPerson(u.getPersonID());
            this.serve(gen,p);
        }
        catch(DaoException e)
        {
            errorResponse = ("Internal Server Error: " + e.getFunction());
        }

        FillResult result = new FillResult(persons, events, errorResponse);
        return result;
    }

    public FillResult serve(int gen, Person child)
    {
        if(gen <= generations)
        {
            Person[] parents = generateParents(child,gen);
            Person father = parents[0];
            Person mother = parents[1];
            generateEvents(father.getPersonID(),mother.getPersonID(),child.getPersonID());
            serve(gen+1,father);
            serve(gen+1,mother);
        }
    }

    /**
     * generates Person objects that can be parents for child
     * adds the parents to the database, and returns them as an array
     *
     * @param child the child of the parents being created
     * @param gen the generation that its on
     */
    private Person[] generateParents(Person child,int gen)
    {
        //if(gen == generations) if this is the case make the parents null
        Random generator = new Random();
        int randomIndex = generator.nextInt(fnames.data.length);
        man.pDao.importPersons(parents);
    }

    /**
     * generates events plausible for a couple with the given child
     * and adds them to the database, also increments the count of
     * events
     *
     * @param fatherID the person ID of the father
     * @param motherID the person ID of the mother
     * @param childID their child's birth year
     */
    private void generateEvents(String fatherID, String motherID, String childID)
    {

    }
}


        //"Fill Service Error: Invalid username";
        //"Fill Service Error: Invalid generations parameter";

        //"Fill Service Error: Internal Server Error";
        //"No Errors";
        //"Fill Service Error: Error Unknown, misuse of setErrorResponse";

