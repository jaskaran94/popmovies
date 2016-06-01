package com.example.www.popmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MovieAdapter.onAdapterItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MovieFragment())
                    .commit();
        }

    }

    @Override
    public void onItemSelected(String id) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }
}
