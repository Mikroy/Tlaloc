package com.example.tlaloc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.FitWindowsViewGroup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.tlaloc.metodosGlobales.obtenerVersionApp;

public class LoadActivity extends AppCompatActivity {
    // Vistas
    TextView txtInfo, txtVersion;
    // Utilidades
    Context context;
    Timer reloj, reloj2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        txtInfo = findViewById(R.id.txtLoad);
        txtInfo = findViewById(R.id.txtVersion);
        context = LoadActivity.this;
        txtVersion.setText(obtenerVersionApp(context));
        setReloj(3000);
    }

    private void setReloj(int tiempo){ // Recibe cantidad de milisegundos
        reloj = new Timer(); // Declarando el timer como nuevo
        reloj.schedule(new TimerTask() {
            @Override
            public void run() {
                // Aquí se ejecuta el código cuando se cumple el tiempo
                Handler handler = new Handler(getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Aquí nos regresa al hilo principal
                        txtInfo.setText(getString(R.string.Preparando));
                        setReloj2(1000);
                    }
                });
            }
        },tiempo);
    }
    private void setReloj2(int tiempo){ // Recibe cantidad de milisegundos
        reloj2 = new Timer(); // Declarando el timer como nuevo
        reloj2.schedule(new TimerTask() {
            @Override
            public void run() {
                // Aquí se ejecuta el código cuando se cumple el tiempo
                Handler handler = new Handler(getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Aquí nos regresa al hilo principal
                        txtInfo.setText(getString(R.string.Espere));
                        myData myData = new myData(context);
                        boolean acceso = myData.getLogeo();
                        if(acceso){
                            startActivity(new Intent(context,MainActivity.class));
                        }else{
                            startActivity(new Intent(context,loginActivity.class));
                        }
                    }
                });
            }
        },tiempo);
    }
}