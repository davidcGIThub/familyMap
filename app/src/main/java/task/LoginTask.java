package task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

import com.example.david.familymap.MainActivity;

import java.net.HttpURLConnection;
import java.net.URL;

import data.DataManager;
import request.EventRequest;
import request.FamilyRequest;
import request.LoginRequest;
import request.PersonRequest;
import request.UserEventsRequest;
import result.FamilyResult;
import result.LoginResult;
import result.PersonResult;
import result.UserEventsResult;
import server.Client;

/**
 * Created by david on 11/16/17.
 */

public class LoginTask extends AsyncTask<LoginRequest, Void, String>
{
    private Context context;
    private Button signInButton;
    private Button registerButton;
    private boolean success;

    public LoginTask(Context c, Button mSignInButton, Button mRegisterButton)
    {
        signInButton = mSignInButton;
        registerButton = mRegisterButton;
        context = c;
    }


    protected String doInBackground(LoginRequest... loginRequests)
    {
        Client client = new Client();
        LoginRequest loginRequest = loginRequests[0];
        LoginResult loginResult = (LoginResult) client.run("/user/login",loginRequest);
        String response = null;

        DataManager dman = DataManager.getInstance();
        if(loginResult == null)
        {
            response = "Log In Failed: Host Connection Error";
            success = false;
        }
        else if(loginResult.getErrorResponse() == null)
        {
            dman.authToken = loginResult.getAuthToken();
            dman.userPersonID = loginResult.getPersonID();

            PersonRequest personRequest = new PersonRequest(dman.userPersonID,dman.authToken);
            PersonResult personResult = (PersonResult) client.run(("/person/" + dman.userPersonID), personRequest);
            response = personResult.getPerson().getFirstName() + " " + personResult.getPerson().getLastName();

            FamilyRequest familyRequest = new FamilyRequest(dman.authToken);
            FamilyResult familyResult = (FamilyResult) client.run("/person",familyRequest);
            dman.persons = familyResult.getPersons();

            UserEventsRequest eventsRequest = new UserEventsRequest(dman.authToken);
            UserEventsResult eventsResult = (UserEventsResult) client.run("/event", eventsRequest);
            dman.events = eventsResult.getEvents();
            success = true;
        }
        else
        {
            response = "Log In Failed: " + loginResult.getErrorResponse();
            success = false;
        }

        return response;
    }

    protected void onPostExecute(String response)
    {
        Toast.makeText(context,response,Toast.LENGTH_LONG).show();
        signInButton.setEnabled(true);
        registerButton.setEnabled(true);
        if(success)
        {
            MainActivity activity = (MainActivity) context;
            activity.switchToMapFragment();
        }
    }
}