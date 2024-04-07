package endpoints;

import implementation.Services;

import javax.xml.ws.Endpoint;
import java.sql.SQLException;

public class ServicesEndPoint {
    private static String url="http://localhost:1011/webService"; //url to publish webservice

    public static void main(String[] args) throws SQLException {
        Services services=new Services();
        System.out.println("Webservice running at "+url);
        Endpoint.publish(url,services);
    }
}
