package com.example.mylandscapeapplication;

public class LandscapeHelper {
    public static boolean isLandscape(){
        return MainApplication.getApplication().getResources().getBoolean(R.bool.is_landscape);
    }
}
