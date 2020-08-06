package com.example.tlaloc;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class metodosGlobales{
    /**
     * Clase Java donde se encunetran todos los metodos globales de la aplicación
     * */
    public static String obtenerVersionApp(Context context){
        String respuesta = "";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            respuesta = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return respuesta;
    }
}
