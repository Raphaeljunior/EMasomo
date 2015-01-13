package com.nanoxcorp.emasomorepaires;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class bookAdapter extends ArrayAdapter<Books> {
    Context context;
    ArrayList<Books> books;
    public bookAdapter(Context context, ArrayList<Books> books) {
        super(context, R.layout.book_a,books);
        this.context = context;
        this.books = books;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.book_a,parent,false);
        assert view != null;
        ImageView img = (ImageView) view.findViewById(R.id.imageView);//TODO: set image to res
        TextView txt = (TextView) view.findViewById(R.id.textView);
        txt.setText(books.get(position).getTitle());
        img.setImageResource(R.drawable.flashcards);
        return view;
    }
}
