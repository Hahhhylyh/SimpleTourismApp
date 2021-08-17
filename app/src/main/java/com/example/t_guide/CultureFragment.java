package com.example.t_guide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CultureFragment extends Fragment {

    private CardView cvMY, cvSG, cvJP, cvHK, cvIDN, cvTH;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_culture, container, false);

        cvMY = view.findViewById(R.id.cv_my);
        cvSG = view.findViewById(R.id.cv_sg);
        cvJP = view.findViewById(R.id.cv_jp);
        cvHK = view.findViewById(R.id.cv_hk);
        cvIDN = view.findViewById(R.id.cv_idn);
        cvTH = view.findViewById(R.id.cv_th);

        //Button to Malaysia Culture
        cvMY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key", "1");

                InfoFragment infoF = new InfoFragment();
                infoF.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, infoF).addToBackStack(null).commit();
            }
        });

        //Button to Singapore Culture
        cvSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key", "2");

                InfoFragment infoF = new InfoFragment();
                infoF.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, infoF).addToBackStack(null).commit();
            }
        });

        //Button to Japan Culture
        cvJP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key", "3");

                InfoFragment infoF = new InfoFragment();
                infoF.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, infoF).addToBackStack(null).commit();
            }
        });

        //Button to Hong Kong Culture
        cvHK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key", "4");

                InfoFragment infoF = new InfoFragment();
                infoF.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, infoF).addToBackStack(null).commit();
            }
        });

        //Button to Indonesia Culture
        cvIDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key", "5");

                InfoFragment infoF = new InfoFragment();
                infoF.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, infoF).addToBackStack(null).commit();
            }
        });

        //Button to Thailand Culture
        cvTH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key", "6");

                InfoFragment infoF = new InfoFragment();
                infoF.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, infoF).addToBackStack(null).commit();
            }
        });

        return view;
    }
}