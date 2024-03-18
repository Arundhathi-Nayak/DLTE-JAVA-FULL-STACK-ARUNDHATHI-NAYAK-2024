package soapwebservice;

import javax.xml.ws.Endpoint;

public class EndPoint {
    private static String url="http://localhost:3030/operations";
    public static void main(String[] args) {
        CallOperation callOperation=new CallOperation();
        System.out.println("Webservice hosted @ "+url);
        Endpoint.publish(url,callOperation);
    }
}
