package com.nanoxcorp.emasomorepaires;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import java.net.MalformedURLException;

public class downloadFacade implements downloadInterface,Runnable{
    Context context;
    Notification notification;
    NotificationCompat.Builder nBuilder;
    NotificationManager nManager;

    boolean suc;


    public downloadFacade(String url,Context context){
        this.context = context;
        suc = false;
        nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        try {
            new DownloadFile(url,this,context).execute();
            Thread progressBar = new Thread(this);
            progressBar.start();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
    public void setSuccess(boolean success){suc = success;}
    public boolean getSuccess(){return suc;}

    @Override
    public void success(int succ) {
        if(succ > 0){
           setSuccess(true);
        }else{
            setSuccess(false);
        }
    }

    @Override
    public void progress(int progress) {
       nBuilder.setProgress(100,progress,false);
    }

    @Override
    public void run() {
        int icon = 0;//TODO:get icon from res
        String name = "";
        nBuilder = new NotificationCompat.Builder(context);
        nBuilder.setSmallIcon(icon);
        nBuilder.setContentTitle("");
        nBuilder.setContentText("");
        nBuilder.setOngoing(true);
        nBuilder.setProgress(100,0,false);
        notification = nBuilder.build();
        nManager.notify(100,notification);

    }
}
