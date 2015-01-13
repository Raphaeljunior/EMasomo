package com.nanoxcorp.emasomorepaires;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FlashCard {//holds flash card data
    int id,colorVal;
    String question,answer;
    public FlashCard(int id){
        setId(id);
    }
    public FlashCard(int id, int color, HashMap<String,String> assocMap){
        setId(id);
        setColorVal(color);
        Set set = assocMap.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry entry= (Map.Entry)iterator.next();
            setQuestion(entry.getKey().toString());
            setAnswer(entry.getValue().toString());
        }
    }
    public void setId(int id){
        this.id = id;
    }
    public void setColorVal(int color){
        this.colorVal = color;
    }
    public int getColorVal(){
        return colorVal;
    }
    public void setQuestion(String question){
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }
}
