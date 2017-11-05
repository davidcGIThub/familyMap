package model;

/**
 * Created by dc1992 on 10/12/17.
 */

public class AuthToken
{
    /** unique authorization token generated for a user*/
    private String token;
    /** the userName that uses this token*/
    private String userName;
    /** the time that the authorization token was created*/
    private String timestamp;

    /**
     * creates a AuthToken object
     *
     * @param token     token being set
     * @param userName  userName being set
     */
    public AuthToken(String token, String userName, String timestamp)
    {
        this.token = token;
        this.userName = userName;
        this.timestamp = timestamp;
    }

    /**
     * sets the unique token for this authorization token object
     *
     * @param token token being set
     */
    public void setToken(String token)
    {
        this.token = token;
    }

    /**
     * sets the userName
     *
     * @param username userName being set
     */
    public void setUsername(String username)
    {
        this.userName = username;
    }

    /**
     * sets the timestamp
     *
     * @param timestamp timestamp being set
     */
    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    /**
     * gets the token
     *
     * @return token
     */
    public String getToken()
    {
        return token;
    }

    /**
     * gets the userName using this token
     *
     * @return userName
     */
    public String getUsername()
    {
        return userName;
    }

    /**
     * gets the timestamp for this token
     *
     * @return timestamp
     */
    public String getTimeStamp()
    {
        return timestamp;
    }
}
