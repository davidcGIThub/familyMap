package result;

/**
 * Created by dc1992 on 10/13/17.
 */

public class ClearResult
{
    /** Success response that will be printed to the user*/
    private String successResponse;
    /** Error response that will be printed to the user*/
    private String errorResponse;

    /**
     * creates a ClearResult object, and creates the success and error response
     *
     * @param successResponse the sucessful response
     * @param errorResponse the error response
     */
    public ClearResult()
    {
        this.successResponse  = null;
        this.errorResponse = null;
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
}
