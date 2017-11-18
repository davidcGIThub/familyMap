package request;

/**
 * Created by dc1992 on 10/23/17.
 */

public class UserEventsRequest extends Request
{
    /**
     * creates a UserEventsRequest object
     *
     * @param authToken the authorization token for the user
     */
    public UserEventsRequest(String authToken)
    {
        setAuthToken(authToken);
    }
}
