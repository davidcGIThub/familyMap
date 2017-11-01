package request;

/**
 * Created by dc1992 on 10/23/17.
 */

public class FillRequest
{
    /** the number of generations that will be created for the user*/
    private int generations;
    /** the authorization token of the user and session*/
    private String username;

    /**
     *
     * @param generations
     * @param username
     */
    public FillRequest(int generations, String username)
    {
        this.generations = generations;
        this.username = username;
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
    public String getUsername()
    {
        return username;
    }

    /**
     * sets the authorization token for the user and session
     *
     * @param username the authorization token for the user and session
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
}
