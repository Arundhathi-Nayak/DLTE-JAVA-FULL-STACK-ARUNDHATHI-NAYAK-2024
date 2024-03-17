package readservice;

import javax.xml.ws.Endpoint;

public class EndPoint {
    private static String url="http://localhost:1010/mybank";
    public static void main(String[] args) {
        Transactions transactions=new Transactions();
        System.out.println("Webservice hosted @ "+url);
        Endpoint.publish(url,transactions);
    }
}
