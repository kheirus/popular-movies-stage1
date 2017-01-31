package com.example.kheireddine.popularmoviesstage1.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.kheireddine.popularmoviesstage1.api.MovieDBServiceAPI;

/**
 * This class contain utils static functions to use in all project
 * Created by kheireddine on 30/01/17.
 */


public class Utils {
    public final static String TAG = "pm_debug";

    /**
     * Show a short toast Message
     * @param msg : Message to display
     */
    public static void showShortToastMessage(Context context, String msg){
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Show a long toast Message
     * @param msg : Message to display
     */
    public static void showLongToastMessage(Context context, String msg){
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * Check the network connection
     * @param context : Activity context
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    /**
     * Check the api key
     */
    public static boolean isValidApiKey(){
        if (MovieDBServiceAPI.API_KEY.equals("YOUR_API") ||
                MovieDBServiceAPI.API_KEY.isEmpty() ||
                MovieDBServiceAPI.API_KEY.equals("")){
            return false;
        }
        else return true;
    }


    /**
     * Show an alert dialog
     * @param context : Activity context
     * @param title   : Dialog title
     * @param message : Dialog message
     */
    public static void showDialog(Context context, String title, String message){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


}
