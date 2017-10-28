package dao;

import model.User;

/**
 * Created by dc1992 on 10/12/17.
 */

public class UserDao
{
    /**
     * creates a user Dao constructor
     */
    public UserDao()
    {

    }

    /**
     * creates a user in the database
     *
     * @param user user model being created
     */
    public void createUser(User user) {

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
