package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedStorage {
    public static final String STORAGE_NAME = "Storage";

    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;
    private static Context context = null;

    public static void init( Context cntxt ){
        context = cntxt;
    }

    private static void init(){
        settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putString("sort", "nan");
        editor.putString("tagged", "2");
        editor.commit();
    }

    public static void addProperty( String name, String value ){
        if( settings == null ){
            init();
        }
        editor = settings.edit();
        editor.putString( name, value );
        editor.commit();
    }

    public static String getProperty( String name ){
        if( settings == null ){
            init();
        }
        return settings.getString( name, null );
    }
}
