package com.nanoxcorp.emasomorepaires;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class efcAdapter extends ArrayAdapter<String> {
    ArrayList<String> data;
    Context context;
    public efcAdapter(Context context, ArrayList<String> data) {
        super(context, R.layout.eflc,data);
        this.data = data;
        this.context = context;
    }

    @Override
    public View getView(int position,View view,ViewGroup root){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.eflc,root,false);
        assert view != null;
        ImageView thumbnail = (ImageView) view.findViewById(R.id.imageView);
        Button download = (Button) view.findViewById(R.id.button);
        TextView content = (TextView) view.findViewById(R.id.textView);
        String[] processedResults;
        processedResults = data.get(position).split(":");
        thumbnail.setImageBitmap(getThumbnail(processedResults[3]));
        content.setText(processedResults[1].split("/")[processedResults[1].split("/").length - 1]);
        return view;

    }
    public void onClick(View v){//TODO:DLC

    }
    public Bitmap getThumbnail(String url){
        imageFacad imagefacad = new imageFacad(url);
        return  imagefacad.getThumb();
    }
}
