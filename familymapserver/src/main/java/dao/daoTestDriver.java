package dao;

/**
 * Created by david on 10/28/17.
 */

public class daoTestDriver {

    public static void main( String args[] )
    {
        DaoManager man = new DaoManager();
        man.OpenSqlSession();
        man.createFamilyMapTables();
        man.deleteAll();
        man.closeSqlSession();

    }
}