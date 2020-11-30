package com.ftninformatika.zadatak.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ftninformatika.zadatak.Models.Jelo;
import com.ftninformatika.zadatak.R;

import java.io.InputStream;


public class DetailsFragment extends Fragment {

    private TextView tvNaziv, tvOpis, tvKategorija, tvSastojci, tvKalorije, tvCena;
    private ImageView ivSlika;
    private static Jelo jelo;

    public DetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvNaziv = view.findViewById(R.id.textView_Naziv);
        tvOpis = view.findViewById(R.id.textView_Opis);
        tvKategorija = view.findViewById(R.id.textView_Kategorija);
        tvSastojci = view.findViewById(R.id.textView_Sastojci);
        tvKalorije = view.findViewById(R.id.textView_Kalorije);
        tvCena = view.findViewById(R.id.textView_Cena);

        ivSlika = view.findViewById(R.id.imageView_Slika);

        setupViews();

    }

    private void setupViews() {
        tvNaziv.setText(jelo.getNaziv());
        tvOpis.setText(jelo.getOpis());
        tvKategorija.setText(jelo.getKategorija());
        tvSastojci.setText(jelo.getSastojci());
        tvKalorije.setText(jelo.getKategorija() + " KCAL");
        tvCena.setText(jelo.getCena() + " RSD");

        try {
            InputStream is = getContext().getAssets().open(jelo.getSlikaUrl());
            Drawable drawable = Drawable.createFromStream(is, null);

            ivSlika.setImageDrawable(drawable);
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void setJelo(Jelo jelo) {
        this.jelo = jelo;
    }
}