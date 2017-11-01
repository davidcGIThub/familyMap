package dao;
import java.sql.*;
import model.User;

/**
 * Created by dc1992 on 10/12/17.
 */

public class UserDao
{
    private Connection c;

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
    public void addUser(User user) throws DaoException
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
            throw new DaoException("addUser():" + e.getClass().getName() + ": " + e.getMessage() );
        }
    }


    /**
     * imports many users into the database
     *
     * @param users array of users being imported
     */
    public void importUsers(User[] users) throws DaoException
    {
        try
        {
            for (int i = 0; i < users.length; i++) {
                this.addUser(users[i]);
            }
        }
        catch(DaoException e)
        {
            throw new DaoException("importUser() - " + e.getFunction());

        }
    }

    /**
     * checks to see if the username and password are valid
     *
     * @param username username being checked
     * @param password password being checked
     * @return  true if the username and password are valid
     */
    public boolean checkNameAndPassword(String username, String password) throws DaoException
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
            throw new DaoException("checkNameAndPassword():" + e.getClass().getName() + ": " + e.getMessage() );
        }
        return valid;
    }

    /**
     * gets the user object
     *
     * @param username username of user
     * @return returns the user object
     */
    public User getUser(String username) throws DaoException
    {
        User user = null;
        PreparedStatement prepared = null;
        try
        {
            prepared = c.prepareStatement("SELECT * FROM Users WHERE Username = ?;");
            prepared.setString(1, username);
            ResultSet rs = prepared.executeQuery();
            String username_ = rs.getString("Username");
            String password_ = rs.getString("Password");
            String email_ = rs.getString("Email");
            String personID_ = rs.getString("PersonID");
            user = new User(username_,password_,email_,personID_);
            rs.close();
            prepared.close();
        }
        catch ( Exception e )
        {
            throw new DaoException("getUser():" + e.getClass().getName() + ": " + e.getMessage() );
        }
        return user;
    }

    /**
     * deletes the specified user
     *
     * @param username username of the user that will be deleted
     */
    public void deleteUser(String username) throws DaoException
    {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE from Users where Username='" + username + "';";
            stmt.executeUpdate(sql);
            //c.commit(); autocommit mode
        } catch ( Exception e ) {
            throw new DaoException("deleteUser(): " + e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /**
     * deletes all of the users in the database
     */
    public void deleteAllUsers() throws DaoException
    {

        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE from Users;";
            stmt.executeUpdate(sql);
            //c.commit(); autocommit mode
        } catch ( Exception e ) {
            throw new DaoException("deleteAllUsers():" + e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
