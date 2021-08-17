package com.example.t_guide;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InfoFragment extends Fragment {

    private DatabaseHelper myDb;

    private ImageView ivBack, ivFlag;
    private Button btnOpenWeb;
    private TextView tvToolbarTitle, tvTitle, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        myDb = new DatabaseHelper(view.getContext());

        tvToolbarTitle = view.findViewById(R.id.toolbar_title);
        ivBack = view.findViewById(R.id.iv_back);
        ivFlag = view.findViewById(R.id.flag);
        btnOpenWeb = view.findViewById(R.id.btn_openweb);
        tvTitle = view.findViewById(R.id.title);
        tv1 = view.findViewById(R.id.tips1);
        tv2 = view.findViewById(R.id.tips2);
        tv3 = view.findViewById(R.id.tips3);
        tv4 = view.findViewById(R.id.tips4);
        tv5 = view.findViewById(R.id.tips5);
        tv6 = view.findViewById(R.id.tips6);
        tv7 = view.findViewById(R.id.tips7);
        tv8 = view.findViewById(R.id.tips8);
        tv9 = view.findViewById(R.id.tips9);
        tv10 = view.findViewById(R.id.tips10);

        //Back button to previous fragment
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        //get the data passed from the previous fragment - used to determine which culture id to retrieve
        Bundle bundle = this.getArguments();
        String culture_id = bundle.getString("key");

        //get Culture data
        Cursor res = myDb.getCultureData(culture_id);
        if(res.moveToFirst()){
            tvTitle.setText(res.getString(1));
            tv1.setText(res.getString(2));
            tv2.setText(res.getString(3));
            tv3.setText(res.getString(4));
            tv4.setText(res.getString(5));
            tv5.setText(res.getString(6));
            tv6.setText(res.getString(7));
            tv7.setText(res.getString(8));
            tv8.setText(res.getString(9));
            tv9.setText(res.getString(10));
            tv10.setText(res.getString(11));
        }

        //change action bar title & flag based on the bundle data passed from previous fragment
        switch(culture_id){
            case "1":
                tvToolbarTitle.setText("Malaysia");
                ivFlag.setImageResource(R.drawable.flag_my);
                break;

            case "2":
                tvToolbarTitle.setText("Singapore");
                ivFlag.setImageResource(R.drawable.flag_sg);
                break;

            case "3":
                tvToolbarTitle.setText("Japan");
                ivFlag.setImageResource(R.drawable.flag_jp);
                break;

            case "4":
                tvToolbarTitle.setText("Hong Kong");
                ivFlag.setImageResource(R.drawable.flag_hk);
                break;

            case "5":
                tvToolbarTitle.setText("Indonesia");
                ivFlag.setImageResource(R.drawable.flag_idn);
                break;

            case "6":
                tvToolbarTitle.setText("Thailand");
                ivFlag.setImageResource(R.drawable.flag_th);
                break;

            default:
                tvToolbarTitle.setText("Malaysia");
                ivFlag.setImageResource(R.drawable.flag_my);
                break;
        }

        //Button to open FurtherReadingActivity which will direct to access website
        btnOpenWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FurtherReadingActivity.class);
                intent.putExtra("culture_id", culture_id);
                startActivity(intent);
            }
        });

        return view;
    }
}
