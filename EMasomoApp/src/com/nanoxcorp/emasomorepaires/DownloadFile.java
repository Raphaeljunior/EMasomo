package com.nanoxcorp.emasomorepaires;

import android.content.Context;
import android.os.AsyncTask;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadFile extends AsyncTask<String , Integer, Integer> {
    HttpURLConnection connection;
    URL url;
    Context context;
    downloadInterface tracker;
    public DownloadFile(String url,downloadInterface tracker,Context context ) throws MalformedURLException {
        this.url = new URL("url");
        this.tracker = tracker;
        this.context = context;


    }
    @Override
    protected Integer doInBackground(String... strings) {
        try {

            String[] names = url.toString().split("/");
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent","EMASOMO");
            connection.connect();
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                int contentLength = connection.getContentLength();
                int bytesRead = -1;
                byte[] buffer = new byte[contentLength];
                FileOutputStream out = context.openFileOutput(names[names.length - 1], Context.MODE_PRIVATE);
                InputStream dl = connection.getInputStream();
                while((bytesRead = dl.read(buffer)) != -1){
                    out.write(buffer);
                    super.publishProgress(bytesRead);
                }
                return 1;//download happened
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void onPostExecute(Integer progressData){
        tracker.success(progressData);
    }

    @Override
    public void onProgressUpdate(Integer... values){
        tracker.progress(values[0]);
    }
}
