package dao;
import model.User;

/**
 * Created by david on 10/28/17.
 */

public class daoTestDriver {

    public static void main( String args[] )
    {
        DaoManager man = new DaoManager();
        man.createFamilyMapTables();


        //Test the UserDao class

         // Test addUser
         User u0 = new User("username0", "password0", "email0" , "personID0");
         man.uDao.addUser(u0);
         //Test import User
         User u1 = new User("username1", "password1", "email1" , "personID1");
         User u2 = new User("username2", "password2", "email2" , "personID2");
         User u3 = new User("username3", "password3", "email3" , "personID3");
         User u[] = new User[]{u1, u2, u3};
         man.uDao.importUsers(u);
         // Test checkNameAndPassword
         man.uDao.checkNameAndPassword("username0","password0");





       // man.deleteAll();
        man.closeSqlSession();

    }
}