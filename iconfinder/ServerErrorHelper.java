package iconfinder;


/**
* iconfinder/ServerErrorHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from iconfinder.idl
* Wednesday, February 1, 2012 2:13:19 AM CET
*/

abstract public class ServerErrorHelper
{
  private static String  _id = "IDL:iconfinder/ServerError:1.0";

  public static void insert (org.omg.CORBA.Any a, iconfinder.ServerError that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static iconfinder.ServerError extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [0];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          __typeCode = org.omg.CORBA.ORB.init ().create_exception_tc (iconfinder.ServerErrorHelper.id (), "ServerError", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static iconfinder.ServerError read (org.omg.CORBA.portable.InputStream istream)
  {
    iconfinder.ServerError value = new iconfinder.ServerError ();
    // read and discard the repository ID
    istream.read_string ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, iconfinder.ServerError value)
  {
    // write the repository ID
    ostream.write_string (id ());
  }

}