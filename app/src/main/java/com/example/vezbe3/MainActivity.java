package com.example.vezbe3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
        Intent dolazni = getIntent();
        kontakti = popuniListuTestPodacima(dolazni);
        TextView searchField = (TextView) findViewById(R.id.twSearch);
        Button dodajKontakt = (Button) findViewById(R.id.buttonDodaj);
        Intent intent = new Intent(this, EditScreenActivity.class);
        Kontakt novi = new Kontakt(dolazni.getStringExtra("ime"), dolazni.getStringExtra("skype"), dolazni.getStringExtra("telefon"), dolazni.getStringExtra("prezime"));
       // kontakti.add(novi);
        ArrayList<Kontakt> finalKontakti1 = kontakti;
        dodajKontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("podaci",kontaktiString(finalKontakti1));
                intent.putExtra("isEdit", "no");
                startActivity(intent);
            }
        });
        ArrayList<Kontakt> finalKontakti = kontakti;

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String searchText = searchField.getText().toString();
                if (!searchText.isEmpty()) {
                    ArrayList<Kontakt> filtrirani = filtriraj(finalKontakti, searchText);
                    prikaziKontakte(filtrirani);
                } else {
                    prikaziKontakte(finalKontakti);
                }
            }
        });
        prikaziKontakte(kontakti);
        };
    private ArrayList<Kontakt> popuniListuTestPodacima(Intent intent) {
        ArrayList<Kontakt> kontakti = new ArrayList<Kontakt>();
        String podaci = intent.getStringExtra("podaci");
        if (podaci == null || podaci.isEmpty()) {
            kontakti = popuniPocetnePodatke();
        }
        else {
           kontakti = popuniStarePodatke(intent.getStringExtra("podaci"),intent);
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
            Button obrisi = (Button) view.findViewById(R.id.btnObrisi);
            obrisi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    kontakti.remove(kontakt);
                    System.out.println("obrisan"+kontakti.size());
                    prikaziKontakte(kontakti);
                }
            });
            int finalI = i;
            view.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, EditScreenActivity.class);
                intent.putExtra("podaci",kontaktiString(kontakti));
                intent.putExtra("ime", kontakt.getIme());
                intent.putExtra("prezime", kontakt.getPrezime());
                intent.putExtra("telefon", kontakt.getTelefon());
                intent.putExtra("skype", kontakt.getSkype());
                intent.putExtra("isEdit", "yes");
                intent.putExtra("pozicija", finalI);
                startActivity(intent);
            });
            ((LinearLayout) findViewById(R.id.scrollViewLayout)).addView(view);
        }
        oboj();
    }
    private void oboj() {
        int broj = ((LinearLayout)findViewById(R.id.scrollViewLayout)).getChildCount();
        for(int i = 0; i < broj; i++) {
            int bojaPozadine = (i % 2 == 0) ? Color.BLUE : Color.GRAY;
            ((LinearLayout) findViewById(R.id.scrollViewLayout)).getChildAt(i).setBackgroundColor(bojaPozadine);
        }
    }
    private ArrayList<Kontakt> filtriraj(ArrayList<Kontakt> kontakti,String params) {
        ArrayList<Kontakt> filtrirani = new ArrayList<Kontakt>();
        for(Kontakt kontakt : kontakti) {
            if (kontakt.getIme().toLowerCase().contains(params) || kontakt.getPrezime().toLowerCase().contains(params) || kontakt.getTelefon().toLowerCase().contains(params) || kontakt.getSkype().toLowerCase().contains(params)) {
                filtrirani.add(kontakt);
            }
        }
        return filtrirani;
    }
    private String kontaktiString(ArrayList<Kontakt> kontakti) {
        String rezultat = "";
        for (Kontakt kontakt : kontakti) {
            rezultat+=kontakt.getIme()+"-"+kontakt.getPrezime()+"-"+kontakt.getTelefon()+"-"+kontakt.getSkype()+",";
        }
        return rezultat;
    }
    private ArrayList<Kontakt> popuniPocetnePodatke() {
        ArrayList<Kontakt> kontakti= new ArrayList<Kontakt>();
        for(int i=0; i<20; i++) {
            kontakti.add(new Kontakt("ime"+String.valueOf(i), "skype"+String.valueOf(i), "telefon"+String.valueOf(i), "prezime"+String.valueOf(i)));
        }
        return kontakti;
    }
    private ArrayList<Kontakt> popuniStarePodatke(String vrednosti, Intent intent) {
        ArrayList<Kontakt> kontakti= new ArrayList<Kontakt>();
        String [] podaci = vrednosti.split(",");
        for (int i =0; i<podaci.length;i++) {
            String [] atributi = podaci[i].split("-");
            kontakti.add(new Kontakt(atributi[0], atributi[3],atributi[2],atributi[1]));
        }
        String ime = intent.getStringExtra("ime");
        String prezime = intent.getStringExtra("prezime");
        String telefon = intent.getStringExtra("telefon");
        String skype = intent.getStringExtra("skype");
        String isEdit = intent.getStringExtra("isEdit");
        System.out.println("isEdit je "+isEdit);
        int pozicija = intent.getIntExtra("pozicija", -1);
        if(!(ime.isEmpty()) && !isEdit.equals("yes")){
            kontakti.add(new Kontakt(intent.getStringExtra("ime"),intent.getStringExtra("skype"),intent.getStringExtra("telefon"),intent.getStringExtra("prezime")));
        }
        else if (isEdit.equals("yes")) {
            System.out.println("isEdit radi");
            kontakti.get(pozicija).setIme(ime);
            kontakti.get(pozicija).setPrezime(prezime);
            kontakti.get(pozicija).setTelefon(telefon);
            kontakti.get(pozicija).setSkype(skype);
            System.out.println("isEdit radi"+ kontakti.get(pozicija).getIme());
        }
        return kontakti;
    }
}
