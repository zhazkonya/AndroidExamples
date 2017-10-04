package com.example.zhaziraoskenbayeva.fragmentexample;

import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
    implements AnimalListFragment.OnFragmentInteractionListener,
        AnimalDetailsFragment.OnFragmentInteractionListener{

    FragmentManager fm;

    @BindView(R.id.welcomeTxt) TextView welTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        fm = getFragmentManager();

    }

    @OnClick(R.id.welcomeTxt)
    public void sayWelcome(){
        welTxtView.setText("WELCOME AGAIN!!!");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onAnimalSelection(String name) {
        //Toast.makeText(this, name + " was clicked", Toast.LENGTH_LONG).show();
        AnimalDetailsFragment animalDetFrag =
                (AnimalDetailsFragment) fm.findFragmentById(R.id.aDetailsFrag);
        animalDetFrag.updateView(name);
    }
}
