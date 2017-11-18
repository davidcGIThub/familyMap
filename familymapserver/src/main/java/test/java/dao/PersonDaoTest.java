package test.java.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DaoException;
import dao.DaoManager;
import model.Person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by david on 11/2/17.
 */
public class PersonDaoTest {

    private DaoManager man;

    @Before
    public void setUp() throws Exception
    {
        System.out.println("PersonDao Junit Testing:");
        man = new DaoManager();
    }

    @After
    public void tearDown() throws Exception
    {
        man.closeSqlSession();
    }

    @Test
    public void addGetPerson() throws Exception
    {
        Person p0 = new Person("personID0", "descendant2", "firstName0", "lastName0", "m", "father0", "mother0", "spouse0");
        man.pDao.addPerson(p0);
        assertEquals(man.pDao.getPerson("personID0").getPersonID(),"personID0");
        man.deleteAll();
    }

    @Test
    public void importPersons() throws Exception
    {
        Person p1 = new Person("personID1", "descendant1", "firstName1", "lastName1", "m", "father1", "mother1", "spouse1");
        Person p2 = new Person("personID2", "descendant2", "firstName2", "lastName2", "f", "father2", "mother2", "spouse2");
        Person p3 = new Person("personID3", "descendant3", "firstName3", "lastName3", "f", "father0", "mother3", "spouse3");
        Person p[] = new Person[]{p1, p2, p3};
        man.pDao.importPersons(p);
       // catch(DaoException e)
       // {
       //     System.out.println(e.getFunction());
       // }
        assertEquals(man.pDao.getPerson("personID1").getPersonID(),"personID1");
        assertEquals(man.pDao.getPerson("personID2").getPersonID(),"personID2");
        assertEquals(man.pDao.getPerson("personID3").getPersonID(),"personID3");
        man.deleteAll();
    }

    @Test
    public void updateFamilyMember() throws Exception
    {
        Person p0 = new Person("personID0", "descendant2", "firstName0", "lastName0", "m", "father0", "mother0", "spouse0");
        Person p1 = new Person("personID1", "descendant1", "firstName1", "lastName1", "m", "father1", "mother1", "spouse1");
        Person p2 = new Person("personID2", "descendant2", "firstName2", "lastName2", "f", "father2", "mother2", "spouse2");
        Person p3 = new Person("personID3", "descendant3", "firstName3", "lastName3", "f", "father0", "mother3", "spouse3");
        Person p[] = new Person[]{p0, p1, p2, p3};
        man.pDao.importPersons(p);
        man.pDao.updateFamilyMember("Father", "personID2", "personID1");
        man.pDao.updateFamilyMember("Mother", "personID3", "personID1");
        man.pDao.updateFamilyMember("Spouse", "personID0", "personID1");
        assertEquals("personID2",man.pDao.getPerson("personID1").getFather());
        assertEquals("personID3",man.pDao.getPerson("personID1").getMother());
        assertEquals("personID0",man.pDao.getPerson("personID1").getSpouse());

        man.deleteAll();
    }

    @Test
    public void getUserPersons() throws Exception
    {
        Person p0 = new Person("personID0", "descendant2", "firstName0", "lastName0", "m", "father0", "mother0", "spouse0");
        Person p1 = new Person("personID1", "descendant1", "firstName1", "lastName1", "m", "father1", "mother1", "spouse1");
        Person p2 = new Person("personID2", "descendant2", "firstName2", "lastName2", "f", "father2", "mother2", "spouse2");
        Person p3 = new Person("personID3", "descendant3", "firstName3", "lastName3", "f", "father0", "mother3", "spouse3");
        Person p[] = new Person[]{p0, p1, p2, p3};
        man.pDao.importPersons(p);

        Person[] pp = man.pDao.getUserPersons("descendant2");
        assertEquals(pp.length, 2);

        man.deleteAll();
    }

    @Test
    public void deletePerson() throws Exception
    {
        Person p1 = new Person("personID1", "descendant1", "firstName1", "lastName1", "m", "father1", "mother1", "spouse1");
        man.pDao.addPerson(p1);
        man.pDao.deletePerson("personID1");
        try
        {
            man.pDao.getPerson("personID1");
            assertTrue(false);
        }
        catch(DaoException e)
        {
            assertTrue(true);
        }
        man.deleteAll();
    }

    @Test
    public void deleteAllPersons() throws Exception
    {
        Person p4 = new Person("personID4", "descendant4", "firstName4", "lastName4", "m", "father4", "mother4", "spouse4");
        man.pDao.addPerson(p4);
        man.pDao.deleteAllPersons();
        try
        {
            man.pDao.getPerson("personID4");
            assertTrue(false);
        }
        catch(DaoException e)
        {
            assertTrue(true);
        }
        man.deleteAll();
    }

}