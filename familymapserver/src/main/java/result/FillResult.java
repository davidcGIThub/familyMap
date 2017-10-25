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
     * @param successResponse the success response for this result
     * @param errorResponse the error response for this result
     */
    public FillResult(String successResponse, String errorResponse) {
        this.successResponse = successResponse;
        this.errorResponse = errorResponse;
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
