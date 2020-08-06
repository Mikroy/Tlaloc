package com.example.tlaloc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.tlaloc.variablesGlobales.passwordTemporal;
import static com.example.tlaloc.variablesGlobales.usuarioTemporal;

public class loginActivity extends AppCompatActivity {
    TextView txtVersion;
    EditText edtUsuario, edtPassword;
    Button btnAccesar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnAccesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = edtUsuario.getText().toString();
                String password = edtPassword.getText().toString();
                if(usuario.isEmpty()){
                    Toast.makeText(context,getString(R.string.faltaUsuario),Toast.LENGTH_SHORT).show();
                }else if(password.isEmpty()){
                    Toast.makeText(context,getString(R.string.faltaPassword),Toast.LENGTH_SHORT).show();
                }else{ // Entra aquí si usuario y password están vacíos
                    if(usuario.equals(usuarioTemporal)){
                        if(password.equals((passwordTemporal))){
                            Toast.makeText(context,getString(R.string.accesoCorrecto),Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(context,MainActivity.class));
                            myData myData = new myData(context);
                            myData.guardarUsario(usuario);
                            myData.setLogeo(true);
                        }else{
                            Toast.makeText(context,getString(R.string.accesoIncorrecto),Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(context,getString(R.string.accesoIncorrecto),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void configuracionesIniciales(){
        txtVersion = findViewById(R.id.txtLoginVersion);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        btnAccesar = findViewById(R.id.btnAccesar);
        context = loginActivity.this;
    }
}