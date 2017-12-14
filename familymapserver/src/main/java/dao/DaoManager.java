package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


/**
 * Created by dc1992 on 10/12/17.
 */

public class DaoManager
{
    /** connection that will be stored by this Dao Manager*/
    private Connection c;
    /** authorization token data access object */
    public AuthTokenDao aDao;
    /** event data access object */
    public EventDao eDao;
    /** person data access object */
    public PersonDao pDao;
    /** user data access object */
    public UserDao uDao;

    /**
     * creates a DoaManager object
     */
    public DaoManager() throws DaoException
    {
        try
        {
            this.OpenSqlSession();
            uDao = new UserDao(c);
            pDao = new PersonDao(c);
            eDao = new EventDao(c);
            aDao = new AuthTokenDao(c);
        }
        catch(DaoException e)
        {
            throw new DaoException(e.getFunction());
        }

    }

    public void createFamilyMapTables() throws DaoException
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
                    " Year int not null );" +
                    //" contraint ck_EventType check (EventType in ('Birth','Baptism','Marriage','Death'))); " +

                    " CREATE TABLE Users" +
                    " (Username text not null primary key, " +
                    " Password text not null, " +
                    " Email text not null, " +
                    " PersonID text not null); " +

                    " CREATE TABLE Tokens " +
                    " (Token text not null primary key, " +
                    " Username text not null, " +
                    " TimeStamp String not null); ";

            stmt.executeUpdate(sql);
            stmt.close();

        } catch ( Exception e ) {
            //System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            //System.exit(0);
            throw new DaoException("createFamilyMapTables(): " + e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * opens a session in SQL
     */
    private void OpenSqlSession() throws DaoException
    {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:database/FamilyMap.db");
            c.setAutoCommit(true);
        } catch ( Exception e ) {
            throw new DaoException("closeSqlSession(): " + e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /**
     *  closes a session in SQL
     */
    public void closeSqlSession() throws DaoException
    {
        try
        {
            this.c.close();
        }
        catch ( Exception e )
        {
            throw new DaoException("closeSqlSession(): " + e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /**
     * deletes everything in the database
     */
    public void deleteAll() throws DaoException
    {
        try
        {
            aDao.deleteAllTokens();
            pDao.deleteAllPersons();
            uDao.deleteAllUsers();
            eDao.deleteAllEvents();
        }
        catch(DaoException e)
        {
            throw new DaoException("deleteALL() - " + e.getFunction());
        }
    }

    /**
     *
     * @throws DaoException
     */
     public void deleteTables() throws DaoException
     {
         Statement stmt = null;
         try
         {
             stmt = c.createStatement();
             String sql = "drop table if exists Persons;" +
                     "drop table if exists Events;" +
                     "drop table if exists Users;" +
                     "drop table if exists Tokens;";
             stmt.executeUpdate(sql);
             stmt.close();
             //c.commit(); it is in autocommit mode
         }
         catch ( Exception e )
         {
             throw new DaoException("deleteTables(): " + e.getClass().getName() + ": " + e.getMessage() );
         }
     }
}
