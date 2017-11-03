package service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import request.ClearRequest;
import result.ClearResult;

import static org.junit.Assert.*;

/**
 * Created by david on 11/3/17.
 */
public class ClearServiceTest {
    private ClearRequest clearRequest;
    private ClearService clearService;
    @Before
    public void setUp() throws Exception
    {
        clearRequest = new ClearRequest();
        clearService = new ClearService();
    }

    @Test
    public void serve() throws Exception
    {
        assertEquals("No Errors", clearService.serve(clearRequest).getErrorResponse());
    }

}