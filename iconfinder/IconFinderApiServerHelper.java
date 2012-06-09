package iconfinder;


/**
* iconfinder/IconFinderApiServerHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from iconfinder.idl
* Wednesday, February 1, 2012 2:13:19 AM CET
*/

abstract public class IconFinderApiServerHelper
{
  private static String  _id = "IDL:iconfinder/IconFinderApiServer:1.0";

  public static void insert (org.omg.CORBA.Any a, iconfinder.IconFinderApiServer that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static iconfinder.IconFinderApiServer extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (iconfinder.IconFinderApiServerHelper.id (), "IconFinderApiServer");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static iconfinder.IconFinderApiServer read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_IconFinderApiServerStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, iconfinder.IconFinderApiServer value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static iconfinder.IconFinderApiServer narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof iconfinder.IconFinderApiServer)
      return (iconfinder.IconFinderApiServer)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      iconfinder._IconFinderApiServerStub stub = new iconfinder._IconFinderApiServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static iconfinder.IconFinderApiServer unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof iconfinder.IconFinderApiServer)
      return (iconfinder.IconFinderApiServer)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      iconfinder._IconFinderApiServerStub stub = new iconfinder._IconFinderApiServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}