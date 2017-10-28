package dao;
import java.sql.*;


/**
 * Created by dc1992 on 10/12/17.
 */

public class DaoManager
{

    public void createDatabase()
    {
        Connection c = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    /**
     * creates a DoaManager object
     */
    public DaoManager()
    {

    }

    /**
     * opens a session in SQL
     */
    public void OpenSqlSession()
    {
        
    }

    /**
     *  closes a session in SQL
     */
    public void closeSqlSession()
    {

    }

    /**
     * deletes everything in the database
     */
    public void deleteAll()
    {

    }
}
