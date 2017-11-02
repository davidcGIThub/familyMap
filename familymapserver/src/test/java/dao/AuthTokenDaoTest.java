package dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import model.AuthToken;

import static org.junit.Assert.*;

/**
 * Created by david on 11/1/17.
 */
public class AuthTokenDaoTest {

    private DaoManager man;
    AuthToken a0;
    AuthToken a1;
    AuthToken a2;
    AuthToken a3;
    AuthToken a4;

    @Before
    public void setUp() throws Exception
    {
        System.out.println("AuthTokenDao Junit Testing:");
        man = new DaoManager();
        String timeExpired = "2017-10-30 17:43:17.019";
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String timeCurrent = currentTimeStamp.toString();
        a0 = new AuthToken("token0", "username0", timeCurrent);
        a1 = new AuthToken("token1", "username1", timeExpired);
        a2 = new AuthToken("token2", "username2", timeCurrent);
        a3 = new AuthToken("token3", "username3", timeCurrent);
        man.aDao.addToken(a0);
        man.aDao.addToken(a1);
    }

    @After
    public void tearDown() throws Exception
    {
        man.closeSqlSession();
    }

    @Test
    public void addGetToken() throws Exception
    {
        man.aDao.addToken(a3);
        assertEquals(man.aDao.getUsername("token3"),"username3");
    }

    @Test
    public void checkAuthorization() throws Exception
    {
        assertTrue(man.aDao.checkAuthorization(a0));
        assertTrue(!man.aDao.checkAuthorization(a1));
        assertTrue(!man.aDao.checkAuthorization(a2));
        assertTrue(man.aDao.checkAuthorization(a3));
    }

    @Test
    public void getUsername() throws Exception
    {
        assertEquals("username1",man.aDao.getUsername(a1.getToken()));
    }

    @Test
    public void deleteToken() throws Exception
    {
        man.aDao.deleteToken("token0");
        try
        {
            man.aDao.getUsername("token0");
            assertTrue(false);
        }
        catch(DaoException e)
        {
            assertTrue(true);
        }
    }

    @Test
    public void deleteAllTokens() throws Exception
    {
        a4 = new AuthToken("token4", "username4", "2017.352.25");
        man.aDao.deleteAllTokens();
        try
        {
            man.aDao.getUsername("token4");
            assertTrue(false);
        }
        catch(DaoException e)
        {
            assertTrue(true);
        }
    }

    @Test
    public void refreshTokens() throws Exception
    {
        String timeExpired = "2017-10-30 17:43:17.019";
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String timeCurrent = currentTimeStamp.toString();
        AuthToken a4 = new AuthToken("token4", "username4", timeCurrent);
        AuthToken a5 = new AuthToken("token5", "username5", timeExpired);
        man.aDao.addToken(a4);
        man.aDao.addToken(a5);
        man.aDao.refreshTokens();
        System.out.println("Expired token deletion is Successfull");
    }

}