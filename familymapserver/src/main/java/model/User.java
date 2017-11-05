package model;

/**
 * Created by dc1992 on 10/12/17.
 */
/**this class stores accessible information about the user8*/
public class User
{
    /**unique userName for the user */
    private String userName;
    /** password for the user */
    private String password;
    /** email address for the user*/
    private String email;
    /** unique person ID that belongs to this user*/
    private String personID;

    /**
     * creates a user object
     *
     * @param userName unique userName being set
     * @param password  password being set
     * @param email     email being set
     * @param personID    personID being set
     */
    public User(String userName, String password, String email, String personID)
    {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.personID = personID;
    }


    /**
     * sets the userName for the user
     *
     * @param u     userName being added
     */
    public void setUsername(String u)
    {
        this.userName = u;
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
     * gets the userName of the user
     *
     * @return      userName
     */
    public String getUsername()
    {
        return userName;
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
