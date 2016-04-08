package com.fourall.exercicioselecaomobile.helpers;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by julianonardon on 05/04/16.
 */
public class ConnectionHelper {

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // Testa conexão
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
