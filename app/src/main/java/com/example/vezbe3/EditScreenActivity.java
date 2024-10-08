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
        TextView prezime = (TextView) findViewById(R.id.tfPrezime);
        TextView telefon = (TextView) findViewById(R.id.tfTelefon);
        TextView skype = (TextView) findViewById(R.id.tfSkype);
        Button potvrdi = (Button) findViewById(R.id.btnPotvrda);
        Intent intent = new Intent(this, MainActivity.class);
        Intent dolazni = getIntent();
        String isEdit = dolazni.getStringExtra("isEdit");
        int pozicija = dolazni.getIntExtra("pozicija", -1);
        if (isEdit.equals("yes")) {

            ime.setText(dolazni.getStringExtra("ime"));
            prezime.setText(dolazni.getStringExtra("prezime"));
            telefon.setText(dolazni.getStringExtra("telefon"));
            skype.setText(dolazni.getStringExtra("skype"));
        }
        potvrdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("ime", String.valueOf(ime.getText()));
                intent.putExtra("prezime", String.valueOf(prezime.getText()));
                intent.putExtra("telefon", String.valueOf(telefon.getText()));
                intent.putExtra("skype", String.valueOf(skype.getText()));
                intent.putExtra("podaci", String.valueOf(dolazni.getStringExtra("podaci")));
                System.out.println("isEdit tamo je "+isEdit);
                intent.putExtra("isEdit",isEdit);
                intent.putExtra("pozicija",pozicija);
                startActivity(intent);
            }
        });
    }
}
