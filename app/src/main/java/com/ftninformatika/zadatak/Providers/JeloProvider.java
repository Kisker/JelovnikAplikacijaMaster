package com.ftninformatika.zadatak.Providers;

import com.ftninformatika.zadatak.Models.Jelo;

import java.util.ArrayList;
import java.util.List;

public class JeloProvider {
    private static List<Jelo> jela = null;
    private static List<Jelo> jelaByKategorija = null;
    private static List<String> kategorije = null;

    private static void init() {
        if (jela == null) {
            jela = new ArrayList<>();
            jela.add(new Jelo(
                    0, "jelo1.jpg", "Sarma", "Sarma sa kupusom", "Glavno jelo",
                    "Mleveno meso i kupus"
                    , 300, 499.99));
            jela.add(new Jelo(
                    1, "jelo2.jpg", "Palacinke", "Palacinka sa kremom i plazmom", "Desert",
                    "Jaja , brasno , mleko , voda , krem , plazma", 450, 149.99));
            jela.add(new Jelo(
                    2, "jelo3.jpg", "Salata", "Salata od kupusa", "Dodatak jelu",
                    "Kupus , sirce , so", 40, 99.99));
            jela.add(new Jelo(
                    3, "jelo4.jpg", "Meze", "Lako jelo pre glavnog jela", "Predjelo",
                    "Kulen , slanina , salama , sir", 150, 199.99));
        }
    }

    public static List<Jelo> getAllJela() {
        init();

        return jela;
    }

    public static List<String> getKategorije(){
        init();

        kategorije = new ArrayList<>();

        for (int i = 0; i < jela.size(); i++) {
            if (!kategorije.contains(jela.get(i).getKategorija()))
                kategorije.add(jela.get(i).getKategorija());
        }

        return kategorije;
    }

    public static List<Jelo> getAllJelaByKategorija(String kategorija) {
        init();

        jelaByKategorija = new ArrayList<>();

        for (int i = 0; i < jela.size(); i++) {
            if (getJeloById(i).getKategorija().equals(kategorija))
                jelaByKategorija.add(jela.get(i));
        }

        return jelaByKategorija;
    }

    public static Jelo getJeloById(int id) {
        init();

        if (id >= 0 && id < jela.size()) {
            return jela.get(id);
        }
        return null;
    }

    public static Jelo getJeloByNaziv(String naziv) {
        init();

        for(int i=0;i<jela.size();i++){
            if(jela.get(i).getNaziv().equals(naziv))
                return jela.get(i);
        }
        return null;
    }
}
