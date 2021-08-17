package com.example.t_guide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LocationFragment extends Fragment {

    private ImageView ivBack;
    private TextView tbTitle;
    private Button btn1, btn2, btn3, btn4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        ivBack = view.findViewById(R.id.iv_back);
        tbTitle = view.findViewById(R.id.toolbar_title);
        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);

        Bundle bundle = this.getArguments();
        String country = bundle.getString("country");

        //Set Title, Location Name, and Location Image based on the value passed in the previous fragment
        if(bundle != null){
            switch(country){
                case "1":
                    tbTitle.setText("Malaysia");
                    btn1.setText("Petronas Twin Towers");
                    btn1.setBackgroundResource(R.drawable.my1);
                    btn2.setText("Resorts World Genting");
                    btn2.setBackgroundResource(R.drawable.my2);
                    btn3.setText("A Famosa");
                    btn3.setBackgroundResource(R.drawable.my3);
                    btn4.setText("Sunway Lagoon");
                    btn4.setBackgroundResource(R.drawable.my4);
                    break;

                case "2":
                    tbTitle.setText("Singapore");
                    btn1.setText("Universal Studios Singapore");
                    btn1.setBackgroundResource(R.drawable.sg1);
                    btn2.setText("Sentosa");
                    btn2.setBackgroundResource(R.drawable.sg2);
                    btn3.setText("Gardens by the Bay");
                    btn3.setBackgroundResource(R.drawable.sg3);
                    btn4.setText("Merlion");
                    btn4.setBackgroundResource(R.drawable.sg4);
                    break;

                case "3":
                    tbTitle.setText("Japan");
                    btn1.setText("Mount Fuji");
                    btn1.setBackgroundResource(R.drawable.jp1);
                    btn2.setText("Itsukushima Shrine");
                    btn2.setBackgroundResource(R.drawable.jp2);
                    btn3.setText("Osaka Castle");
                    btn3.setBackgroundResource(R.drawable.jp3);
                    btn4.setText("Akihabara");
                    btn4.setBackgroundResource(R.drawable.jp4);
                    break;

                case "4":
                    tbTitle.setText("Hong Kong");
                    btn1.setText("Hong Kong Disneyland");
                    btn1.setBackgroundResource(R.drawable.hk1);
                    btn2.setText("Ocean Park");
                    btn2.setBackgroundResource(R.drawable.hk2);
                    btn3.setText("Lan Kwai Fong");
                    btn3.setBackgroundResource(R.drawable.hk3);
                    btn4.setText("The Peak Tram");
                    btn4.setBackgroundResource(R.drawable.hk4);
                    break;

                default:
                    tbTitle.setText("Error");
                    break;
            }
        }

        //Button to next intro fragment with the first location of each country
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("country", country);
                bundle.putString("location", "1");

                IntroFragment introF = new IntroFragment();
                introF.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, introF).addToBackStack(null).commit();
            }
        });

        //Button to next intro fragment with the second location of each country
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("country", country);
                bundle.putString("location", "2");

                IntroFragment introF = new IntroFragment();
                introF.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, introF).addToBackStack(null).commit();
            }
        });

        //Button to next intro fragment with the third location of each country
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("country", country);
                bundle.putString("location", "3");

                IntroFragment introF = new IntroFragment();
                introF.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, introF).addToBackStack(null).commit();
            }
        });

        //Button to next intro fragment with the forth location of each country
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("country", country);
                bundle.putString("location", "4");

                IntroFragment introF = new IntroFragment();
                introF.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, introF).addToBackStack(null).commit();
            }
        });

        //Back button to previous fragment
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }
}