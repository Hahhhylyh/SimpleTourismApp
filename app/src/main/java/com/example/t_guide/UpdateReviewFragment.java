package com.example.t_guide;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UpdateReviewFragment extends Fragment {

    private DatabaseHelper myDb;
    SharedPreferences pref;

    private Button btnSave, btnCancel;
    private ImageView ivBack;
    private TextView tvTitle;
    private RatingBar rbRating;
    private EditText etReview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_review, container, false);

        myDb = new DatabaseHelper(view.getContext());
        pref = this.getActivity().getSharedPreferences("user_details", 0);

        ivBack = view.findViewById(R.id.iv_back);
        tvTitle = view.findViewById(R.id.toolbar_title);
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnSave = view.findViewById(R.id.btn_save);
        rbRating = view.findViewById(R.id.rb_rating);
        etReview = view.findViewById(R.id.et_review);

        //get the data passed from the previous fragment - used to determine which record to retrieve
        Bundle bundle = this.getArguments();
        String country = bundle.getString("country");
        String location = bundle.getString("location");

        //get data from session
        String uid = pref.getString("uid", null);
        String username = pref.getString("username", null);

        //Back button to previous fragment
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        //retrieve review records - show it to enable user to edit their review easier
        Cursor review = myDb.isRated(country, location, uid);
        if(review.moveToFirst()){
            rbRating.setRating(review.getInt(review.getColumnIndex("rating")));
            etReview.setText(review.getString(review.getColumnIndex("review")));
        }

        //save changes for review
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rating = (int) rbRating.getRating();
                String review = etReview.getText().toString();

                //if any blank field
                if(rating == 0) {
                    Toast.makeText(getActivity(),"Please rate it !", Toast.LENGTH_SHORT).show();
                }
                else if(review.equals("")){
                    Toast.makeText(getActivity(),"Please write your review !", Toast.LENGTH_SHORT).show();
                }
                else {
                    //if update review success, then direct to introFragment
                    //create current datetime but in UTC to conform with the sqlite default UTC format
                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                    Date date = new Date(System.currentTimeMillis());
                    String now = formatter.format(date);
                    Boolean success = myDb.updateReview(country, location, uid, rating, review, now);
                    if(success)
                    {
                        Toast.makeText(getActivity(), "Update Review Successfully ~",Toast.LENGTH_SHORT).show();
                        if (getFragmentManager().getBackStackEntryCount() != 0) {
                            getFragmentManager().popBackStack();
                        }
                        Bundle bundle = new Bundle();
                        bundle.putString("country", country);
                        bundle.putString("location", location);

                        IntroFragment introF = new IntroFragment();
                        introF.setArguments(bundle);
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, introF).commit();
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Update failed !",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Cancel button to previous fragment
        btnCancel.setOnClickListener(new View.OnClickListener() {
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
