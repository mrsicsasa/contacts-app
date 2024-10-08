package com.example.vezbe3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ArrayList<Kontakt> kontakti = new ArrayList<Kontakt>();
        kontakti = popuniListuTestPodacima();
        prikaziKontakte(kontakti);
        };
    private ArrayList<Kontakt> popuniListuTestPodacima() {
        ArrayList<Kontakt> kontakti = new ArrayList<Kontakt>();
        for(int i=0; i<20; i++) {
            kontakti.add(new Kontakt("ime"+String.valueOf(i), "skype"+String.valueOf(i), "telefon"+String.valueOf(i), "prezime"+String.valueOf(i)));
        }
        return kontakti;
    }
    private void prikaziKontakte(ArrayList<Kontakt> kontakti) {
        LinearLayout scrollViewLayout = findViewById(R.id.scrollViewLayout);
        scrollViewLayout.removeAllViews();
        for(int i = 0; i < kontakti.size(); i++) {
            Kontakt kontakt = kontakti.get(i);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.jedanred, null);
            TextView ime = view.findViewById(R.id.twIme);
            TextView prezime = view.findViewById(R.id.twPrezime);
            TextView telefon = view.findViewById(R.id.twTelefon);
            TextView skype = view.findViewById(R.id.twSkype);
            ime.setText(kontakt.getIme());
            prezime.setText(kontakt.getPrezime());
            telefon.setText(kontakt.getTelefon());
            skype.setText(kontakt.getSkype());
            oboj();
            Button obrisi = (Button) view.findViewById(R.id.btnObrisi);
            obrisi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    kontakti.remove(kontakt);
                    System.out.println("obrisan"+kontakti.size());
                    prikaziKontakte(kontakti);
                }
            });
            ((LinearLayout) findViewById(R.id.scrollViewLayout)).addView(view);
        }
    }
    private void oboj() {
        int broj = ((LinearLayout)findViewById(R.id.scrollViewLayout)).getChildCount();
        for(int i = 0; i < broj; i++) {
            int bojaPozadine = (i % 2 == 0) ? Color.BLUE : Color.GRAY;
            ((LinearLayout) findViewById(R.id.scrollViewLayout)).getChildAt(i).setBackgroundColor(bojaPozadine);
        }
    }
}
