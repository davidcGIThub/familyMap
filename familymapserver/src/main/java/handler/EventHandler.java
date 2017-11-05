package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URI;

import request.EventRequest;
import request.UserEventsRequest;
import result.EventResult;
import result.UserEventsResult;
import service.ReturnEventService;
import service.ReturnUserEventsService;

/**
 * Created by david on 11/2/17.
 */

public class EventHandler implements HttpHandler
{
    // Handles HTTP requests containing the "/event" or /event/[eventID] URL path.
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

        // /event/[eventID] - Returns the single Event object with the specified ID
        // /person - Returns ALL events for ALL family members of the current user. The current user
        // is determined from the provided authToken

        Gson gson = new Gson();

        try
        {
            Headers eventHeaders = exchange.getRequestHeaders();
            String token = eventHeaders.getFirst("Authorization");

            URI uri = exchange.getRequestURI();
            String[] strings = uri.toString().split("/");
            if(strings.length > 2)
            {
                String eventID = strings[2];
                EventRequest eventRequest = new EventRequest(eventID,token);
                ReturnEventService eventService = new ReturnEventService();
                EventResult eventResult = eventService.serve(eventRequest);

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                Writer writer = new OutputStreamWriter(exchange.getResponseBody());
                String jsonStr = gson.toJson(eventResult);
                writer.write(jsonStr);
                writer.close();
            }
            else
            {
                UserEventsRequest userEventsRequest = new UserEventsRequest(token);
                ReturnUserEventsService userEventsService = new ReturnUserEventsService();
                UserEventsResult userEventsResult = userEventsService.serve(userEventsRequest);

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                Writer writer = new OutputStreamWriter(exchange.getResponseBody());
                String jsonStr = gson.toJson(userEventsResult);
                writer.write(jsonStr);
                writer.close();
            }
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
