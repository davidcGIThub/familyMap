package service;

/**
 * Created by david on 11/2/17.
 */

@SuppressWarnings("serial")
public class ServiceException extends Exception
{
    private String serviceTypeError;

    public ServiceException(String serviceTypeError)
    {
        this.serviceTypeError = serviceTypeError;
    }

    public String getServiceTypeError()
    {
        return serviceTypeError;
    }

}

