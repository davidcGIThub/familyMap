package model;

/**
 * Created by dc1992 on 10/12/17.
 */
/**this class stores accessible information about the user8*/
public class User
{
    /**unique username for the user */
    private String username;
    /** password for the user */
    private String password;
    /** email address for the user*/
    private String email;
    /** unique person ID that belongs to this user*/
    private String personID;

    /**
     * creates a user object
     *
     * @param username unique username being set
     * @param password  password being set
     * @param email     email being set
     * @param personID    personID being set
     */
    public User(String username, String password, String email, String personID)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.personID = personID;
    }


    /**
     * sets the username for the user
     *
     * @param u     username being added
     */
    public void setUsername(String u)
    {
        this.username = u;
    }

    /**
     * sets the password for the user
     *
     * @param p     passsword being added
     */
    public void setPassword(String p)
    {
        this.password = p;
    }

    /**
     * sets the email for the user
     *
     * @param e     email being added
     */
    public void setEmail(String e)
    {
        this.email = e;
    }

    /**
     * sets the person object that belongs to this user
     *
     * @param personID person being added
     */
    public void setPersonID(String personID)
    {
        this.personID = personID;
    }

    /**
     * gets the username of the user
     *
     * @return      username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     *gets the password of the user
     *
      * @return password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     *gets the email of the user
     *
     * @return email
     */
    public String getEmail()
    {
        return  email;
    }

    /**
     * gets the person object that belongs to this user
     *
     * @return person
     */
    public String getPersonID()
    {
        return personID;
    }
}
