import iconfinder.ApiQuery;
import iconfinder.ApiQueryHolder;
import iconfinder.IconFinderApiServer;
import iconfinder.IconFinderApiServerPOA;
import iconfinder.IconFinderImageData;
import iconfinder.ServerError;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.PortableServer.POAHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


class IconFinderApiServerImpl extends IconFinderApiServerPOA {
	
	public final String api_key = "PUT-YOUR-API-KEY-HERE"; //icon finder API KEY

	// get data and parse
	public IconFinderImageData[] api_query(ApiQuery query) throws ServerError {
		if (query != null) {
			try {

				ArrayList<IconFinderImageData> array = new ArrayList<IconFinderImageData>();
				
				String params = "q=" + URLEncoder.encode(query.query, "UTF-8");
				params += "&c=" + query.perPage;
				params += "&p=" + query.page;
				params += "&min=1";
				params += "&max=512";
				params += "&api_key=" + api_key; 
				
				URI uri = new URI(
						"http",
						null,
						"www.iconfinder.com",
						80,
						"/xml/search/",
						params,
						null
						);
				URL url = uri.toURL();
				System.out.println(url);

				InputStream xml = url.openStream();

				DocumentBuilderFactory docFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(xml);

				NodeList list = doc.getElementsByTagName("icon");

				for (int i = 0; i < list.getLength(); i++) {
					NodeList tags = list.item(i).getChildNodes();

					IconFinderImageData imageData = new IconFinderImageData();
					for (int j = 0; j < tags.getLength(); j++) {

						Node tag = tags.item(j);
						if (tag.getNodeName().equals("image")) {
							imageData.url = tag.getTextContent().trim();
						}
						if (tag.getNodeName().equals("size")) {
							imageData.size = tag.getTextContent().trim();
						}
						if (tag.getNodeName().equals("id")) {
							try {
								imageData.id = Integer.parseInt(tag
										.getTextContent().trim());
							} catch (Exception e) {
								imageData.id = 0;
							}

						}

					}
					array.add(imageData);
				}
				if (array.size() > 0) {
					IconFinderImageData[] result = new IconFinderImageData[array.size()];
					Iterator<IconFinderImageData> iter = array.iterator();
					int index = 0;
					while (iter.hasNext()) {
						result[index] = iter.next();
						index++;
					}
					return result;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServerError();
			}
		}

		return null;
	}

	@Override
	public IconFinderImageData[] execute(ApiQuery query) throws ServerError {
		if (query != null) {
			return api_query(query);
		}
		return null;
	}


}

public class Server {

	public static void main(String args[]) {
		try {
			// init ORB
			ORB orb = ORB.init(args, null);

			// get ref
			org.omg.CORBA.Object obj = orb
					.resolve_initial_references("RootPOA");
			POAHelper.narrow(obj).the_POAManager().activate();

			obj = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = org.omg.CosNaming.NamingContextExtHelper
					.narrow(obj);

			// create servant
			IconFinderApiServerImpl impl = new IconFinderApiServerImpl();
			IconFinderApiServer apiServer = impl._this(orb);

			ncRef.rebind(ncRef.to_name("IconFinderApiServer"), apiServer);

			System.out.println("ready..");
			orb.run();
		} catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

		System.out.println("exiting..");

	}
}
