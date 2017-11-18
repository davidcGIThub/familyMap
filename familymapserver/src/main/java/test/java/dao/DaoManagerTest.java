package test.java.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DaoException;
import dao.DaoManager;

import static org.junit.Assert.assertTrue;

/**
 * Created by david on 11/1/17.
 */

public class DaoManagerTest
{
    private DaoManager man;

    @Before
    public void setUp() throws Exception
    {
        System.out.println("DaoManager Junit Testing:");
        try
        {
            man = new DaoManager();
            assertTrue(true);
        }
        catch(DaoException e)
        {
            assertTrue(false);
        }
    }

    @After
    public void tearDown() throws Exception
    {
        try
        {
            man.deleteAll();
            assertTrue(true);
        }
        catch(DaoException e)
        {
            assertTrue(false);
        }

        try
        {
            man.closeSqlSession();
            assertTrue(true);
        }
        catch(DaoException e)
        {
            System.err.println(e. getFunction());
            assertTrue(false);
        }
    }

    @Test
    public void testCreateFamilyMapTables() throws Exception
    {
        try
        {
            man.deleteTables();
            man.createFamilyMapTables();
            assertTrue(true);
        }
        catch(DaoException e)
        {
            assertTrue(false);
        }
    }



}