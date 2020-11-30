package com.ftninformatika.zadatak.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ftninformatika.zadatak.Models.Jelo;
import com.ftninformatika.zadatak.R;

import java.util.List;

public class JeloAdapter extends BaseAdapter {

    private List<Jelo> jela;
    private Activity activity;

    public JeloAdapter(List<Jelo> jela, Activity activity) {
        this.jela = jela;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return jela.size();
    }

    @Override
    public Jelo getItem(int i) {
        return jela.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.listview_jela_naziv_kategorija, null);
        }

        TextView tvNaziv = view.findViewById(R.id.textView_NazivJela);
        TextView tvKategorija = view.findViewById(R.id.textView_KategorijaJela);

        tvNaziv.setText(jela.get(i).getNaziv());
        tvKategorija.setText(jela.get(i).getKategorija());

        return view;
    }
}
