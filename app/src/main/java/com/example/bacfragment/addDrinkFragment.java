package com.example.bacfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addDrinkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addDrinkFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // My variables
    RadioGroup drinkSize;
    SeekBar alcoholIn;
    double alcPercent;
    TextView percentStr;
    final static public String DRINKS_KEY = "DRINKS";


    public addDrinkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addDrinkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static addDrinkFragment newInstance(String param1, String param2) {
        addDrinkFragment fragment = new addDrinkFragment();
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
        View addDrinkView = inflater.inflate(R.layout.fragment_add_drink, container, false);

        alcoholIn = addDrinkView.findViewById(R.id.alcohol_seekbar);
        percentStr = addDrinkView.findViewById(R.id.percent_txt);
        alcoholIn.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                percentStr.setText(i+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        addDrinkView.findViewById(R.id.cancel2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).switchFragment(view);
            }
        });

        drinkSize = addDrinkView.findViewById(R.id.size_group);
        addDrinkView.findViewById(R.id.addDrink).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println("You will add a drink some how");
                if(drinkSize.getCheckedRadioButtonId() == -1){
                    Toast toast = Toast.makeText(getActivity(), "Please select a drink size", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
                alcPercent = (double) alcoholIn.getProgress()/100;

                int drinkSzOz = drinkSize.getCheckedRadioButtonId();
                int ounces = 0;

                if(drinkSzOz == R.id.one_oz){
                    ounces = 1;
                }
                else if(drinkSzOz == R.id.five_oz){
                    ounces = 5;
                }
                else{
                    ounces = 12;
                }
                Drinks newDrink = new Drinks(alcPercent, ounces);
                ((MainActivity)getActivity()).addDrink(view, newDrink);
            }
        });


        return addDrinkView;
    }
}