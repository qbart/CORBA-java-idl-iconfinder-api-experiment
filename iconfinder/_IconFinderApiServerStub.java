package iconfinder;


/**
* iconfinder/_IconFinderApiServerStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from iconfinder.idl
* Wednesday, February 1, 2012 2:13:19 AM CET
*/

public class _IconFinderApiServerStub extends org.omg.CORBA.portable.ObjectImpl implements iconfinder.IconFinderApiServer
{

  public iconfinder.IconFinderImageData[] execute (iconfinder.ApiQuery query) throws iconfinder.ServerError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("execute", true);
                iconfinder.ApiQueryHelper.write ($out, query);
                $in = _invoke ($out);
                iconfinder.IconFinderImageData $result[] = iconfinder.IconFinderImageData_seqHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:iconfinder/ServerError:1.0"))
                    throw iconfinder.ServerErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return execute (query        );
            } finally {
                _releaseReply ($in);
            }
  } // execute

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:iconfinder/IconFinderApiServer:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _IconFinderApiServerStub
