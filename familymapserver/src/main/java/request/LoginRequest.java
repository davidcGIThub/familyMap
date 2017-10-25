package request;

/**
 * Created by dc1992 on 10/13/17.
 */

public class LoginRequest
{
    /** the username of the user*/
    private String username;
    /** the password of the user*/
    private String password;

    /**
     * creates a LoginRequest object
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public LoginRequest(String username, String password)
    {
        this.username = username;
        this.password = password;
    }


    /**
     * gets the username of the user
     *
     * @return username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * gets the password
     *
     * @return the password of the user
     */
    public String getPassword()
    {
        return password;
    }

    /**
     *sets the username
     *
     * @param username the username of the user
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * sets the password
     *
     * @param password the password of the user
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
}
