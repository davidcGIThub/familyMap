package task;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

import com.example.david.familymap.MainActivity;

import java.net.HttpURLConnection;
import java.net.URL;

import data.DataManager;
import request.FamilyRequest;
import request.PersonRequest;
import request.RegisterRequest;
import request.UserEventsRequest;
import result.FamilyResult;
import result.PersonResult;
import result.RegisterResult;
import result.UserEventsResult;
import server.Client;

/**
 * Created by david on 11/16/17.
 */

public class RegisterTask extends AsyncTask<RegisterRequest, Void, String>
{
    private Context context;
    private Button signInButton;
    private Button registerButton;
    private boolean success;

    public RegisterTask(Context c, Button mSignInButton, Button mRegisterButton)
    {
        signInButton = mSignInButton;
        registerButton = mRegisterButton;
        context = c;
    }


    protected String doInBackground(RegisterRequest... registerRequests)
    {
        Client client = new Client();
        RegisterRequest registerRequest = registerRequests[0];
        RegisterResult registerResult = (RegisterResult) client.run("/user/register",registerRequest);
        String response = null;

        DataManager dman = DataManager.getInstance();
        if(registerResult == null)
        {
            response = "Register Failed: Host Connection Error";
            success = false;
        }
        else if(registerResult.getErrorResponse() == null)
        {
            dman.authToken = registerResult.getAuthToken();
            dman.userPersonID = registerResult.getPersonID();

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
            response = "Register Failed: " + registerResult.getErrorResponse();
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