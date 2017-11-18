package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/*
	The DefaultHandler is the HTTP handler that processes
	incoming HTTP requests that contain the "/games/list" URL path.

	Notice that DefaultHandler implements the HttpHandler interface,
	which is define by Java.  This interface contains only one method
	named "handle".  When the HttpServer object (declared in the Server class)
	receives a request containing the "web/" URL path, it calls
	DefaultHandler.handle() which actually processes the request.
*/
public class DefaultHandler implements HttpHandler
{
    // Handles HTTP requests containing the "web/" URL path.
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
    public void handle(HttpExchange exchange) throws IOException
    {
        try
        {
            String filePathStr = "web";
            String relativePathStr = exchange.getRequestURI().getPath();
            if(relativePathStr.equals("/"))
            {
                relativePathStr = "/index.html";
            }
            String pathStr = filePathStr + relativePathStr;
            Path filePath = FileSystems.getDefault().getPath(pathStr);
            byte[] fileData = Files.readAllBytes(filePath);
            // if it fails, assume they want "html/404.html"
            //Now send the file back over the HttpExchange
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
            //200 means it worked, 0 means there are no other headers (this sends the headers)
            //sends data in response body
            OutputStream respBody = exchange.getResponseBody(); //gives us an output stream to write
            //response data to put our file data into response body
            respBody.write(fileData); //put our file data into response body
            respBody.close(); // sends the data to the client
            //if it failed send back a different header with  number that means it broke (404,500 etc
            // and call exchange.getResponseBody.close(); to send an empty response
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