package com.nanoxcorp.emasomorepaires;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Books {
    public int id;
    public String title;
    public String description;

    public Books(int id,String title,String description){
        setId(id);
        setTitle(title);
        setDescription(description);
    }
    public Books(int id, HashMap<String, String> data){
        setId(id);
        Set set = data.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            setTitle(entry.getKey().toString());
            setDescription(entry.getValue().toString());
        }
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
