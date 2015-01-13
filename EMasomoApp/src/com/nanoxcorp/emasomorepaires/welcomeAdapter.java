package com.nanoxcorp.emasomorepaires;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class welcomeAdapter extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> content;

    public welcomeAdapter(Context context, ArrayList<String> data) {
        super(context, R.layout.home,data);
        this.context = context;
        this.content = data;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup root){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.home,root,false);
        assert view != null;
        ImageView image = (ImageView) view.findViewById(R.id.imageView);
        TextView text = (TextView) view.findViewById(R.id.textView);
        int imageRef = Integer.parseInt(content.get(position).split(":")[0]);
        String textRef = content.get(position).split(":")[1];
        image.setImageResource(imageRef);
        text.setText(textRef);
        return view;

    }

}
