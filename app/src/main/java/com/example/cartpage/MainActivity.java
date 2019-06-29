package com.example.cartpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TryCartFragment.OnFragmentInteractionListener {

    private Fragment fragment;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment();
    }

    public void addFragment() {
        manager = getSupportFragmentManager();
        fragment = manager.findFragmentById(R.id.frameContainer);
        if(fragment==null) {
            fragment = new TryCartFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.frameContainer,fragment);
            transaction.commit();
        }
    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
