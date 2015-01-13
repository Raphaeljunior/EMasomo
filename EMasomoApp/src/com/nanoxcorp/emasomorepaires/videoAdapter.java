package com.nanoxcorp.emasomorepaires;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//TODO:YouTube API
public class videoAdapter extends ArrayAdapter<String>{
    Context context;
    String[] data;
    public videoAdapter(Context context,String[] data) {
        super(context, R.layout.bv_a);//TODO:Res
        this.data = new String[2];
        this.data = data;
        this.context = context;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup root){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.bv_a,root,false);
        ImageView thumbnail = (ImageView)view.findViewById(R.id.thumbnail);
        TextView description = (TextView)view.findViewById(R.id.description);

        return view;
    }
}
