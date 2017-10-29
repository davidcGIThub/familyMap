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
    public void createUser(User user)
    {

        Statement stmt = null;
        try {

            stmt = c.createStatement();
            String sql = "INSERT INTO Users (Username,Password,Email,PersonID) " +
                    "VALUES (" + user.getUsername() + "," + user.getPassword() + "," + user.getEmail()  + "," + user.getPersonID() + ");";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                    "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                    "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                    "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }


    /**
     * imports many users into the database
     *
     * @param user array of users being imported
     */
    public void importUsers(User[] user)
    {

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
        boolean valid = true;
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
