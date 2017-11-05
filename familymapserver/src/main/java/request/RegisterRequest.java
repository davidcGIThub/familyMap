package request;

/**
 * Created by dc1992 on 10/13/17.
 */

public class RegisterRequest
{
    /**the userName of the user*/
    private String userName;
    /** the password of the user*/
    private String password;
    /** the email of the user*/
    private String email;
    /** the first name of the user*/
    private String firstname;
    /** the last name of the user*/
    private String lastname;
    /** the gender of the user*/
    private String gender;

    /**
     *Creates a Register Request object
     *
     * @param userName userName of the user
     * @param password password of the user
     * @param email email of the user
     * @param firstname first name of the user
     * @param lastname last name of the user
     * @param gender gender of the user
     */
    public RegisterRequest(String userName, String password, String email, String firstname, String lastname, String gender)
    {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
    }

    /**
     * gets the userName of the user
     *
     * @return userName
     */
    public String getUsername()
    {
        return userName;
    }

    /**
     * gets the password of the user
     *
     * @return password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * gets the email fo the user
     *
     * @return email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     *  gets the first name of the user
     *
     * @return firstName
     */
    public String getFirstname()
    {
        return firstname;
    }

    /**
     * gets the last name of the user
     *
     * @return lastName
     */
    public String getLastname()
    {
        return lastname;
    }

    /**
     * gets the gender of the user
     *
     * @return gender
     */
    public String getGender()
    {
        return gender;
    }

    /**
     * sets the userName
     *
     * @param username user name of the user being set
     */
    public void setUsername(String username)
    {
        this.userName = username;
    }

    /**
     * sets the password
     *
     * @param password password of the user being set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * sets the email
     *
     * @param email email of the user being set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * sets the first name
     *
     * @param firstname first name of the user being set
     */
    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    /**
     * sets the last name
     *
     * @param lastname last name of the user being set
     */
    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    /**
     *sets the gender
     *
     * @param gender gender of the user being set
     */
    public void setGender(String gender)
    {
        this.gender = gender;
    }
}
