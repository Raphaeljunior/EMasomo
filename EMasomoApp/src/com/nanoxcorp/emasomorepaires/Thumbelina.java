package com.nanoxcorp.emasomorepaires;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Thumbelina extends AsyncTask<String, Void, Bitmap>{
    image imageInstance;
    public Thumbelina(image imageInstance){
        this.imageInstance = imageInstance;

    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap thumbnail = BitmapFactory.decodeStream(input);
            return thumbnail;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public void onPostExecute(Bitmap img){
       imageInstance.setThumb(img);

    }
}
