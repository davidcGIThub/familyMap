package service;
import request.*;
import model.*;
/**
 * Created by david on 11/1/17.
 */

public class serviceTestDriver
{
    public static void main( String args[] )
    {

        //Create Requests
        User user1 = new User("username1", "password1", "email1", "personID1");
        User user2 = new User("username2", "password2", "email2", "personID2");
        User user3 = new User("username3", "password3", "email3", "personID3");
        Person person1 = new Person("personID1", "username1", "firstName1", "lastName1", "gender1", "father1", "mother1", "spouse1");
        Person person2 = new Person("personID2", "username2", "firstName2", "lastName2", "gender2", "father2", "mother2", "spouse2");
        Person person3 = new Person("personID3", "username3", "firstName3", "lastName3", "gender3", "father3", "mother3", "spouse3");
        Event event1 = new Event("eventID1","username1", "personID1",1.0, 1.0,"country1","city1","eventType1",1);
        Event event2 = new Event("eventID2","username2", "personID2",2.0, 2.0,"country2","city2","eventType2",2);
        Event event3 = new Event("eventID3","username3", "personID3",3.0, 3.0,"country3","city3","eventType3",3);
        User[] users = new User[]{user1,user2,user3};
        Person[] persons = new Person[]{person1,person2,person3};
        Event[] events = new Event[]{event1,event2,event3};

        LoadRequest loadReq0 = new LoadRequest(users, persons, events);
        RegisterRequest registerReq0 = new RegisterRequest("username0", "password0", "email0", "firstName0", "lastName0", "gender0");
        LoginRequest loginReq0 = new LoginRequest("username0", "password0");
        FillRequest fillReq0 = new FillRequest(4, "authToken0");
        EventRequest eventReq0 = new EventRequest("eventID0", "authToken0");
        UserEventsRequest userEventsReq0 = new UserEventsRequest("authToken0");
        PersonRequest personReq0 = new PersonRequest("authToken0", "personID0");
        FamilyRequest familyReq0 = new FamilyRequest("authToken0");
        ClearRequest clearReq0 = new ClearRequest();

        //Test the Services

        LoadService.serve(loadReq0);

    }

}

