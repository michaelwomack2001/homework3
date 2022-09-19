package com.example.bacfragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BacCalculationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BacCalculationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Custom parms
    private static Profile user;
    TextView initialWeight, bacOut, drinkTally,status;

    static ArrayList<Drinks> drinksList = new ArrayList<Drinks>();
    static int drinksTallyInt;
    public BacCalculationFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BacCalculationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BacCalculationFragment newInstance(String param1, String param2) {
        BacCalculationFragment fragment = new BacCalculationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_bac_calculation, container, false);
        initialWeight = mainView.findViewById(R.id.initial_weight);
        drinkTally = mainView.findViewById(R.id.num_drink);

        mainView.findViewById(R.id.addDrink).setEnabled(false);
        mainView.findViewById(R.id.view_drink).setEnabled(false);

        mainView.findViewById(R.id.addDrink).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).switchFragment(view);
            }
        });
        mainView.findViewById(R.id.set).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).switchFragment(view);
            }
        });
        mainView.findViewById(R.id.view_drink).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).gotToViewDrinks(view,drinksList);
            }
        });
        mainView.findViewById(R.id.reset).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try{
                    status.setBackgroundResource(R.drawable.roundedcorner);
                    GradientDrawable drawable = (GradientDrawable) status.getBackground();

                    drinksList.clear();
                    bacOut.setText("0.000");
                    initialWeight.setText("N/A");
                    drinkTally.setText("0");
                    status.setText("You are safe");
                    drawable.setColor(Color.GREEN);
                    user = new Profile();
                    mainView.findViewById(R.id.addDrink).setEnabled(false);
                    mainView.findViewById(R.id.view_drink).setEnabled(false);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        try {
            initialWeight.setText(Double.toString(user.getWeight()));
            mainView.findViewById(R.id.addDrink).setEnabled(true);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        try{
            drinksTallyInt = drinksList.size();
            drinkTally.setText(Integer.toString(drinksTallyInt));
            if(drinksList.size() != 0) {
                mainView.findViewById(R.id.view_drink).setEnabled(true);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        try{
           bacCalculaton(mainView);
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return mainView;
    }
    public static void setProfile(Profile newUser){
        user = newUser;
    }

    public static void addDrink(Drinks newDrink){
        System.out.println(newDrink.getAlcPercent());
        drinksList.add(newDrink);
    };

    public void bacCalculaton(View mainView){
        double bacNum = 0.0;
        double consumed = 0.0;
        String bacStr;
        bacOut = mainView.findViewById(R.id.bac_level);
        for(int i = 0; i < drinksList.size(); i++){
            consumed += drinksList.get(i).getAlcPercent()*drinksList.get(i).getSize();
            System.out.println(consumed);
        }
        if(user.getGender() == "Male"){
            bacNum = (consumed * 5.14)/(user.getWeight() * 0.73);
            System.out.println("Bac Out: " + bacNum);
            bacStr = String.format("%.2f", bacNum);
            bacOut.setText(bacStr);
        }
        else {
            bacNum = (consumed * 5.14) / (user.getWeight() * 0.66);
            System.out.println("Bac Out: " + bacNum);
            bacStr = String.format("%.2f", bacNum);
            bacOut.setText(bacStr);
        }

        status = mainView.findViewById(R.id.status);
        status.setBackgroundResource(R.drawable.roundedcorner);
        GradientDrawable drawable = (GradientDrawable) status.getBackground();

        if (bacNum >= 0.25) {

            //disable add drink
            mainView.findViewById(R.id.addDrink).setEnabled(false);
            status.setText("Over the limit");
            drawable.setColor(Color.RED);


            Toast overLimit = Toast.makeText(getActivity(), "No more drinks for you!", Toast.LENGTH_LONG);
            overLimit.setGravity(Gravity.CENTER, 0, 0);
            overLimit.show();

        } else if (bacNum > 0.2) {
            status.setText("Over the limit");
            drawable.setColor(Color.RED);

        } else if (bacNum > 0.08) {
            status.setText("Be Careful!");
            drawable.setColor(Color.YELLOW);
        } else {
            status.setText("You're Safe");
            drawable.setColor(Color.GREEN);
        }
    }
}