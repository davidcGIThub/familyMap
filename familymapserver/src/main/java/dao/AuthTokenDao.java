package dao;
import java.sql.*;
import model.AuthToken;

/**
 * Created by dc1992 on 10/12/17.
 */

public class AuthTokenDao
{
    Connection c;
    /**
     * creates an authorization token object
     */
    public AuthTokenDao(Connection c)
    {
        this.c = c;
    }

    /**
     * creates an authorization token in the database
     *
     * @param token the token object
     */
    public void createToken(AuthToken token)
    {

    }

    /**
     * checks the authorization of the token with the username,
     * if the timestamp is expired it deletes the authToken in the database
     *
     * @param token token object
     * @return true it is authorized
     */
    public boolean checkAuthorization(AuthToken token)
    {
        boolean auth = true;
        return auth;
    }


    /**
     * deletes the specified token
     *
     *  @param token the authorization token being deleted
     */
    public void deleteToken(AuthToken token)
    {

    }

    /**
     * deletes all of the authorization tokens in the database
     */
    public void deleteAllTokens()
    {

    }
}
