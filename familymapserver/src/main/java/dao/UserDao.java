package dao;
import java.sql.*;
import model.User;

/**
 * Created by dc1992 on 10/12/17.
 */

public class UserDao
{
    Connection c;

    /**
     * creates a user Dao constructor
     */
    public UserDao(Connection c)
    {
        this.c = c;
    }

    /**
     * creates a user in the database
     *
     * @param user user model being created
     */
    public void addUser(User user)
    {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO Users (Username, Password, Email, PersonID) " +
                    "VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getEmail()  + "', '" + user.getPersonID() + "');";
            stmt.executeUpdate(sql);

            stmt.close();
            //c.commit(); it is in autocommit mode
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("User added successfully");
    }


    /**
     * imports many users into the database
     *
     * @param users array of users being imported
     */
    public void importUsers(User[] users)
    {
        for(int i = 0; i < users.length; i++)
        {
            this.addUser(users[i]);
        }
    }

    /**
     * checks to see if the username and password are valid
     *
     * @param username username being checked
     * @param password password being checked
     * @return  true if the username and password are valid
     */
    public boolean checkNameAndPassword(String username, String password)
    {
        boolean valid = false;
        Statement stmt = null;
        try
        {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Users;" );

            while ( rs.next() )
            {
                String username_ = rs.getString("Username");
                String password_ = rs.getString("Password");

                if(username_.equals(username) && password_.equals(password))
                {
                    valid = true;
                    break;
                }
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Username and Password Authentication Successfull");
        return valid;
    }

    /**
     * gets the user object
     *
     * @param username username of user
     * @return returns the user object
     */
    public User getUser(String username)
    {
        User user = null;
        return user;
    }

    /**
     * deletes the specified user
     *
     * @param username username of the user that will be deleted
     */
    public void deleteUser(String username)
    {

    }

    /**
     * deletes all of the users in the database
     */
    public void deleteAllUsers()
    {

    }
}
