package result;

/**
 * Created by dc1992 on 10/13/17.
 */

public class PersonResult
{
    /** the username associated with this person*/
    private String descendant;
    /** the id for this person*/
    private String personID;
    /** the first name of the person*/
    private String firstName;
    /** the last name of the person*/
    private String lastName;
    /** the gender of the person*/
    private String gender;
    /** the father of the person, may be null*/
    private String father;
    /** the mother of the person, may be null*/
    private String mother;
    /** the spouse of the person, may be null*/
    private String spouse;
    /** the error response printed to the user*/
    private String errorResponse;

    /**
     * creates a PersonResult object, and creates the error Response message
     *
     * @param descendant the username or descendant associated with the person
     * @param personID the ID of the person
     * @param firstName the first name of the person
     * @param lastName the last name of the person
     * @param gender the gender of the person
     * @param father the father of the person, may be null
     * @param mother the mother of the person, may be null
     * @param spouse the spouse of the person, may be null
     * @param errorResponse the error Response message
     */
    public PersonResult(String descendant, String personID, String firstName, String lastName, String gender, String father, String mother, String spouse, String errorResponse) {
        this.descendant = descendant;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
        this.errorResponse = errorResponse;
    }

    /**
     * sets the descendant or username
     *
     * @param descendant the username or descendant associated with the person
     */
    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    /**
     * sets the person ID
     *
     * @param personID the ID of the person
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * sets the first name
     *
     * @param firstName the first name of the person
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * sets the last name
     *
     * @param lastName the last name of the person
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * sets the gender
     *
     * @param gender the gender of the person
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * sets the father
     *
     * @param father the father of the person, may be null
     */
    public void setFather(String father) {
        this.father = father;
    }

    /**
     * sets the mother
     *
     * @param mother the mother of the person, may be null
     */
    public void setMother(String mother) {
        this.mother = mother;
    }

    /**
     * sets the spouse
     *
     * @param spouse the spouse of the person, may be null
     */
    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }


    /**
     * gets the descendant or username associated with the person
     *
     * @return descendant
     */
    public String getDescendant() {
        return descendant;
    }

    /**
     * gets the person Id of the person
     *
     * @return personID
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * gets the First Name of the Person
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * gets the Last Name of the person
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * gets the gender of the person
     *
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * gets the Father of the person
     *
     * @return father
     */
    public String getFather() {
        return father;
    }

    /**
     * gets the mother of the person
     *
     * @return mother
     */
    public String getMother() {
        return mother;
    }

    /**
     * gets the spouse of the person
     *
     * @return spouse
     */
    public String getSpouse() {
        return spouse;
    }

    /**
     * gets the error response message
     *
     * @return errorResponse
     */
    public String getErrorResponse() {
        return errorResponse;
    }
}
