package com.example.bacfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String TAG = "demo";
    String gotToFrag = "BacCalc";
    TextView initialWeight, drinksTally, bacOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.fragmentContainer, new BacCalculationFragment());
        ft.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void switchFragment(View view, String fragmentId ){
        Fragment newFragment = null;
        if(fragmentId.equals("bacCalc")){
            newFragment = new BacCalculationFragment();
        }
        else if(fragmentId.equals("setProf")){
            newFragment = new setProfileFragment();
        }
        else if(fragmentId.equals("viewDrinks")){
            newFragment = new viewDrinksFragment();
        }
        else if(fragmentId.equals("addDrinks")){
            newFragment = new addDrinkFragment();
        }
        setFragment(this, newFragment);
    }
    public void switchFragment(View view){
        Fragment newFragment = null;
        switch(view.getId()){
            case R.id.addDrink:
                newFragment = new addDrinkFragment();
                break;
            case R.id.view_drink:
                newFragment = new viewDrinksFragment();
                break;
            case R.id.set:
                newFragment = new setProfileFragment();
                break;
            case R.id.cancel:
            case R.id.cancel2:
            case R.id.close_view:
                newFragment = new BacCalculationFragment();
                break;
        }
        setFragment(this, newFragment);
    }

    public static void setFragment(AppCompatActivity activity, Fragment fragment){
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();

    }
    public static void sendUserAndFragment(AppCompatActivity activity, Fragment fragment, Profile user){
        //Sending the User Object
        BacCalculationFragment.setProfile(user);
        //Switching to BacCalc
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }
    public void addProfileInfo(View view, Profile newUser){
        initialWeight = view.findViewById(R.id.initial_weight);
        BacCalculationFragment.setProfile(newUser);
        Fragment nextFragment = new BacCalculationFragment();
        setFragment(this, nextFragment);
    }

    public void addDrink(View view, Drinks newDrink){
        drinksTally = view.findViewById(R.id.num_drink);
        System.out.println(newDrink.getSize());
        BacCalculationFragment.addDrink(newDrink);
        Fragment nextFragment = new BacCalculationFragment();
        setFragment(this, nextFragment);
    }

    public void gotToViewDrinks(View view, ArrayList<Drinks> drinksList){
        viewDrinksFragment.getDrinksList(drinksList);
        Fragment nextFragment = new viewDrinksFragment();
        setFragment(this, nextFragment);
    }
}