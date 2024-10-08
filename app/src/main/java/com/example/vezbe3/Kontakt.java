package com.example.vezbe3;

public class Kontakt {
    private String ime;
    private String prezime;
    private String telefon;
    private String skype;

    public Kontakt(String ime, String skype, String telefon, String prezime) {
        this.ime = ime;
        this.skype = skype;
        this.telefon = telefon;
        this.prezime = prezime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
}
