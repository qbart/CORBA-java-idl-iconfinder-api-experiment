import javax.swing.SwingUtilities;

import iconfinder.ApiQuery;
import iconfinder.IconFinderApiServer;
import iconfinder.IconFinderApiServerHelper;
import iconfinder.IconFinderImageData;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class Client {

	private static IconFinderApiServer apiService;

	public static void main(String args[]) {
		try {
            // init
            ORB orb = ORB.init(args, null);

            // get the root
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            apiService = IconFinderApiServerHelper.narrow(ncRef.resolve_str("IconFinderApiServer"));
            
            SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new IconFinder(apiService);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
