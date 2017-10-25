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
     * @param successResponse
     * @param errorResponse
     */
    public LoadResult(String successResponse, String errorResponse)
    {
        this.successResponse = successResponse;
        this.errorResponse = errorResponse;
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
}
