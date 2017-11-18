package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URI;

import request.FillRequest;
import result.FillResult;
import service.FillService;

/**
 * Created by david on 11/2/17.
 */

public class FillHandler implements HttpHandler
{
    // Handles HTTP requests containing the "/user/[username]/{generations}" URL path.
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

        // Populates the server's database with generated data for the specified user name. The
        // required "username" parameter must be a user already registered with the server. If there
        // is any data in the database already associated with the given user name, it is deleted.
        // The optional "generations" parameter lets the caller specify the number of generations
        // of ancestors to be generated, and must be a non-negative integer (the default is 4, which
        // results in 31 new persons each with associated events

        Gson gson = new Gson();

        try
        {
            URI uri = exchange.getRequestURI();
            String[] strings = uri.toString().split("/");
            String username = strings[2];
            int generations = 4;
            if(strings.length > 3)
            {
                generations = Integer.parseInt(strings[3]);
            }
            FillRequest fillRequest = new FillRequest(generations,username);
            FillService fillService = new FillService();
            FillResult fillResult = fillService.serve(fillRequest);

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

            Writer writer = new OutputStreamWriter(exchange.getResponseBody());
            String jsonStr = gson.toJson(fillResult);
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
