package request;

/**
 * Created by dc1992 on 10/23/17.
 */

public class FillRequest
{
    /** the number of generations that will be created for the user*/
    private int generations;
    /** the authorization token of the user and session*/
    private String authToken;

    /**
     *
     * @param generations
     * @param authToken
     */
    public FillRequest(int generations, String authToken)
    {
        this.generations = generations;
        this.authToken = authToken;
    }

    /**
     * gets the amount of generations that will be created
     *
     * @return generations
     */
    public int getGenerations()
    {
        return generations;
    }

    /**
     * sets the amount of generations that will be created
     *
     * @param generations the number of generations that will be created
     */
    public void setGenerations(int generations)
    {
        this.generations = generations;
    }

    /**
     * gets the authorization token for the user and session
     *
     * @return authToken
     */
    public String getAuthToken()
    {
        return authToken;
    }

    /**
     * sets the authorization token for the user and session
     *
     * @param authToken the authorization token for the user and session
     */
    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }
}
