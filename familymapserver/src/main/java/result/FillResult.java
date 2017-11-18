package result;

/**
 * Created by dc1992 on 10/13/17.
 */

public class FillResult extends Result
{
    /** the success Response printed to the user*/
    private String successResponse;
    /** the error response printed to the user*/
    private String errorResponse;

    /**
     * creates a FillResult object, and creates the error and success response
     *
     * @param numPersons the number of persons added
     * @param numEvents the number of events added
     * @param errorResponse the type of error
     */
    public FillResult(int numPersons, int numEvents, String errorResponse)
    {
        this.errorResponse = errorResponse;
        setSuccessResponse(numPersons,numEvents);
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
     * gets the Success Response for this event
     *
     * @return successResponse
     */
    public String getSuccessResponse() {
        return successResponse;
    }

    /**
     *gets the error Response for this result
     *
     * @return errorResponse
     */
    public String getErrorResponse() {
        return errorResponse;
    }

    /**
     *  creates the success response
     *
     * @param numPersons number of persons added to the database from the service
     * @param numEvents number of events added to the database from the service
     */
    public void setSuccessResponse(int numPersons, int numEvents)
    {
        this.successResponse = "Fill Service Complete: Successfully added " + numPersons + " persons and " + numEvents +  " events to the database.";
    }

    /**
     * creates an error response dependant on what error is specified
     *
     * @param errorResponse the type of error
     */
    public void setErrorResponse(String errorResponse)
    {
        this.errorResponse = errorResponse;
    }
}
