package com.example.tlaloc;

import android.content.Context;
import android.content.SharedPreferences;

public class myData {
    private SharedPreferences preferences;

    /**
     * CONSTRUCTOR DE CLASE MYDATA, SOLCITIA EL CONTEXT PARA CONFIGURAR PREFERENCES
     */
    public myData(Context context){
        // Configura preferences, se crea un archivo llamado sharedCursoAvanzado de modo privado.
        preferences = context.getSharedPreferences("sharedCursoAvanzado",Context.MODE_PRIVATE);
    }

    public void guardarUsario(String usuario){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nombreUsuario", usuario);
        editor.apply();
    }

    public void setLogeo(Boolean access){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("login", access);
        editor.apply();
    }

    public String obtenerUsuario(){
        return preferences.getString("nombreUsuario", "");
    }

    public Boolean getLogeo(){
        return preferences.getBoolean("login", false);
    }
}
