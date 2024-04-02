package endpoints;

import implementation.Services;

import javax.xml.ws.Endpoint;

public class ServicesEndPoint {
    private static String url="http://localhost:1011/webService"; //url to publish webservice

    public static void main(String[] args) {
        Services services=new Services();
        System.out.println("Webservice running at "+url);
        Endpoint.publish(url,services);
    }
}
