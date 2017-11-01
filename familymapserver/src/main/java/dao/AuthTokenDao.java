package dao;
import java.sql.*;
import model.AuthToken;
import java.sql.Timestamp;

/**
 * Created by dc1992 on 10/12/17.
 */

public class AuthTokenDao
{
    private final int hour = 3600000;
    private Connection c;
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
    public void addToken(AuthToken token)
    {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO Tokens (Token, Username, TimeStamp) " +
                    "VALUES ('" + token.getToken() + "', '" + token.getUsername() + "', '" + token.getTimeStamp() + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            //c.commit(); it is in autocommit mode
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Token added successfully");
    }

    /**
     * checks the authorization of the token with the username,
     * if the timestamp is expired it deletes the authToken in the database
     *
     * @param tokenModel token object
     * @return true it is authorized
     */
    public boolean checkAuthorization(AuthToken tokenModel)
    {

        boolean authorized = false;
        Statement stmt = null;
        try
        {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Tokens;" );

            while ( rs.next() )
            {
                String token_ = rs.getString("Token");
                String username_ = rs.getString("Username");
                String timeStamp_ = rs.getString("TimeStamp");

                if(token_.equals(tokenModel.getToken()) && username_.equals(tokenModel.getUsername()))
                {
                    if(isExpired(timeStamp_))
                    {
                        authorized = false;
                        deleteToken(token_);
                    }
                    else
                    {
                        authorized = true;
                    }
                    break;
                }
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Checking Authorization is Successfull");
        return authorized;
    }


    /**
     * deletes the specified token
     *
     *  @param token the authorization token being deleted
     */
    public void deleteToken(String token)
    {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE from Tokens where Token='" + token + "';";
            stmt.executeUpdate(sql);
            //c.commit(); autocommit mode
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Token deletion successfull");
    }

    /**
     * deletes all of the authorization tokens in the database
     */
    public void deleteAllTokens()
    {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE from Tokens;";
            stmt.executeUpdate(sql);
            //c.commit(); autocommit mode
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("All Tokens deleted successfully");
    }

    /**
     *  Deletes all the tokens that are expired
     */
    public void refreshTokens()
    {
        boolean authorized = false;
        Statement stmt = null;
        try
        {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Tokens;" );
            while ( rs.next() )
            {
                String token_ = rs.getString("Token");
                String timeStamp_ = rs.getString("TimeStamp");

                if(isExpired(timeStamp_))
                {
                    this.deleteToken(token_);
                }
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Expired token deletion is Successfull");
    }

    /**
     * checks to see if the timestamp given is expired, longer than an hour
     *
     * @param time this is the timestamp given
     *
     * @return expired
     */
    private boolean isExpired(String time)
    {
        boolean expired = false;

        Timestamp tokenTimeStamp = Timestamp.valueOf(time);
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        if( (currentTimeStamp.getTime() - tokenTimeStamp.getTime()) > hour )
        {
            expired = true;
        }
        return expired;
    }
}
