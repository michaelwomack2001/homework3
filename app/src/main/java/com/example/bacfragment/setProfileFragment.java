package com.example.bacfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link setProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class setProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    //My text feilds
    EditText weightIn;
    RadioGroup genderSel;
    double weight;
    String gender;

    public setProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment setProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static setProfileFragment newInstance(String param1, String param2) {
        setProfileFragment fragment = new setProfileFragment();
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
        View setProfView = inflater.inflate(R.layout.fragment_set_profile, container, false);
        setProfView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).switchFragment(view);
            }
        });
        setProfView.findViewById(R.id.set2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println("You will setProfile here");
                try{
                    weightIn = setProfView.findViewById(R.id.weight_input);
                    weight = Double.parseDouble(weightIn.getText().toString());
                }
                catch (NumberFormatException exception){
                    System.out.println("Weight is not numbers");
                }

                genderSel = setProfView.findViewById(R.id.gender_group);
                int checked = genderSel.getCheckedRadioButtonId();

                if(checked == R.id.female){
                    gender = "Female";
                }
                else if(checked == R.id.male){
                    gender = "Male";
                }

                if(weightIn.getText().length() == 0 || genderSel.getCheckedRadioButtonId() == -1){
                    Toast toast = Toast.makeText(getActivity(), "You must select gender and add weight", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0,0);
                    toast.show();
                }
                else{
                    Profile newUser = new Profile(weight, gender);
                    ((MainActivity)getActivity()).addProfileInfo(view, newUser);
                }
            }
        });


        return setProfView;
    }
}