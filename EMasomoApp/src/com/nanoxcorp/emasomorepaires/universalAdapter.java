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

public class universalAdapter extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> data;
    public universalAdapter(Context context, ArrayList<String> data) {
        super(context, R.layout.el_a, data);
        this.context = context;
        this.data = data;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup root){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.el_a,root,false);
        assert view != null;
        String[] arr = data.get(position).split(":");
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        TextView txt = (TextView) view.findViewById(R.id.textView);
        Bitmap bit = new imageFacad(arr[2]).getThumb();
        if(bit != null) {
            img.setImageBitmap(bit);
        }else{
            img.setImageResource(R.drawable.forum);
        }
        txt.setText(arr[3]);
        return view;
    }
}
