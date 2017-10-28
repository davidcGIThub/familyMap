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
    private String person;

    /**
     * creates a user object
     *
     * @param username unique username being set
     * @param password  password being set
     * @param email     email being set
     * @param person    personID being set
     */
    public User(String username, String password, String email, String person)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.person = person;
    }

    /**
     *
     * constructor that creates a user model object
     * @param u		set username (String)
     * @param p		set password (String)
     * @param e     set email (String)
     */


    /**
     * sets the username for the user
     *
     * @param u     username being added
     */
    public void setUsername(String u)
    {

    }

    /**
     * sets the password for the user
     *
     * @param p     passsword being added
     */
    public void setPassword(String p)
    {

    }

    /**
     * sets the email for the user
     *
     * @param e     email being added
     */
    public void setEmail(String e)
    {

    }

    /**
     * sets the person object that belongs to this user
     *
     * @param person person being added
     */
    public void setPerson(String person)
    {
        this.person = person;
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
    String getPassword()
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
    public String getPerson()
    {
        return person;
    }
}
