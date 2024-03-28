package org.clientservice;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class httpTest {
    public static void printResponse(HttpResponse response) throws IOException {
        System.out.println(response.getProtocolVersion());
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.getStatusLine().getReasonPhrase());
        System.out.println(response.getStatusLine().toString());
        //  System.out.println(EntityUtils.toString(response.getEntity()));
        Gson gson=new Gson();
        String json= EntityUtils.toString(response.getEntity());
        System.out.println(json);
        //  JsonObject o=new JsonObject(jjson);

    }

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost:7001/BuildingRestfulwebserviceforDAOlayer/findByUsername?username=shreyas12");
        CloseableHttpResponse response = httpclient.execute(httpget);

        try {
            //  System.out.println(httpget);
            printResponse(response);
            //     System.out.println(response.getEntity());
        } finally {
            response.close();
        }
    }
}
