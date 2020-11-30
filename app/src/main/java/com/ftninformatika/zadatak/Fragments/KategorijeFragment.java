package com.ftninformatika.zadatak.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ftninformatika.zadatak.Adapters.KategorijeRecyclerAdapter;
import com.ftninformatika.zadatak.R;

public class KategorijeFragment extends Fragment {

    private RecyclerView rvList;
    private ProgressBar progressBar;

    public KategorijeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kategorije, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvList = view.findViewById(R.id.rvList_Kategorije);
        progressBar = view.findViewById(R.id.progressBarKategorije);

        startAsyncTaskLoad();

    }

    private void setupList() {

        rvList.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvList.setLayoutManager(layoutManager);

        KategorijeRecyclerAdapter adapter = new KategorijeRecyclerAdapter((KategorijeRecyclerAdapter.OnElementClickListener) getContext()); //2.
        rvList.setAdapter(adapter);

    }

    private void startAsyncTaskLoad() {
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