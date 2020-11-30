package com.ftninformatika.zadatak;

import android.app.AlertDialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.ftninformatika.zadatak.Adapters.JelaRecyclerAdapter;
import com.ftninformatika.zadatak.Adapters.KategorijeRecyclerAdapter;
import com.ftninformatika.zadatak.Dialogs.DialogAbout;
import com.ftninformatika.zadatak.Dialogs.DialogChoose;
import com.ftninformatika.zadatak.Fragments.DetailsFragment;
import com.ftninformatika.zadatak.Fragments.KategorijeFragment;
import com.ftninformatika.zadatak.Fragments.JelaFragment;
import com.ftninformatika.zadatak.Fragments.PreferenceFragment;
import com.ftninformatika.zadatak.Models.Jelo;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JelaRecyclerAdapter.OnElementClickListener , KategorijeRecyclerAdapter.OnElementClickListener {

    private List<String> drawerItems;
    private ListView drawerList;
    private DrawerLayout drawerLayout;

    private Toolbar toolbar;
    private AlertDialog dialog_about;
    private AlertDialog dialog_choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillData();
        setupToolbar();
        setupDrawer();

    }

    private void fillData() {
        drawerItems = new ArrayList<>();
        drawerItems.add("Kategorije");
        drawerItems.add("Jela");
        drawerItems.add("Podesavanja");
        drawerItems.add("O aplikaciji");
    }

    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
            actionBar.setHomeButtonEnabled(true);
            actionBar.show();
        }
    }

    private void showSnackbar() {
        Snackbar snackbar;

        snackbar = Snackbar.make(findViewById(R.id.root), "Unos|Izmena|Brisanje", Snackbar.LENGTH_LONG);
        snackbar.show();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            showSnackbar();
            showDialogChoose(0);
        } else if (item.getItemId() == R.id.edit) {
            showSnackbar();
            showDialogChoose(1);
        } else if (item.getItemId() == R.id.delete) {
            showSnackbar();
            showDialogChoose(2);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setupDrawer() {
        drawerList = findViewById(R.id.leftDrawer);
        drawerLayout = findViewById(R.id.drawer_layout);

        ArrayAdapter<String> adapter =  new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, drawerItems);
        drawerList.setAdapter(adapter);
        drawerList.setOnItemClickListener((parent, view, position, id) -> {
            String title = "";
            if (position == 0) {
                title = "Kategorije";
                showKategorijeFragment();
            } else if (position == 1) {
                title = "Jela";
                showJelaFragment();
            } else if (position == 2) {
                title = "Podesavanja";
                showPreferences();
            } else if (position == 3) {
                title = "O aplikaciji";
                showDialog();
            }
            setTitle(title);
            drawerLayout.closeDrawer(drawerList);
        });
        new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

    }

    private void showKategorijeFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        KategorijeFragment fragment = new KategorijeFragment();
        transaction.replace(R.id.root, fragment);
        transaction.commit();
    }

    public void showJelaFragment() {
        JelaFragment listfragment = new JelaFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.root, listfragment).commit();
    }

    public void showJelaByKategorijaFragment(String kategorija) {
        JelaFragment listfragment = new JelaFragment(kategorija);
        getSupportFragmentManager().beginTransaction().replace(R.id.root, listfragment).commit();
    }

    private void showDetails(Jelo jelo) {
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setJelo(jelo);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root, detailsFragment)
                .addToBackStack(null)
                .commit();
    }

    private void showPreferences() {
        FragmentTransaction fragment = getSupportFragmentManager().beginTransaction();
        PreferenceFragment preferenceFragment = new PreferenceFragment();
        fragment.replace(R.id.root, preferenceFragment);
        fragment.commit();
    }

    private void showDialog() {
        if (dialog_about == null)
            dialog_about = new DialogAbout(this).prepareDialog();
        else if (dialog_about.isShowing())
            dialog_about.dismiss();
        dialog_about.show();
    }

    private void showDialogChoose(int id) {
        if (dialog_choose == null)
            dialog_choose = new DialogChoose(this).prepareDialog(id);
        else if (dialog_choose.isShowing())
            dialog_choose.dismiss();
        dialog_choose.show();
    }

    @Override
    public void onElementClicked(Jelo jelo) {
        showDetails(jelo);
    }

    @Override
    public void onElementClicked(String kategorija) {
        showJelaByKategorijaFragment(kategorija);
    }
}