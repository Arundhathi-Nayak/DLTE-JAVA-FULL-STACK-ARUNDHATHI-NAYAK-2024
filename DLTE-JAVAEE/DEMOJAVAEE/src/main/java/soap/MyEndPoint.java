package soap;

import javax.xml.ws.Endpoint;

public class MyEndPoint {
    private static String url="http://localhost:1010/source";
    public static void main(String[] args) {
        MySource mySource=new MySource();
        System.out.println("Soap web service running "+url);
        Endpoint.publish(url,mySource);
    }
}
