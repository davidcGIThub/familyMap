package model;

/**
 * Created by dc1992 on 10/12/17.
 */

public class Person {
    /**
     * Unique person ID for the person
     */
    private String personID;
    /**
     * descendant, or username associated with this person
     */
    private String descendant;
    /**
     * first name of the person
     */
    private String firstName;
    /**
     * last name of the person
     */
    private String lastName;
    /**
     * gender of the person
     */
    private String gender;
    /**
     * father of the person, could be null
     */
    private String father;
    /**
     * mother of the person,  could be null
     */
    private String mother;
    /**
     * spouse of the person, could be null
     */
    private String spouse;

    /**
     * creates a person object
     *
     * @param ID   set person ID
     * @param desc set descendant or username
     * @param fnam set first name of the person
     * @param lnam set the last name of the person
     * @param gen  set the gender of the person
     */
    public Person(String ID, String desc, String fnam, String lnam, String gen) {
        personID = ID;
        descendant = desc;
        firstName = fnam;
        lastName = lnam;
        gender = gen;
    }

    /**
     * sets the person ID for the person
     *
     * @param personID ID being added
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * sets the descendant or username asocciated with the person
     *
     * @param descendant username being added
     */
    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    /**
     * sets the first name of the person
     *
     * @param firstName first name being added
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * sets the last name of the person
     *
     * @param lastName last name being added
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * sets the gender of the person
     *
     * @param gender gender being added
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * sets the father of the person
     *
     * @param father father's name being added
     */
    public void setFather(String father) {
        this.father = father;
    }

    /**
     * sets the mother of the person
     *
     * @param mother mother's name being added
     */
    public void setMother(String mother) {
        this.mother = mother;
    }

    /**
     * sets the spouse of the person
     *
     * @param spouse spouse's name being added
     */
    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    /**
     * gets the person ID of the person
     *
     * @return person ID
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * gets the descendant or username associated with that person
     *
     * @return username or descendant
     */
    public String getDescendant() {
        return descendant;
    }

    /**
     * gets the first name of the person
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * gets the last name of the person
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * gets the gender of the person
     *
     * @return gender "m" or "f"
     */
    public String getGender() {
        return gender;
    }

    /**
     * gets the father of the person, may return null
     *
     * @return father
     */
    public String getFather() {
        return father;
    }

    /**
     * gets the mother of the person, may return null
     *
     * @return mother
     */
    public String getMother() {
        return mother;
    }

    /**
     * gets the spouse of the person, may return null
     *
     * @return spouse
     */
    public String getSpouse() {
        return spouse;
    }
}






