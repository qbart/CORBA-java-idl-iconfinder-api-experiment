package iconfinder;

/**
* iconfinder/ServerErrorHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from iconfinder.idl
* Wednesday, February 1, 2012 2:13:19 AM CET
*/

public final class ServerErrorHolder implements org.omg.CORBA.portable.Streamable
{
  public iconfinder.ServerError value = null;

  public ServerErrorHolder ()
  {
  }

  public ServerErrorHolder (iconfinder.ServerError initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = iconfinder.ServerErrorHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    iconfinder.ServerErrorHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return iconfinder.ServerErrorHelper.type ();
  }

}
