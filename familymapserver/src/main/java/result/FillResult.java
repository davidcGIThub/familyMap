package result;

/**
 * Created by dc1992 on 10/13/17.
 */

public class FillResult
{
    /** the success Response printed to the user*/
    private String successResponse;
    /** the error response printed to the user*/
    private String errorResponse;

    /**
     * creates a FillResult object, and creates the error and success response
     *
     */
    public FillResult()
    {
        this.successResponse = null;
        this.errorResponse = null;
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
}
