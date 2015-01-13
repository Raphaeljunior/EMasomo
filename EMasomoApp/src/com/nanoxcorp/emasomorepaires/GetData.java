package com.nanoxcorp.emasomorepaires;

import java.util.*;

public class GetData implements dataListener {
    ArrayList<String[]> results;

    public GetData(String url){
       results = new ArrayList<String[]>();
        new NetworkConnection(this).execute(url);
    }

    @Override
    public void listen(ArrayList<String> data) {
        if(data != null){
            unpackRepack(data);
        }
    }
    public ArrayList<ArrayList> unpackRepack(ArrayList<String> data){
        for(int i = 0; i < data.size();i++){
            String[] arrData = Arrays.toString(results.get(i)).split(":");
            results.add(arrData);
        }

        return null;
    }

    public ArrayList<String[]> retData(){
        if(results != null) {
            return results;
        }
        return null;
    }
}

