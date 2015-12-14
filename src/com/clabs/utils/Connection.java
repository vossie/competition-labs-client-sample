package com.clabs.utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {

    public Connection(String rootUri, String spaceName, String apiKey, int port) {

        this.spaceName=spaceName;
        this.rootUri=rootUri;
        this.apiKey=apiKey;
        this.port=port;
    }

    private String spaceName;
    private String rootUri;
    private String apiKey;
    private int port;

    private final String USER_AGENT = "Mozilla/5.0";

    private String toUrl(String resourcePath) {
        return rootUri + ":" + port + "/api/" + spaceName + "/"+ resourcePath;
    }

    // HTTP GET request
    public ConnectionResultWrapper sendGet(String resourcePath, int skip, int limit) throws Exception {
        return sendGet(resourcePath + "?_skip=" + skip + "&_limit=" + limit, "");
    }

    public ConnectionResultWrapper sendGet(String resourcePath, int skip, int limit, String urlParameters) throws Exception {
        return sendGet(resourcePath + "?_skip=" + skip + "&_limit=" + limit, urlParameters);
    }

    public ConnectionResultWrapper sendGet(String resourcePath) throws Exception {
        return sendGet(resourcePath, "");
    }

    public ConnectionResultWrapper sendGet(String resourcePath, String urlParameters) throws Exception {
        return sendWithoutBody("GET", resourcePath, urlParameters);
    }

    // HTTP DELETE request
    public ConnectionResultWrapper sendDelete(String resourcePath, String urlParameters) throws Exception {
        return sendWithoutBody("DELETE", resourcePath, urlParameters);
    }

    // HTTP request
    public ConnectionResultWrapper sendWithoutBody(String method, String resourcePath, String urlParameters) throws Exception {

        String url = toUrl(resourcePath);

        if(!urlParameters.isEmpty()) {
            url = (url.contains("?")) ? url +"&"+ urlParameters :  url + "?" + urlParameters;
        }

        URL obj = new URL(url);
        HttpURLConnection con;

        if(url.startsWith("https"))
            con = (HttpsURLConnection) obj.openConnection();
        else
            con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod(method);

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("X-API-KEY",  apiKey);
        con.setRequestProperty("Accept-Encoding", "application/json");

        int responseCode = con.getResponseCode();
        System.out.println("\n[Sending] '"+method+"' request to URL : " + url);
        System.out.println("[Response] Code : " + responseCode);

        BufferedReader in;

        if (con.getResponseCode() == 200) {
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            /* error from server */
            in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return new ConnectionResultWrapper()
                .setBody(response.toString())
                .setHttpStatusCode(responseCode)
                .setContentType(con.getContentType());
    }

    // HTTP POST request
    public ConnectionResultWrapper sendPost(String resourcePath, String body) throws Exception {
        return sendWithBody("POST", resourcePath, body);
    }

    // HTTP PUT request
    public ConnectionResultWrapper sendPut(String resourcePath, String body) throws Exception {
        return sendWithBody("PUT", resourcePath, body);
    }

    // HTTP send with body
    public ConnectionResultWrapper sendWithBody(String method, String resourcePath, String body) throws Exception {

        String url = toUrl(resourcePath);
        URL obj = new URL(url);
        HttpURLConnection con;

        if(url.startsWith("https"))
            con = (HttpsURLConnection) obj.openConnection();
        else
            con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod(method);

        //add request header
        con.setRequestProperty("X-API-KEY",  apiKey);
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept-Encoding", "application/json");
        con.setRequestProperty("Content-Length", Integer.toString(body.length()));

        // Send request
        con.setDoOutput(true);
        con.getOutputStream().write(body.getBytes("UTF8"));
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        //wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\n[Sending] '"+method+"' request to URL : " + toUrl(resourcePath));
        System.out.println("[Response] Code : " + responseCode);

        BufferedReader in;

        if (con.getResponseCode() == 200) {
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            /* error from server */
            in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return new ConnectionResultWrapper()
                .setBody(response.toString())
                .setHttpStatusCode(responseCode)
                .setContentType(con.getContentType());
    }
}
