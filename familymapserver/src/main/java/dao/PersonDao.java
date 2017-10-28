package dao;

import model.Person;

/**
 * Created by dc1992 on 10/12/17.
 */

public class PersonDao
{
    /**
     * creates a PersonDao object
     */
    public PersonDao()
    {

    }

    /**
     * creates a person in the database
     *
     * @param person person object
     */
    public void createPerson(Person person) {

    }

    /**
     * imports an array of person objects into the database
     *
     * @param persons array of person objects
     */
    public void importPersons(Person[] persons)
    {

    }

    /**
     *gets specified person from the database
     *
     * @param personID ID of the person wanted
     * @return person object
     */
    public Person getPerson(String personID) {
        Person person = null;
        return person;
    }

    /**
     * gets all of the persons associated with the user
     *
     * @param username descendant of all the persons
     * @return an array of persons
     */
    public Person[] getUserPersons(String username)
    {
        Person[] persons = null;
        return persons;
    }

    /**
     * delets all of the persons associated with the user
     *
     * @param username descendant of all the persons
     */
    public void deleteUserPersons(String username) {

    }

    /**
     * deletes the specified person
     *
     * @param personID of the person
     */
    public void deletePerson(String personID)
    {

    }

    /**
     * deletes all of the persons in the database
     */
    public void deleteAllPersons()
    {

    }
}
