package dao;
import java.sql.*;


/**
 * Created by dc1992 on 10/12/17.
 */

public class DaoManager
{
    /** connection that will be stored by this Dao Manager*/
    Connection c;
    /** authorization token data access object */
    public AuthTokenDao authDao;
    /** event data access object */
    public EventDao eDao;
    /** person data access object */
    public PersonDao pDao;
    /** user data access object */
    public UserDao uDao;

    /**
     * creates a DoaManager object
     */
    public DaoManager()
    {
        this.OpenSqlSession();
        uDao = new UserDao(c);

    }

    public void createFamilyMapTables()
    {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE Persons" +
                    "(PersonID text not null primary key," +
                    " Descendant text not null, " +
                    " FirstName text not null, " +
                    " LastName text not null, " +
                    " Gender text not null, " +
                    " Father text, " +
                    " Mother text, " +
                    " Spouse text, " +
                    " contraint ck_Gender check (Gender in ('m','f'))); " +

                    "CREATE TABLE Events" +
                    "(EventID text not null primary key, " +
                    " Descendant text not null, " +
                    " PersonID text not null, " +
                    " Latitude real not null, " +
                    " Longitude real not null, " +
                    " Country text not null, " +
                    " City text not null, " +
                    " EventType text not null, " +
                    " Year int not null); " +

                    " CREATE TABLE Users" +
                    " (Username text not null primary key, " +
                    " Password text not null, " +
                    " Email text not null, " +
                    " PersonID text not null); " +

                    " CREATE TABLE Tokens " +
                    " (Tokens text not null primary key, " +
                    " Username text not null, " +
                    " TimeStamp text not null); ";

            stmt.executeUpdate(sql);
            stmt.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    /**
     * opens a session in SQL
     */
    public void OpenSqlSession()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:database/FamilyMap.db");
            c.setAutoCommit(true);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        
    }

    /**
     *  closes a session in SQL
     */
    public void closeSqlSession()
    {
        try
        {
            this.c.close();
            System.out.println("Closed database successfully");

        }
        catch ( Exception e )
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * deletes everything in the database
     */
    public void deleteAll()
    {
        Statement stmt = null;
        try
        {
            stmt = c.createStatement();
            String sql = " drop table if exists Persons; " +
                        " drop table if exists Events; " +
                        " drop table if exists Users; " +
                        " drop table if exists Tokens; ";

            stmt.executeUpdate(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Deleted all data successfully");
    }
}
