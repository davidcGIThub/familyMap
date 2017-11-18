package task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;

import data.DataManager;
import request.FamilyRequest;
import request.LoginRequest;
import request.PersonRequest;
import result.FamilyResult;
import result.LoginResult;
import result.PersonResult;
import server.Client;

/**
 * Created by david on 11/16/17.
 */

public class LoginTask extends AsyncTask<LoginRequest, Void, String>
{
    private Context context;

    public LoginTask(Context c)
    {
        context = c;
    }


    protected String doInBackground(LoginRequest... loginRequests)
    {
        Client client = new Client();
        LoginRequest loginRequest = loginRequests[0];
        LoginResult loginResult = (LoginResult) client.run("/user/login",loginRequest);
        String response = null;

        DataManager dman = DataManager.getInstance();
        if(loginResult.getErrorResponse() == null)
        {
            dman.authToken = loginResult.getAuthToken();
            dman.userPersonID = loginResult.getPersonID();

            PersonRequest personRequest = new PersonRequest(dman.userPersonID,dman.authToken);
            PersonResult personResult = (PersonResult) client.run(("/person/" + dman.userPersonID), personRequest);
            response = personResult.getPerson().getFirstName() + " " + personResult.getPerson().getLastName();

            FamilyRequest familyRequest = new FamilyRequest(dman.authToken);
            FamilyResult familyResult = (FamilyResult) client.run("/person",familyRequest);
            dman.persons = familyResult.getPersons();
        }
        else
        {
            response = "Log In Failed: " + loginResult.getErrorResponse();
        }

        return response;
    }

    protected void onPostExecute(String response)
    {
        Toast.makeText(context,response,Toast.LENGTH_LONG).show();
    }
}