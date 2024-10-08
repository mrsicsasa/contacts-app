package com.example.vezbe3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class EditScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.editscreen);
        TextView ime = (TextView) findViewById(R.id.tfIme);
        TextView prezime = (TextView) findViewById(R.id.tfIme);
        TextView telefon = (TextView) findViewById(R.id.tfIme);
        TextView skype = (TextView) findViewById(R.id.tfIme);
        Button potvrdi = (Button) findViewById(R.id.btnPotvrda);
        Intent intent = new Intent(this, MainActivity.class);
        Intent dolazni = getIntent();
        potvrdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("tip", "kreiranje");
                intent.putExtra("ime", String.valueOf(ime.getText()));
                intent.putExtra("prezime", String.valueOf(prezime.getText()));
                intent.putExtra("telefon", String.valueOf(telefon.getText()));
                intent.putExtra("skype", String.valueOf(skype.getText()));
                intent.putExtra("podaci", String.valueOf(dolazni.getStringExtra("podaci")));
                startActivity(intent);
            }
        });
    }
}
