package org.mvp.labs.java.server.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class LightweightHttpServer {
	public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/listener", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
        	InputStream request = t.getRequestBody();
        	BufferedReader in = new BufferedReader(new InputStreamReader(request));
        	String ln;
        	System.out.println("****************************** BEGIN, method "+t.getRequestMethod());
        	while((ln = in.readLine())!=null){
        		System.out.println(ln);
        	}
        	System.out.println("****************************** END");
        	
            String response = "It works...";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
