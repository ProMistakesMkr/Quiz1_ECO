package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Encuesta_1 extends AppCompatActivity implements View.OnClickListener {

    private CheckBox nE1, nE2, nE5;
    private Button contiBtn;
    int puntos;
    String name, ident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta_n_e);

        //Las ref de todos
        nE1 = findViewById(R.id.nE1);
        nE2 = findViewById(R.id.nE2);
        nE5 = findViewById(R.id.nE5);
        contiBtn = findViewById(R.id.contiBtn);

        //Botón
        contiBtn.setOnClickListener(this);
        //Checkbox
        nE1.setOnClickListener(this);
        nE2.setOnClickListener(this);
        nE5.setOnClickListener(this);
        //Lo que vale el puntaje en este punto
        puntos = 0;


        name = getIntent().getExtras().getString("name");
        ident = getIntent().getExtras().getString("ident");

        //Este es para que esté invisible cuando apenas entra la persona a esa pantalla
        if (nE1.isChecked() == false && nE2.isChecked() == false && nE5.isChecked() == false) {
            contiBtn.setVisibility(View.INVISIBLE);
        }

    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.contiBtn:

                Intent n = new Intent(this, Encuesta_2.class);
                n.putExtra("name",name);
                n.putExtra("ident",ident);
                n.putExtra("puntos", puntos);
                startActivity(n);
                finish();
                break;

            case R.id.nE1:
                nE1.isChecked();

                if(nE1.isChecked()){
                    puntos+=3;
                }else{
                    puntos-=3;
                }

                nE5.setChecked(false);
                break;

            case R.id.nE2:
                nE2.isChecked();
                if (nE2.isChecked()) {
                    puntos += 3;
                }else{
                    puntos+=3;
                }
                nE5.setChecked(false);
                break;


            case R.id.nE5:
                nE5.isChecked();
                if (nE5.isChecked()) {
                    puntos = 0;
                }
                nE1.setChecked(false);
                nE2.setChecked(false);
                break;

        }
        if (nE1.isChecked() || nE2.isChecked() || nE5.isChecked()) {
            contiBtn.setVisibility(View.VISIBLE);
        } else {
            contiBtn.setVisibility(View.INVISIBLE);
        }
    }
}