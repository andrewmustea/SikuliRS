package common.util;

public class GeneralException extends Exception
{
	public GeneralException(String inErrorMessage, Throwable inError)
	{
		super(inErrorMessage, inError);
	}
}
