package com.ftninformatika.zadatak.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.ftninformatika.zadatak.Adapters.JelaRecyclerAdapter;
import com.ftninformatika.zadatak.Providers.JeloProvider;
import com.ftninformatika.zadatak.R;

public class JelaFragment extends Fragment { //Listfragment

    private RecyclerView rvList;
    private ProgressBar progressBar;

    private String kategorija;

    public JelaFragment() {
    }

    public JelaFragment(String kategorija) {
        this.kategorija = kategorija;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvList = view.findViewById(R.id.rvList);
        rvList.setVisibility(View.GONE);

        progressBar = view.findViewById(R.id.progressBarJela);

        startAsyncTaskLoad();

    }

    public void setupList() {

        progressBar.setVisibility(View.GONE);
        rvList.setVisibility(View.VISIBLE);

        if (kategorija == null) {

            rvList.setHasFixedSize(true);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            rvList.setLayoutManager(layoutManager);

            JelaRecyclerAdapter adapter = new JelaRecyclerAdapter((JelaRecyclerAdapter.OnElementClickListener) getContext());
            rvList.setAdapter(adapter);
        }
        else{
            rvList.setHasFixedSize(true);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            rvList.setLayoutManager(layoutManager);

            JelaRecyclerAdapter adapter = new JelaRecyclerAdapter((JelaRecyclerAdapter.OnElementClickListener) getContext(), JeloProvider.getAllJelaByKategorija(kategorija));
            rvList.setAdapter(adapter);
        }
    }


    private void startAsyncTaskLoad(){
        LoadAsyncTask task = new LoadAsyncTask();
        task.execute(5);
    }

    public class LoadAsyncTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            rvList.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setupList();
            rvList.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            int sec = integers[0];

            for (int i = 0; i < sec; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }
}
