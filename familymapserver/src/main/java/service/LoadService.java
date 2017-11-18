package service;

import dao.DaoException;
import dao.DaoManager;
import model.Event;
import model.Person;
import model.User;
import request.ClearRequest;
import request.LoadRequest;
import result.LoadResult;

/**
 * Created by dc1992 on 10/13/17.
 */

public class LoadService
{
    /** the Dao manager that will be used for this service, containing a connection*/
    private DaoManager man;
    /** the error response that will be input to the result*/
    private String errorResponse;

    public LoadService()
    {
        try
        {
            man = new DaoManager();
            errorResponse = "No Errors";
        }
        catch (DaoException e)
        {
            errorResponse = ("Internal Server Error: " + e.getFunction());
        }
    }


    /**
     * Clears all data from the database and then loads the posted user, person, and event into the database
     *
     * @param request LoadRequest object containing the user, person and event data
     * @return LoadResult object stating if the service was successful
     */
    public LoadResult serve(LoadRequest request)
    {

        Person[] persons = request.getPersons();
        Event[] events = request.getEvents();
        User[] users = request.getUsers();

        LoadResult result = null;
        int numPersons = 0;
        int numEvents = 0;
        int numUsers = 0;

        ClearRequest clearRequest = new ClearRequest();
        ClearService clearService = new ClearService();
        clearService.serve(clearRequest);

        if (errorResponse.equals("No Errors"))
        {
            if(persons == null || events == null || users == null)
            {
                errorResponse = "Load Service Error: Invalid Request Data - missing values";
            }
            else
            {
                try
                {
                    man.pDao.importPersons(persons);
                    man.eDao.importEvents(events);
                    man.uDao.importUsers(users);
                    numPersons = persons.length;
                    numEvents = events.length;
                    numUsers = users.length;
                }
                catch (DaoException e)
                {
                    errorResponse = ("Internal Server Error: " + e.getFunction());
                }
            }

        }
        result = new LoadResult(numUsers,numPersons,numEvents,errorResponse);
        return result;
    }
}

// "Load Service Error: Invalid request data - invalid values";

