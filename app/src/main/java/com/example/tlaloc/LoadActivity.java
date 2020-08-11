package com.example.tlaloc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.FitWindowsViewGroup;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static android.Manifest.permission_group.CAMERA;
import static com.example.tlaloc.metodosGlobales.obtenerVersionApp;

public class LoadActivity extends AppCompatActivity {
    // Vistas
    TextView txtInfo, txtVersion;
    Button btnPermisos;
    // Utilidades
    Context context;
    Timer reloj, reloj2;
    boolean permisoCamara = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        txtInfo = findViewById(R.id.txtLoad);
        txtInfo = findViewById(R.id.txtVersion);
        btnPermisos = findViewById(R.id.btnPermisos);
        context = LoadActivity.this;
        txtVersion.setText(obtenerVersionApp(context));
        revisarPermisos();
    }

    @Override
    protected void onResume() {
        // Listener espera a que el evento pase para ejecutarse
        super.onResume();
        btnPermisos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                revisarPermisos();
                btnPermisos.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void revisarPermisos() {
        permisoCamara = checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED;
        if(permisoCamara){
            setReloj(3000);
        }else{
            requestPermissions(new String[]{CAMERA}, 25);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // Que resultados obtuvimos cuando solicitamos los permisos
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 25){
            permisoCamara = checkSelfPermission(CAMERA) == PackageManager.PERMISSION_DENIED;
            if(permisoCamara){
                setReloj(3000);
            }else{
                txtInfo.setText(getString(R.string.alertaPermisos));
                btnPermisos.setVisibility(View.VISIBLE);
            }
        }
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