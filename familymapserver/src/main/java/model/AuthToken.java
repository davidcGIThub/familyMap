package model;

/**
 * Created by dc1992 on 10/12/17.
 */

public class AuthToken
{
    /** unique authorization token generated for a user*/
    private String token;
    /** the username that uses this token*/
    private String username;
    /** the time that the authorization token was created*/
    private String timestamp;

    /**
     * creates a AuthToken object
     *
     * @param token     token being set
     * @param username  username being set
     */
    public AuthToken(String token, String username, String timestamp)
    {
        this.token = token;
        this.username = username;
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
     * sets the username
     *
     * @param username username being set
     */
    public void setUsername(String username)
    {
        this.username = username;
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
     * gets the username using this token
     *
     * @return username
     */
    public String getUsername()
    {
        return username;
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
