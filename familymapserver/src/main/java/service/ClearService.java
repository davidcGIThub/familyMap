package service;

import dao.DaoException;
import dao.DaoManager;
import request.ClearRequest;
import result.ClearResult;

/**
 * Created by dc1992 on 10/12/17.
 */

public class ClearService
{
    /**
     * requests the server to delete all of the data in the database
     * returns an error if can't
     *
     * @param request ClearRequest object containing nothing
     * @return ClearResult object stating if the service was successful
     */
    public ClearResult serve(ClearRequest request)
    {
        ClearResult result = null;
        try
        {
            DaoManager man = new DaoManager();
            man.deleteAll();
            man.closeSqlSession();
            result = new ClearResult("No Errors");
        }
        catch(DaoException e)
        {
            //System.err.println(e.getFunction());
            result = new ClearResult("Internal Server Error: " + e.getFunction());
        }
        return result;
    }
}

