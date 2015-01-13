package com.nanoxcorp.emasomorepaires;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class forumAdapter extends ArrayAdapter<String> {
    ArrayList<String> data;
    Context context;
    public forumAdapter(Context context, ArrayList<String> data) {
        super(context, R.layout.forum_a);
        this.data = data;
        this.context = context;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup root){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.forum_a,root,false);
        String[] processedData = data.get(position).split(":");
        TextView txt = (TextView) view.findViewById(R.id.textView);
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        txt.setText(processedData[0]);
        img.setImageBitmap(getBit(processedData[1]));//TODO

        return view;
    }
    public Bitmap getBit(String url){
        imageFacad imagefacad = new imageFacad(url);
        return imagefacad.getThumb();
    }
}
