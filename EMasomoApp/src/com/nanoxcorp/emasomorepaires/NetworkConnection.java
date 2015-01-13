package com.nanoxcorp.emasomorepaires;

import android.os.AsyncTask;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

public class NetworkConnection extends AsyncTask<String,Void,String> {

    HttpClient client;
    HttpEntity entity;
    HttpGet request;
    HttpResponse response;
    HashMap<String,String> processedData;
    dataListener listener;
    public NetworkConnection(dataListener listener){
        this.listener = listener;
        client = new DefaultHttpClient();
        request = new HttpGet();
        processedData = new HashMap<String, String>();

    }
    @Override
    public void onPreExecute(){

    }
    @Override
    public String doInBackground(String...args){
        String data;
        try {
            request.setURI(new URI(args[0]));
            response = client.execute(request);
            if(response.getStatusLine().getStatusCode() == 200){
                entity = response.getEntity();
                data = EntityUtils.toString(entity,HTTP.UTF_8);
                return data;

            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void onPostExecute(String data){
        ArrayList<String> packedData;
        packedData = jsonIconAndString(data);
        listener.listen(packedData);
    }

    //JSON parsers for the different return types


    public ArrayList<String> jsonIconAndString(String json){
        //TODO:Parse data
        return null;

    }
}
