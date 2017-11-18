package server;

/**
 * Created by david on 11/17/17.
 */

import com.google.gson.Gson;

import java.io.*;
import java.net.*;

import data.DataManager;
import request.Request;
import result.ClearResult;
import result.EventResult;
import result.FamilyResult;
import result.FillResult;
import result.LoadResult;
import result.LoginResult;
import result.PersonResult;
import result.RegisterResult;
import result.Result;
import request.PersonRequest;
import result.UserEventsResult;

/*
	The Client class shows how to call a web API operation from
	a Java program.  This is typical of how your Android client
	app will call the web API operations of your server.
*/
public class Client
{
    // The client's "main" method.
    // The "args" parameter should contain two command-line arguments:
    // 1. The IP address or domain name of the machine running the server
    // 2. The port number on which the server is accepting client connections
    public Result run(String apiOperation, Request request)
    {
        DataManager dman = DataManager.getInstance();
        String serverHost = dman.serverHost;
        String serverPort = dman.serverPort;

        Result result = null;
        if (apiOperation.substring(0, 6).equals("/person") || apiOperation.substring(0, 6).equals("/event"))
        {
            getData(serverHost, serverPort, apiOperation, request);
        }
        else
        {
            postData(serverHost, serverPort, apiOperation, request);
        }
        return result;
    }

    // The getGameList method calls the server's "/games/list" operation to
    // retrieve a list of games running in the server in JSON format
    private Result getData(String serverHost, String serverPort, String apiOperation, Request request) {

        String authToken = request.getAuthToken();
        Result result = null;

        // This method shows how to send a GET request to a server
        try
        {
            // Create a URL indicating where the server is running, and which
            // web API operation we want to call
            URL url = new URL("http://" + serverHost + ":" + serverPort + apiOperation);

            // Start constructing our HTTP request
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            // Specify that we are sending an HTTP GET request
            http.setRequestMethod("GET");
            // Indicate that this request will not contain an HTTP request body
            http.setDoOutput(false);
            // Add an auth token to the request in the HTTP "Authorization" header
            http.addRequestProperty("Authorization", authToken);
            // Specify that we would like to receive the server's response in JSON
            // format by putting an HTTP "Accept" header on the request (this is not
            // necessary because our server only returns JSON responses, but it
            // provides one more example of how to add a header to an HTTP request).
            http.addRequestProperty("Accept", "application/json");
            // Connect to the server and send the HTTP request
            http.connect();
            // By the time we get here, the HTTP response has been received from the server.
            // Check to make sure that the HTTP response from the server contains a 200
            // status code, which means "success".  Treat anything else as a failure.
            if (http.getResponseCode() == HttpURLConnection.HTTP_OK)
            {

                Gson gson = new Gson();
                // Get the input stream containing the HTTP response body
                InputStream respBody = http.getInputStream();
                // Extract JSON data from the HTTP response body
                Reader reader = new InputStreamReader(respBody);
                if(apiOperation.substring(0,5).equals("/event"))
                {
                    if(apiOperation.length() > 6)
                    {
                        result = gson.fromJson(reader, EventResult.class);
                    }
                    else
                    {
                        result = gson.fromJson(reader, UserEventsResult.class);
                    }
                }
                else
                {
                    if(apiOperation.length() > 7)
                    {
                        result = gson.fromJson(reader, PersonResult.class);
                    }
                    else
                    {
                        result = gson.fromJson(reader, FamilyResult.class);
                    }
                }
            }
            else
            {
                // The HTTP response status code indicates an error
                // occurred, so print out the message from the HTTP response
                System.out.println("ERROR: " + http.getResponseMessage());
            }
        }
        catch (IOException e) {
            // An exception was thrown, so display the exception's stack trace
            e.printStackTrace();
        }
        return result;
    }

    // The claimRoute method calls the server's "/routes/claim" operation to
    // claim the route between Atlanta and Miami
    private Result postData(String serverHost, String serverPort, String apiOperation, Request request)
    {
        Gson gson = new Gson();
        Reader reader = null;
        Result result = null;
        // This method shows how to send a POST request to a server
        try
        {
            // Create a URL indicating where the server is running, and which
            // web API operation we want to call
            URL url = new URL("http://" + serverHost + ":" + serverPort + apiOperation);

            // Start constructing our HTTP request
            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            // Specify that we are sending an HTTP POST request
            http.setRequestMethod("POST");
            // Indicate that this request will contain an HTTP request body
            http.setDoOutput(true);	// There is a request body

            // Connect to the server and send the HTTP request
            http.connect();
            String reqData = gson.toJson(request);
            // Get the output stream containing the HTTP request body
            OutputStream reqBody = http.getOutputStream();
            // Write the JSON data to the request body
            writeString(reqData, reqBody);
            // Close the request body output stream, indicating that the
            // request is complete
            reqBody.close();

            // By the time we get here, the HTTP response has been received from the server.
            // Check to make sure that the HTTP response from the server contains a 200
            // status code, which means "success".  Treat anything else as a failure.
            if (http.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                // Get the input stream containing the HTTP response body
                InputStream respBody = http.getInputStream();
                // Extract JSON data from the HTTP response body
                reader = new InputStreamReader(respBody);

                switch (apiOperation)
                {
                    case "/user/register":
                        result = gson.fromJson(reader, RegisterResult.class);
                        break;
                    case "/user/login":
                        result = gson.fromJson(reader, LoginResult.class);
                        break;
                    case "/clear":
                        result = gson.fromJson(reader, ClearResult.class);
                        break;
                    case "/load":
                        result = gson.fromJson(reader, LoadResult.class);
                        break;
                    default:
                        result = gson.fromJson(reader, FillResult.class);
                }

            }
            else {
                // The HTTP response status code indicates an error
                // occurred, so print out the message from the HTTP response
                System.out.println("ERROR: " + http.getResponseMessage());
            }
        }
        catch (IOException e)
        {
            // An exception was thrown, so display the exception's stack trace
            e.printStackTrace();
        }
        return result;
    }

    /*
        The readString method shows how to read a String from an InputStream.
    */
    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    /*
        The writeString method shows how to write a String to an OutputStream.
    */
    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

}