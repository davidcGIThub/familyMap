package request;

/**
 * Created by dc1992 on 10/13/17.
 */

public class LoginRequest
{
    /** the userName of the user*/
    private String userName;
    /** the password of the user*/
    private String password;

    /**
     * creates a LoginRequest object
     *
     * @param userName the userName of the user
     * @param password the password of the user
     */
    public LoginRequest(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }


    /**
     * gets the userName of the user
     *
     * @return userName
     */
    public String getUsername()
    {
        return userName;
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
     *sets the userName
     *
     * @param username the userName of the user
     */
    public void setUsername(String username)
    {
        this.userName = username;
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
