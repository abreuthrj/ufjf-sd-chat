package br.ufjf.chat.exception;

/**
 *
 * @author ltoscano
 */
public class ChatException extends Exception
{
    public ChatException(String msg)
    {
        super(msg);
    }
    
    public ChatException(Throwable ex)
    {
        super(ex);
    }
    
    public ChatException(String msg, Throwable ex)
    {
        super(msg, ex);
    }
}
