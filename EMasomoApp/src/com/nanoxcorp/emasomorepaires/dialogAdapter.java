package com.nanoxcorp.emasomorepaires;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class dialogAdapter extends ArrayAdapter<String>{
    Context context;
    optionsStore opS;
    public dialogAdapter(Context context, optionsStore optS) {
        super(context, R.layout.dialog_a);
        this.context = context;
        this.opS = optS;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup root){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.dialog_a,root,false);
        EditText question = (EditText) view.findViewById(R.id.editText);
        EditText answer = (EditText) view.findViewById(R.id.editText2);
        TextView instruction = (TextView) view.findViewById(R.id.textView);
        SeekBar sBar = (SeekBar) view.findViewById(R.id.seekBar);
        opS.question(question.getText().toString());
        opS.answer(answer.getText().toString());
        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                View currentView = view.findViewById(R.id.relL);
                View root = currentView.getRootView();
                root.setBackgroundColor(i);
                opS.colorVal(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        return view;
    }

}
