package com.nanoxcorp.emasomorepaires;

import android.graphics.Bitmap;

public class imageFacad implements image {
    Bitmap bitmap;
    public  imageFacad(String url){
        new Thumbelina(this).execute(url);
    }
    @Override
    public Bitmap getThumb() {
        return bitmap;
    }

    @Override
    public Bitmap setThumb(Bitmap img) {
        bitmap = img;
        return null;
    }
}
