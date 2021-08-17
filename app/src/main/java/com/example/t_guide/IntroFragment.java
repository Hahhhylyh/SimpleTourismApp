package com.example.t_guide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;

public class IntroFragment extends Fragment {

    private DatabaseHelper myDb;
    SharedPreferences pref;

    private CircleImageView ratedImg;
    private LinearLayout firstTimeRate, rated;
    private Button btnEdit, btnSubmit;
    private ImageView ivBack, ivDelete;
    private TextView ratedName, ratedReview, ratedDate, tvSeeReview, tvTitle, tvSummary, tvTs1, tvTs2, tvTs3, tvTs4, tvT1, tvT2, tvAvg, tvNum;
    private RatingBar rbRating, ratedRating, rbAvg;
    private EditText etReview;
    private ProgressBar pb1, pb2, pb3, pb4, pb5;
    private android.widget.VideoView VideoView;
    private android.net.Uri Uri;
    private android.widget.MediaController MediaController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro, container, false);

        myDb = new DatabaseHelper(view.getContext());
        pref = this.getActivity().getSharedPreferences("user_details", 0);

        firstTimeRate = view.findViewById(R.id.firstTimeRate);
        rbRating = view.findViewById(R.id.rb_rating);
        etReview = view.findViewById(R.id.et_review);
        btnSubmit = view.findViewById(R.id.btn_submit);

        rated = view.findViewById(R.id.Rated);
        ratedImg = view.findViewById(R.id.profile_image);
        ratedName= view.findViewById(R.id.rated_name);
        ratedReview = view.findViewById(R.id.rated_review);
        ratedRating = view.findViewById(R.id.rated_rating);
        ratedDate = view.findViewById(R.id.rated_date);
        tvSeeReview = view.findViewById(R.id.tv_seeReview);
        ivDelete = view.findViewById(R.id.iv_delete);

        tvAvg = view.findViewById(R.id.tv_avg);
        rbAvg = view.findViewById(R.id.rb_avg);
        tvNum = view.findViewById(R.id.tv_num);
        pb1 = view.findViewById(R.id.pb_1);
        pb2 = view.findViewById(R.id.pb_2);
        pb3 = view.findViewById(R.id.pb_3);
        pb4 = view.findViewById(R.id.pb_4);
        pb5 = view.findViewById(R.id.pb_5);

        btnEdit = view.findViewById(R.id.btn_edit);
        ivBack = view.findViewById(R.id.iv_back);
        tvTitle = view.findViewById(R.id.toolbar_title);
        tvSummary = view.findViewById(R.id.tv_summary);
        tvTs1 = view.findViewById(R.id.tv_ts1);
        tvTs2 = view.findViewById(R.id.tv_ts2);
        tvTs3 = view.findViewById(R.id.tv_ts3);
        tvTs4 = view.findViewById(R.id.tv_ts4);
        tvT1 = view.findViewById(R.id.tv_t1);
        tvT2 = view.findViewById(R.id.tv_t2);
        VideoView videoView = view.findViewById(R.id.video_view);

        //get the data passed from the previous fragment - used to determine which location id to retrieve
        Bundle bundle = this.getArguments();
        String country = bundle.getString("country");
        String location = bundle.getString("location");

        //get data from session
        String uid = pref.getString("uid", null);
        String username = pref.getString("username", null);

        //set visibility for the first time review or edit review
        //if user already rated the location, then show their review
        //else if user not yet rate the location, then allow user to rate
        Cursor review = myDb.isRated(country, location, uid);
        if(review.getCount() > 0) {
            firstTimeRate.setVisibility(View.GONE);
            ratedName.setText(username);
            if(review.moveToFirst()){
                ratedRating.setRating(review.getInt(review.getColumnIndex("rating")));
                ratedDate.setText(review.getString(review.getColumnIndex("latestDate")));
                ratedReview.setText(review.getString(review.getColumnIndex("review")));
            }
            Bitmap picture = myDb.getImage(uid);
            if (picture == null)
                ratedImg.setImageResource(R.drawable.profile_panda1);
            else
                ratedImg.setImageBitmap(picture);
        }
        else {
            rated.setVisibility(View.GONE);
        }

        //Show Rating Summary
        Cursor summary = myDb.getRatingSummary(country, location);
        if(summary.getCount() > 0) {
            int[] star = {0, 0, 0, 0, 0};
            while(summary.moveToNext()) {
                int rating = summary.getInt(0);
                int count = summary.getInt(1);
                switch(rating)
                {
                    case 1:
                        star[0] = count;
                        break;

                    case 2:
                        star[1] = count;
                        break;

                    case 3:
                        star[2] = count;
                        break;

                    case 4:
                        star[3] = count;
                        break;

                    case 5:
                        star[4] = count;
                        break;
                }
            }
            //Calculate the total reviews
            int totalReviews = 0;
            for (int value : star)
                totalReviews += value;

            //Calculate average rating
            float avg = 0.0f;
            for (int i = 0; i < star.length; ++i) {
                avg += (i + 1) * star[i];
            }
            avg /= totalReviews;

            //Set the rating accordingly
            tvAvg.setText(String.format("%.1f", avg));
            rbAvg.setRating(avg);
            tvNum.setText(totalReviews + " reviews");
            pb1.setMax(totalReviews);
            pb1.setProgress(star[0]);
            pb2.setMax(totalReviews);
            pb2.setProgress(star[1]);
            pb3.setMax(totalReviews);
            pb3.setProgress(star[2]);
            pb4.setMax(totalReviews);
            pb4.setProgress(star[3]);
            pb5.setMax(totalReviews);
            pb5.setProgress(star[4]);
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
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
                    //if add review success, then reload introFragment
                    Boolean success = myDb.addReview(rating, review, country, location, Integer.parseInt(uid));
                    if(success)
                    {
                        Toast.makeText(getActivity(), "Review Submitted ~",Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putString("country", country);
                        bundle.putString("location", location);

                        IntroFragment introF = new IntroFragment();
                        introF.setArguments(bundle);
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, introF).commit();
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Invalid Review, failed !",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean deleted = myDb.deleteReview(country, location, uid);
                if(deleted)
                {
                    Toast.makeText(getActivity(), "Review has been deleted ~",Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("country", country);
                    bundle.putString("location", location);

                    IntroFragment introF = new IntroFragment();
                    introF.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, introF).commit();
                }
                else
                {
                    Toast.makeText(getActivity(), "Delete failed !",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //get Location data
        Cursor res = myDb.getLocationData(country, location);
        if(res.moveToFirst()){
            tvTitle.setText(res.getString(3));
            tvSummary.setText(res.getString(4));
            tvTs1.setText(res.getString(5));
            tvTs2.setText(res.getString(6));
            tvTs3.setText(res.getString(7));
            tvTs4.setText(res.getString(8));
            tvT1.setText("\u2022 " + res.getString(9));
            tvT2.setText("\u2022 " + res.getString(10));
        }

        //To save disk space, I used the same video for all locations... (Its a video about cinematic aerial of Genting Highland)
        String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.genting;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(getContext());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        //Back button to previous fragment
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        //Button to Review fragment
        tvSeeReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("country", country);
                bundle.putString("location", location);

                ReviewFragment reF = new ReviewFragment();
                reF.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, reF).addToBackStack(null).commit();
            }
        });

        //Edit button to update review fragment
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("country", country);
                bundle.putString("location", location);

                UpdateReviewFragment upF = new UpdateReviewFragment();
                upF.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, upF).addToBackStack(null).commit();
            }
        });

        return view;
    }
}
