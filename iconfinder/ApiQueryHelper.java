package iconfinder;


/**
* iconfinder/ApiQueryHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from iconfinder.idl
* Wednesday, February 1, 2012 2:13:19 AM CET
*/

abstract public class ApiQueryHelper
{
  private static String  _id = "IDL:iconfinder/ApiQuery:1.0";

  public static void insert (org.omg.CORBA.Any a, iconfinder.ApiQuery that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static iconfinder.ApiQuery extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [4];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "query",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[1] = new org.omg.CORBA.StructMember (
            "licence",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[2] = new org.omg.CORBA.StructMember (
            "page",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[3] = new org.omg.CORBA.StructMember (
            "perPage",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (iconfinder.ApiQueryHelper.id (), "ApiQuery", _members0);
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

  public static iconfinder.ApiQuery read (org.omg.CORBA.portable.InputStream istream)
  {
    iconfinder.ApiQuery value = new iconfinder.ApiQuery ();
    value.query = istream.read_string ();
    value.licence = istream.read_long ();
    value.page = istream.read_long ();
    value.perPage = istream.read_long ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, iconfinder.ApiQuery value)
  {
    ostream.write_string (value.query);
    ostream.write_long (value.licence);
    ostream.write_long (value.page);
    ostream.write_long (value.perPage);
  }

}