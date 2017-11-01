package dao;
import model.AuthToken;
import model.Event;
import model.Person;
import model.User;
import java.sql.Timestamp;

/**
 * Created by david on 10/28/17.
 */

public class daoTestDriver
{
    public static void main( String args[] )
    {
        try
        {
            DaoManager man = new DaoManager();
            System.out.println("Opened database successfully");
            man.createFamilyMapTables();
            System.out.println("Table created successfully");

            /** ****** Test the UserDao class ********/
            // Test addUser
            User u0 = new User("username0", "password0", "email0", "personID0");
            man.uDao.addUser(u0);
            System.out.println("User added successfully");
            //Test import User
            User u1 = new User("username1", "password1", "email1", "personID1");
            User u2 = new User("username2", "password2", "email2", "personID2");
            User u3 = new User("username3", "password3", "email3", "personID3");
            User u[] = new User[]{u1, u2, u3};
            man.uDao.importUsers(u);
            System.out.println("Users imported successfully");
            //Test getUser
            User ug1 = man.uDao.getUser("username2");
            System.out.println("The user retrieved is " + ug1.getUsername() + " with personID " + ug1.getPersonID());
            System.out.println("User retrieved successfully");
            // Test checkNameAndPassword
            man.uDao.checkNameAndPassword("username0", "password0");
            System.out.println("Username and Password Authentication Successfull");
            // Test deleting one user
            man.uDao.deleteUser("username2");
            System.out.println("User deletion successfully");
            // Test deleting all the users
            man.uDao.deleteAllUsers();
            System.out.println("All Users deleted successfully");
            //*******************************************/

            /** Test the PersonDao class **************/
            //Test the addPerson
            Person p0 = new Person("personID0", "descendant2", "firstName0", "lastName0", "m", "father0", "mother0", "spouse0");
            man.pDao.addPerson(p0);
            System.out.println("Person added successfully");
            //Test import Persons
            Person p1 = new Person("personID1", "descendant1", "firstName1", "lastName1", "m", "father1", "mother1", "spouse1");
            Person p2 = new Person("personID2", "descendant2", "firstName2", "lastName2", "f", "father2", "mother2", "spouse2");
            Person p3 = new Person("personID3", "descendant3", "firstName3", "lastName3", "f", "father0", "mother3", "spouse3");
            Person p[] = new Person[]{p1, p2, p3};
            man.pDao.importPersons(p);
            System.out.println("Persons imported successfully");
            //Test get Person
            Person pg1 = man.pDao.getPerson("personID1");
            System.out.println("Person retrieved is " + pg1.getFirstName() + " with ID " + pg1.getPersonID());
            System.out.println("Person retrieved successfully");
            //Test get UserPersons
            Person pp[] = null;
            pp = man.pDao.getUserPersons("descendant2");
            for (int i = 0; i < pp.length; i++) {
                System.out.println("Person " + i + " retrieved is " + pp[i].getFirstName() + " with ID " + pp[i].getPersonID());
            }
            System.out.println("All user persons retrieved successfully");
            //Test deleteUserPersons
            man.pDao.deleteUserPersons("descendant2");
            System.out.println("All user persons deleted successfully");
            //Test deletePerson
            man.pDao.deletePerson("personID1");
            System.out.println("Person deleted successfully");
            //Test deleteAllPersons
            man.pDao.deleteAllPersons();
            System.out.println("All Persons deleted successfully");
            //*******************************************/

            /** Test the EventDao class **************/
            //test addEvent
            Event e0 = new Event("eventID0", "descendant0", "personID0", 0, 0, "country0", "city0", "eventType0", 0);
            man.eDao.addEvent(e0);
            System.out.println("Event added successfully");
            //test import Events
            Event e1 = new Event("eventID1", "descendant1", "personID1", 1, 1, "country1", "city1", "eventType1", 1);
            Event e2 = new Event("eventID2", "descendant1", "personID2", 2, 2, "country2", "city2", "eventType2", 2);
            Event e3 = new Event("eventID3", "descendant1", "personID3", 3, 3, "country3", "city3", "eventType3", 3);
            Event e4 = new Event("eventID4", "descendant4", "personID4", 4, 4, "country4", "city4", "eventType4", 4);
            Event e5 = new Event("eventID5", "descendant5", "personID4", 5, 5, "country5", "city5", "eventType5", 5);
            Event e6 = new Event("eventID6", "descendant6", "personID6", 6, 6, "country6", "city6", "eventType5", 6);
            Event e[] = new Event[]{e1, e2, e3, e4, e5, e6};
            man.eDao.importEvents(e);
            System.out.println("Events imported successfully");
            //test getUserEvents
            Event ee[] = man.eDao.getUserEvents("descendant1");
            for (int i = 0; i < ee.length; i++) {
                System.out.println("Event " + i + " retrieved is " + ee[i].getEventType() + " with ID " + ee[i].getEventID());
            }
            System.out.println("All user events retrieved successfully");
            //test getPersonEvents
            ee = man.eDao.getPersonEvents("personID4");
            for (int i = 0; i < ee.length; i++) {
                System.out.println("Event " + i + " retrieved is " + ee[i].getEventType() + " with ID " + ee[i].getEventID());
            }
            System.out.println("All person events retrieved successfully");
            //test getEvent
            Event eg1 = man.eDao.getEvent("eventID0");
            System.out.println("Event retrieved is " + eg1.getEventType() + " with ID " + eg1.getEventID());
            System.out.println("Event retrieved successfully");
            //test deleteUserEvents
            man.eDao.deleteUserEvents("descendant1");
            System.out.println("All user events deleted successfully");
            //test deletePersonEvents
            man.eDao.deletePersonEvents("personID4");
            System.out.println("Person events deleted successfully");
            //test deleteEvent
            man.eDao.deleteEvent("eventID0");
            System.out.println("Event deleted successfully");
            //test deleteAllEvents
            man.eDao.deleteAllEvents();
            System.out.println("All Events deleted successfully");
            //*******************************************/

            /** Test the AuthTokenDao class ************** */
            //Tests the addToken
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
            System.out.println("Token added successfully");
            //Tests the checkAuthorization
            System.out.println("Token " + a0.getToken() + " 's authorization is " + man.aDao.checkAuthorization(a0));
            System.out.println("Token " + a1.getToken() + " 's authorization is " + man.aDao.checkAuthorization(a1));
            System.out.println("Token " + a2.getToken() + " 's authorization is " + man.aDao.checkAuthorization(a2));
            System.out.println("Token " + a3.getToken() + " 's authorization is " + man.aDao.checkAuthorization(a3));
            System.out.println("Checking Authorization is Successfull");
            // Tests the deleteToken
            man.aDao.deleteToken("token0");
            System.out.println("Token deletion successfull");
            //Tests the refresh Tokens
            AuthToken a4 = new AuthToken("token4", "username4", timeCurrent);
            AuthToken a5 = new AuthToken("token5", "username5", timeExpired);
            man.aDao.addToken(a4);
            man.aDao.addToken(a5);
            man.aDao.refreshTokens();
            System.out.println("Expired token deletion is Successfull");
            // Tests the deleteAllTokens
            man.aDao.deleteAllTokens();
            System.out.println("All Tokens deleted successfully");
            //*******************************************/

            //Test the delete all function
            man.deleteAll();
            System.out.println("Deleted All successfully");
            man.closeSqlSession();
            System.out.println("Closed database successfully");
        }
        catch(DaoException e)
        {
            System.err.println("Error in " + e.getFunction());
        }
    }
}