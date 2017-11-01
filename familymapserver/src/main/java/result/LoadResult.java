package result;

/**
 * Created by dc1992 on 10/13/17.
 */

public class LoadResult
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
     * @param errorResponse type of error (IRD-MV, IRD-IV, ISE, NA)
     */

    public LoadResult(int numUsers, int numPersons, int numEvents, String errorResponse)
    {
        setErrorResponse(errorResponse);
        setSuccessResponse(numUsers,numPersons,numEvents);
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
     * @param errorResponse the type of error (IRD-MV, IRD-IV, ISE, NA)
     */
    public void setErrorResponse(String errorResponse)
    {
        switch (errorResponse)
        {
            case "IRD-MV": this.errorResponse = "Load Service Error: Invalid request data - missing values";
                break;
            case "IRD-IV": this.errorResponse = "Load Service Error: Invalid request data - invalid values";
                break;
            case "ISE": this.errorResponse = "Load Service Error: Internal Server Error";
                break;
            case "NA": this.errorResponse = "No Errors";
                break;
            default: this.errorResponse = "Load Service Error: Error Unknown, misuse of setErrorResponse";
        }
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
