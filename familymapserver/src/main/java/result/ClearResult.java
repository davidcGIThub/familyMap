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
     * creates a ClearResult object
     */
    public ClearResult()
    {
        this.successResponse  = "Clear Service Complete: Clear succeeded";
        this.errorResponse = "Error: Internal servor error";
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
