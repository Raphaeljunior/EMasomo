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

public class libraryAdapter extends ArrayAdapter<FlashCard> {
    ArrayList<String> data;
    Context context;

    public libraryAdapter(Context context, ArrayList<String> data) {
        super(context, R.layout.el_a,loadOut.flashCards);
        this.data = data;
        this.context = context;

    }

    @Override
    public View getView(int position,View convertView,ViewGroup root){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.el_a,root,false);
        assert view != null;
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        TextView txt = (TextView) view.findViewById(R.id.textView);
        txt.setText(loadOut.flashCards.get(position).getQuestion());//TODO: change to getName
        img.setImageResource(R.drawable.ic_launcher);
        return view;
    }

    public Bitmap getImage(String url){
       imageFacad img = new imageFacad(url);
        return img.getThumb();
    }
}
