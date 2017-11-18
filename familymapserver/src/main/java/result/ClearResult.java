package result;

/**
 * Created by dc1992 on 10/13/17.
 */

public class ClearResult extends Result
{
    /** Success response that will be printed to the user*/
    private String successResponse;
    /** Error response that will be printed to the user*/
    private String errorResponse;


    /**
     * creates a ClearResult object
     *
     * @param errorResponse this is the type of error
     */
    public ClearResult(String errorResponse)
    {
        this.successResponse  = "Clear Service Complete: Clear succeeded";
        this.errorResponse = errorResponse;
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
     * gets the successResponse
     *
     * @return
     */
    public String getSuccessResponse()
    {
        return successResponse;
    }

    /**
     * gets the error Response
     *
     * @return
     */
    public String getErrorResponse()
    {
        return errorResponse;
    }

    /**
     * sets the error response dependant on the type of error specified
     *
     * @param errorResponse type of error
     */
    public void setErrorResponse(String errorResponse)
    {
        this.errorResponse = errorResponse;
    }
}
