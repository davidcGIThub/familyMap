package request;

/**
 * Created by dc1992 on 10/23/17.
 */

public class FamilyRequest extends Request
{

    /**
     * creates a FamilyRequest Object
     *
     * @param authToken
     */
    public FamilyRequest(String authToken)
    {
        setAuthToken(authToken);
    }


}
