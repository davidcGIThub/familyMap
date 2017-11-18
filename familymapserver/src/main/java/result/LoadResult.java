package result;

/**
 * Created by dc1992 on 10/13/17.
 */

public class LoadResult extends Result
{
    /** the success response printed to the user*/
    private String successResponse;
    /** the error response printed to the user*/
    private String errorResponse;


    /**
     * creates a LoadResult object, and creates the error and Success Response
     *
     * @param numUsers the number of users added
     * @param numPersons the number of persons added
     * @param numEvents the number of events added
     * @param errorResponse type of error
     */

    public LoadResult(int numUsers, int numPersons, int numEvents, String errorResponse)
    {
        setErrorResponse(errorResponse);
        setSuccessResponse(numUsers,numPersons,numEvents);
        if(errorResponse.equals("No Errors"))
        {
            this.errorResponse = null;
        }
        else
        {
            this.successResponse = null;
        }
    }

    /**
     * gets the success response
     *
     * @return successResponse
     */
    public String getSuccessResponse()
    {
        return successResponse;
    }

    /**
     * gets the error Response
     *
     * @return errorResponse
     */
    public String getErrorResponse()
    {
        return errorResponse;
    }

    /**
     * sets the error response dependant on the error specified
     *
     * @param errorResponse the type of error
     */
    public void setErrorResponse(String errorResponse)
    {
        this.errorResponse = errorResponse;
    }

    /**
     * sets the Success Response
     *
     * @param numUsers number of users added
     * @param numPersons number of Persons added
     * @param numEvents number of Events added
     */
    public void setSuccessResponse(int numUsers, int numPersons, int numEvents)
    {
        this.successResponse = "Load Service Complete: Successfully added " + numUsers + " users, " + numPersons + " persons, and " + numEvents + " events to the database.";
    }
}

