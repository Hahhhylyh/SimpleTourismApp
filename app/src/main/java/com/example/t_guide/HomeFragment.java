package com.example.t_guide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    private Button btnMy, btnSg, btnJp, btnHk;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnMy = view.findViewById(R.id.home_malaysia);
        btnSg = view.findViewById(R.id.home_singapore);
        btnJp = view.findViewById(R.id.home_japan);
        btnHk = view.findViewById(R.id.home_hongkong);

        //Button to Malaysia Page
        btnMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("country", "1");

                LocationFragment lf = new LocationFragment();
                lf.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, lf).addToBackStack(null).commit();
            }
        });

        //Button to Singapore Page
        btnSg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("country", "2");

                LocationFragment lf = new LocationFragment();
                lf.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, lf).addToBackStack(null).commit();
            }
        });

        //Button to Japan Page
        btnJp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("country", "3");

                LocationFragment lf = new LocationFragment();
                lf.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, lf).addToBackStack(null).commit();
            }
        });

        //Button to Hong Kong Page
        btnHk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("country", "4");

                LocationFragment lf = new LocationFragment();
                lf.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, lf).addToBackStack(null).commit();
            }
        });

        return view;
    }
}