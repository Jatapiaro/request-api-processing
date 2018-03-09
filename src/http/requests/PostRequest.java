/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package http.requests;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 *
 * @author jacobotapia
 */
public class PostRequest {
 
    private HttpPost request;
    private String url;
    private URI uri;
    private URIBuilder builder;
    private HashMap<String, String> body;
    private String responseResult;
    
   
    public PostRequest( String url ) throws URISyntaxException {
        this.builder = new URIBuilder(url);
        this.uri = this.builder.build();
        this.request = new HttpPost(uri);
        this.body = new HashMap<String, String>();
        this.responseResult = null;
    }
    
    public void addHeader(String key, String value) {
        this.request.setHeader(key, value);
    }
    
    public void addBodyParam( String key, String value ) {
        this.body.put(key, value);
    }
    
    public void reset() {
        this.request.reset();
        this.body = new HashMap<String, String>();
        this.responseResult = null;
    }
    
    private StringEntity buildStringEntity() throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder("{ ");
        Iterator it = this.body.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            sb.append("\""+pair.getKey()+"\": ");
            sb.append("\""+pair.getValue()+"\", ");
        }
        sb.append("}");
        String s = sb.reverse().toString();
        s.replaceFirst(",", "");
        sb = new StringBuilder(sb);
        sb.reverse();
        return new StringEntity(sb.toString());
    }
    
    public void sendSimpleRequest() {
        
        try {
            HttpClient httpClient = new DefaultHttpClient();
            this.request.setEntity(this.buildStringEntity());
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                this.responseResult = EntityUtils.toString(entity);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    
    }
    
    public void sendImageRequest(String imagePath) {
        try {
            HttpClient httpClient = new DefaultHttpClient();

            File file = new File(imagePath);
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);            
            ByteArrayEntity reqEntity = new ByteArrayEntity(bytes, ContentType.APPLICATION_OCTET_STREAM);
            request.setEntity(reqEntity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                this.responseResult = EntityUtils.toString(entity);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }   
    }
    
    
    /**
    * @return the responseResult
    */
    public String getResponseResult() {
        return this.responseResult;
    }   
    
}
