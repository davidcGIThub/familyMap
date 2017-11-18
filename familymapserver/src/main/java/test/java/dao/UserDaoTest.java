package test.java.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DaoException;
import dao.DaoManager;
import model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by david on 11/2/17.
 */
public class UserDaoTest {

    private DaoManager man;

    @Before
    public void setUp() throws Exception
    {
        System.out.println("UserDao Junit Testing:");
        man = new DaoManager();
    }

    @After
    public void tearDown() throws Exception
    {
        man.closeSqlSession();
    }

    @Test
    public void addGetUser() throws Exception
    {
        User u0 = new User("username0", "password0", "email0", "personID0");
        man.uDao.addUser(u0);
        assertEquals(man.uDao.getUser("username0").getUsername(),"username0");
        man.deleteAll();
    }

    @Test
    public void importUsers() throws Exception
    {
        User u1 = new User("username1", "password1", "email1", "personID1");
        User u2 = new User("username2", "password2", "email2", "personID2");
        User u3 = new User("username3", "password3", "email3", "personID3");
        User u[] = new User[]{u1, u2, u3};
        man.uDao.importUsers(u);
        assertEquals(man.uDao.getUser("username1").getUsername(),"username1");
        assertEquals(man.uDao.getUser("username2").getUsername(),"username2");
        assertEquals(man.uDao.getUser("username3").getUsername(),"username3");

        man.deleteAll();
    }

    @Test
    public void checkNameAndPassword() throws Exception
    {
        User u0 = new User("username0", "password0", "email0", "personID0");
        man.uDao.addUser(u0);
        assertTrue(man.uDao.checkNameAndPassword("username0", "password0"));
        man.deleteAll();
    }

    @Test
    public void checkContains() throws Exception
    {
        User u1 = new User("username1", "password1", "email1", "personID1");
        User u2 = new User("username2", "password2", "email2", "personID2");
        User u3 = new User("username3", "password3", "email3", "personID3");
        User u[] = new User[]{u1, u2, u3};
        man.uDao.importUsers(u);
        assertTrue(man.uDao.checkContains("Username", "username1"));
        assertTrue(man.uDao.checkContains("Password", "password1"));
        assertTrue(man.uDao.checkContains("Email", "email2"));
        assertTrue(man.uDao.checkContains("PersonID", "personID3"));
        man.deleteAll();
    }

    @Test
    public void deleteUser() throws Exception
    {
        User u2 = new User("username2", "password2", "email2", "personID2");
        man.uDao.addUser(u2);
        man.uDao.deleteUser("username2");
        assertTrue(!man.uDao.checkContains("Username", "username2"));
        man.deleteAll();
    }

    @Test
    public void deleteAllUsers() throws Exception
    {
        User u4 = new User("username4", "password4", "email4", "personID4");
        man.uDao.addUser(u4);
        man.uDao.deleteAllUsers();
        try
        {
            man.uDao.getUser("username4");
            assertTrue(false);
        }
        catch(DaoException e)
        {
            assertTrue(true);
        }
    }

}