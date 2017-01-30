package com.example.kheireddine.popularmoviesstage1.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * This class contain utils static functions to use in all project
 * Created by kheireddine on 30/01/17.
 */


public class Utils {
    public final static String TAG = "pm_debug";

    /**
     * Show a short toast getMessage
     * @param msg : Message to display
     */
    public static void showShortToastMessage(Context context, String msg){
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Show a long toast getMessage
     * @param msg : Message to display
     */
    public static void showLongToastMessage(Context context, String msg){
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.show();
    }


}
