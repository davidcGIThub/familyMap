package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;

import request.LoginRequest;
import result.LoginResult;
import service.LoginService;


/**
 * Created by david on 11/2/17.
 */

public class LoginHandler implements HttpHandler
{
    // Handles HTTP requests containing the "/user/login" URL path.
    // The "exchange" parameter is an HttpExchange object, which is
    // defined by Java.
    // In this context, an "exchange" is an HTTP request/response pair
    // (i.e., the client and server exchange a request and response).
    // The HttpExchange object gives the handler access to all of the
    // details of the HTTP request (Request type [GET or POST],
    // request headers, request body, etc.).
    // The HttpExchange object also gives the handler the ability
    // to construct an HTTP response and send it back to the client
    // (Status code, headers, response body, etc.).
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // Logs in the user and returns an auth token

        Gson gson = new Gson();

        try
        {
            // Gets the request body input stream
            Reader reader = new InputStreamReader(exchange.getRequestBody());
            LoginRequest loginRequest = gson.fromJson(reader, LoginRequest.class);
            LoginService loginService = new LoginService();
            LoginResult loginResult = loginService.serve(loginRequest);

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

            Writer writer = new OutputStreamWriter(exchange.getResponseBody());
            String jsonStr = gson.toJson(loginResult);
            writer.write(jsonStr);
            writer.close();
        }
        catch (IOException e) {
            // Some kind of internal error has occurred inside the server (not the
            // client's fault), so we return an "internal server error" status code
            // to the client.
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
            // Since the server is unable to complete the request, the client will
            // not receive the list of games, so we close the response body output stream,
            // indicating that the response is complete.
            exchange.getResponseBody().close();

            // Display/log the stack trace
            e.printStackTrace();
        }
    }
}
