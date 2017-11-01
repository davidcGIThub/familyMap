package dao;

/**
 * Created by david on 11/1/17.
 */

@SuppressWarnings("serial")
public class DaoException extends Exception
{
    private String function;

    public DaoException(String function)
    {
        this.function = function;
    }

    public String getFunction()
    {
        return function;
    }

}
