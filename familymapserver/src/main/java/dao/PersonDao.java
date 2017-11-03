package dao;
import java.sql.*;
import model.Person;

/**
 * Created by dc1992 on 10/12/17.
 */

public class PersonDao
{
    private Connection c;
    /**
     * creates a PersonDao object
     */
    public PersonDao(Connection c)
    {
        this.c = c;
    }

    /**
     * creates a person in the database
     *
     * @param person person object
     */
    public void addPerson(Person person) throws DaoException
    {
        Statement stmt = null;
        try
        {
            stmt = c.createStatement();
            String sql = "INSERT INTO Persons (PersonID, Descendant, FirstName, LastName, Gender, Father, Mother, Spouse) " +
                    "VALUES ('" + person.getPersonID() + "', '" + person.getDescendant() + "', '" + person.getFirstName()  +
                    "', '" + person.getLastName() + "', '" + person.getGender() + "', '" + person.getFather()  +
                    "', '" + person.getMother() + "', '" + person.getSpouse() + "');";
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch ( Exception e )
        {
            throw new DaoException("addPerson():" + e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /**
     * imports an array of person objects into the database
     *
     * @param persons array of person objects
     */
    public void importPersons(Person[] persons) throws DaoException
    {
        try
        {
            for (int i = 0; i < persons.length; i++) {
                this.addPerson(persons[i]);
            }
        }
        catch(DaoException e)
        {
            throw new DaoException("importPersons() - " + e.getFunction());
        }

    }

    /**
     * updates that persons family member
     *
     * @param member "Father", "Mother", "Spouse"
     * @param memberID the person ID of the new family member
     * @param personID the ID of the person who is kin to the new family member
     */
    public void updateFamilyMember(String member, String memberID, String personID) throws DaoException
    {
        Statement stmt = null;
        try
        {
            stmt = c.createStatement();
            String sql = "UPDATE Persons set " + member + " = '" + memberID + "' where PersonID='" + personID + "';";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch ( Exception e ) {
            throw new DaoException("updateFamilyMember(): " + e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /**
     *gets specified person from the database
     *
     * @param personID ID of the person wanted
     * @return person object
     */
    public Person getPerson(String personID) throws DaoException
    {
        Person person = null;
        PreparedStatement prepared = null;

        try
        {
            prepared = c.prepareStatement("SELECT * FROM Persons WHERE PersonID = ?;");
            prepared.setString(1, personID);
            ResultSet rs = prepared.executeQuery();
            String personID_ = rs.getString("PersonID");
            String descendant_ = rs.getString("Descendant");
            String firstName_ = rs.getString("FirstName");
            String lastName_ = rs.getString("LastName");
            String gender_ = rs.getString("Gender");
            String father_ = rs.getString("Father");
            String mother_ = rs.getString("Mother");
            String spouse_ = rs.getString("Spouse");
            person = new Person(personID_, descendant_, firstName_, lastName_, gender_, father_, mother_, spouse_);
            rs.close();
            prepared.close();
        } catch ( Exception e ) {
            throw new DaoException("getPerson(): " + e.getClass().getName() + ": " + e.getMessage() );
        }
        return person;
    }

    /**
     * gets all of the persons associated with the user
     *
     * @param username descendant of all the persons
     * @return an array of persons
     */
    public Person[] getUserPersons(String username) throws DaoException
    {
        Person[] persons = null;
        PreparedStatement prepared = null;
        try {
            prepared = c.prepareStatement("SELECT * FROM Persons WHERE Descendant = ?;");
            prepared.setString(1, username);
            ResultSet rs = prepared.executeQuery();
            int rowcount = 0;
            while(rs.next())
            {
                rowcount++;
            }
            rs = prepared.executeQuery();
            persons = new Person[rowcount];
            for(int i = 0; i < rowcount; i++)
            {
                rs.next();
                String personID_ = rs.getString("PersonID");
                String descendant_ = rs.getString("Descendant");
                String firstName_ = rs.getString("FirstName");
                String lastName_ = rs.getString("LastName");
                String gender_ = rs.getString("Gender");
                String father_ = rs.getString("Father");
                String mother_ = rs.getString("Mother");
                String spouse_ = rs.getString("Spouse");
                Person person = new Person(personID_, descendant_, firstName_, lastName_, gender_, father_, mother_, spouse_);
                persons[i] = person;
            }

            rs.close();
            prepared.close();
        } catch ( Exception e ) {
            throw new DaoException("getUserPersons(): " + e.getClass().getName() + ": " + e.getMessage() );
        }
        return persons;
    }

    /**
     * deletes all of the persons associated with the user
     *
     * @param username descendant of all the persons
     */
    public void deleteUserPersons(String username) throws DaoException
    {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE from Persons where Descendant='" + username + "';";
            stmt.executeUpdate(sql);
            //c.commit(); autocommit mode
        } catch ( Exception e ) {
            throw new DaoException("deleteUserPersons(): " + e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /**
     * deletes the specified person
     *
     * @param personID of the person
     */
    public void deletePerson(String personID) throws DaoException
    {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE from Persons where personID='" + personID + "';";
            stmt.executeUpdate(sql);
            //c.commit(); autocommit mode
        } catch ( Exception e ) {
            throw new DaoException("deletePerson():" + e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /**
     * deletes all of the persons in the database
     */
    public void deleteAllPersons() throws DaoException
    {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE from Persons;";
            stmt.executeUpdate(sql);
            //c.commit(); autocommit mode
        } catch ( Exception e ) {
            throw new DaoException("deleteAllPersons(): " + e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
