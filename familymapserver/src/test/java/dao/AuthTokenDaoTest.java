package dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import model.AuthToken;
import request.ClearRequest;
import service.ClearService;

import static org.junit.Assert.*;

/**
 * Created by david on 11/1/17.
 */
public class AuthTokenDaoTest {

    private DaoManager man;

    @Before
    public void setUp() throws Exception
    {
        ClearRequest clearRequest = new ClearRequest();
        ClearService clearService = new ClearService();
        clearService.serve(clearRequest);

        man = new DaoManager();
    }

    @After
    public void tearDown() throws Exception
    {
        man.closeSqlSession();
    }

    @Test
    public void addGetToken() throws Exception
    {
        System.out.println("AuthTokenDao Junit Testing: addGetToken()");

        AuthToken a3 = new AuthToken("token3", "username3", "2017-10-30 17:43:17.019");
        man.aDao.addToken(a3);
        assertEquals(man.aDao.getUsername("token3"), "username3");
        man.deleteAll();
    }

    @Test
    public void checkAuthorization() throws Exception
    {
        System.out.println("AuthTokenDao Junit Testing: checkAuthorization()");

        String timeExpired = "2017-10-30 17:43:17.019";
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String timeCurrent = currentTimeStamp.toString();
        AuthToken a0 = new AuthToken("token0", "username0", timeCurrent);
        AuthToken a1 = new AuthToken("token1", "username1", timeExpired);
        AuthToken a2 = new AuthToken("token2", "username2", timeCurrent);
        AuthToken a3 = new AuthToken("token3", "username3", timeCurrent);
        man.aDao.addToken(a0);
        man.aDao.addToken(a1);
        man.aDao.addToken(a3);

        assertTrue(man.aDao.checkAuthorization(a0.getToken()));
        assertTrue(!man.aDao.checkAuthorization(a1.getToken()));
        assertTrue(!man.aDao.checkAuthorization(a2.getToken()));
        assertTrue(man.aDao.checkAuthorization(a3.getToken()));
        man.deleteAll();
    }

    @Test
    public void getUsername() throws Exception
    {
        System.out.println("AuthTokenDao Junit Testing: getUserName()");

        AuthToken a1 = new AuthToken("token1", "username1", "2017-10-30 17:43:17.019");
        man.aDao.addToken(a1);
        assertEquals("username1",man.aDao.getUsername(a1.getToken()));
        man.deleteAll();
    }

    @Test
    public void deleteToken() throws Exception
    {
        System.out.println("AuthTokenDao Junit Testing: deleteToken()");

        AuthToken a0 = new AuthToken("token0", "username0", "2017-10-30 17:43:17.019");
        man.aDao.addToken(a0);
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
        man.deleteAll();
    }

    @Test
    public void deleteAllTokens() throws Exception
    {
        System.out.println("AuthTokenDao Junit Testing: deleteAllTokens()");

        AuthToken a4 = new AuthToken("token4", "username4", "2017.352.25");
        man.aDao.addToken(a4);
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
        man.deleteAll();
    }

    @Test
    public void refreshTokens() throws Exception
    {
        System.out.println("AuthTokenDao Junit Testing: refreshTokens()");

        String timeExpired = "2017-10-30 17:43:17.019";
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String timeCurrent = currentTimeStamp.toString();
        AuthToken a4 = new AuthToken("token4", "username4", timeCurrent);
        AuthToken a5 = new AuthToken("token5", "username5", timeExpired);
        man.aDao.addToken(a4);
        man.aDao.addToken(a5);
        man.aDao.refreshTokens();
        try
        {
            man.aDao.getUsername("token5");
            assertTrue(false);
        }
        catch(DaoException e)
        {
            assertTrue(true);
        }
        man.deleteAll();

    }

}