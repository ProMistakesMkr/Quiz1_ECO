package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Encuesta_2 extends AppCompatActivity implements View.OnClickListener {

    private CheckBox s1, s2, s3;
    private Button finBtn;
    int puntos;
    String name, ident;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta_sint);

        finBtn = findViewById(R.id.finBtn);
        s1 = findViewById(R.id.s1);
        s2 = findViewById(R.id.s2);
        s3 = findViewById(R.id.s3);





        finBtn.setOnClickListener(this);

        s1.setOnClickListener(this);
        s2.setOnClickListener(this);
        s3.setOnClickListener(this);


        name = getIntent().getExtras().getString("name");
        ident = getIntent().getExtras().getString("ident");
        puntos = getIntent().getExtras().getInt("puntos");

        if (s1.isChecked() == false && s2.isChecked() == false && s3.isChecked() == false) {
            finBtn.setVisibility(View.INVISIBLE);
        }


    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.finBtn:

                Intent s = new Intent(this, MainActivity.class);
                savePuntos();
                startActivity(s);
                break;

            case R.id.s1:
                s1.isChecked();


                if(s1.isChecked()){
                    puntos+=1;
                }else{
                    puntos-=1;
                }

                s3.setChecked(false);
                break;

            case R.id.s2:
                s2.isChecked();
                if (s2.isChecked()) {
                    puntos += 3;
                }else{
                    puntos+=3;
                }
                s3.setChecked(false);
                break;


            case R.id.s3:
                s3.isChecked();
                if (s3.isChecked()) {
                    puntos = 0;
                }
                s1.setChecked(false);
                s2.setChecked(false);

                break;

        }

        if (s1.isChecked() || s2.isChecked() || s3.isChecked()) {
            finBtn.setVisibility(View.VISIBLE);
        } else {
            finBtn.setVisibility(View.INVISIBLE);
        }

    }

    private void savePuntos(){
        SharedPreferences preferences = getSharedPreferences("locker", MODE_PRIVATE);
        String almacenado = preferences.getString("puntajeNE", ""); //Con esto se guarda lo que ya estaba ahí almacenado
        String nu = "Usuario: " + name + " - id: " + ident +" - puntos: " + puntos + "\n";
        preferences.edit().putString("puntajeNE", nu + almacenado).apply();
    }
}
