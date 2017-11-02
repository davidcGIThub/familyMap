package dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.User;

import static org.junit.Assert.*;

/**
 * Created by david on 11/2/17.
 */
public class UserDaoTest {

    private DaoManager man;
    User u0;
    User u1;
    User u2;
    User u3;
    User u4;

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
        u0 = new User("username0", "password0", "email0", "personID0");
        man.uDao.addUser(u0);
        assertEquals(man.uDao.getUser("username0").getUsername(),"username0");
    }

    @Test
    public void importUsers() throws Exception
    {
        u1 = new User("username1", "password1", "email1", "personID1");
        u2 = new User("username2", "password2", "email2", "personID2");
        u3 = new User("username3", "password3", "email3", "personID3");
        User u[] = new User[]{u1, u2, u3};
        man.uDao.importUsers(u);
        assertEquals(man.uDao.getUser("username1").getUsername(),"username1");
        assertEquals(man.uDao.getUser("username2").getUsername(),"username2");
        assertEquals(man.uDao.getUser("username3").getUsername(),"username3");
    }

    @Test
    public void checkNameAndPassword() throws Exception
    {
        assertTrue(man.uDao.checkNameAndPassword("username0", "password0"));
    }

    @Test
    public void checkContains() throws Exception
    {
        assertTrue(man.uDao.checkContains("Username", "username1"));
        assertTrue(man.uDao.checkContains("Password", "password1"));
        assertTrue(man.uDao.checkContains("Email", "email2"));
        assertTrue(man.uDao.checkContains("PersonID", "personID3"));
    }

    @Test
    public void deleteUser() throws Exception
    {
        man.uDao.deleteUser("username2");
        assertTrue(!man.uDao.checkContains("Username", "username2"));
    }

    @Test
    public void deleteAllUsers() throws Exception
    {
        u4 = new User("username4", "password4", "email4", "personID4");
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