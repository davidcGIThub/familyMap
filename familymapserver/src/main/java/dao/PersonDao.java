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
    public void addPerson(Person person)
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
            //c.commit(); it is in autocommit mode
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Person added successfully");
    }

    /**
     * imports an array of person objects into the database
     *
     * @param persons array of person objects
     */
    public void importPersons(Person[] persons)
    {
        for(int i = 0; i < persons.length; i++)
        {
            this.addPerson(persons[i]);
        }

    }

    /**
     *gets specified person from the database
     *
     * @param personID ID of the person wanted
     * @return person object
     */
    public Person getPerson(String personID)
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
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Person retrieved successfully");
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
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("All user persons retrieved successfully");
        return persons;
    }

    /**
     * deletes all of the persons associated with the user
     *
     * @param username descendant of all the persons
     */
    public void deleteUserPersons(String username)
    {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE from Persons where Descendant='" + username + "';";
            stmt.executeUpdate(sql);
            //c.commit(); autocommit mode
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("All user persons deleted successfully");
    }

    /**
     * deletes the specified person
     *
     * @param personID of the person
     */
    public void deletePerson(String personID)
    {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE from Persons where personID='" + personID + "';";
            stmt.executeUpdate(sql);
            //c.commit(); autocommit mode
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Person deleted successfully");

    }

    /**
     * deletes all of the persons in the database
     */
    public void deleteAllPersons()
    {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE from Persons;";
            stmt.executeUpdate(sql);
            //c.commit(); autocommit mode
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("All Persons deleted successfully");

    }
}
