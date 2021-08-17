package com.example.t_guide;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.sql.Blob;
import java.util.ArrayList;

public class ReviewFragment extends Fragment {

    private DatabaseHelper myDb;

    private ImageView ivBack;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);

        myDb = new DatabaseHelper(view.getContext());

        ivBack = view.findViewById(R.id.iv_back);
        listView = view.findViewById(R.id.listview);

        //get the data passed from the previous fragment - used to determine which review to display
        Bundle bundle = this.getArguments();
        String country = bundle.getString("country");
        String location = bundle.getString("location");

        //Back button to previous fragment
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        //Create data for testing
        ArrayList<Review> arrayList = new ArrayList<>();

        //arrayList.add(new Review(R.drawable.profile_panda1, "Superman", 4, "15/01/2021", "Funny."));
        //arrayList.add(new Review(R.drawable.profile_panda1, "Iron Man", 2, "16/01/2021", "Too Bad."));
        //arrayList.add(new Review(R.drawable.profile_panda1, "UltraMan", 1, "16/01/2021", "No egg no star."));
        //arrayList.add(new Review(R.drawable.profile_panda1, "Power Ranger", 3, "16/01/2021", "123 color change."));
        //arrayList.add(new Review(R.drawable.profile_panda1, "Pokemon Go 2 Plus", 5, "16/01/2021", "Now you see me and now you don't"));

        //get Review data from sqlite
        Cursor res = myDb.getReviewData(country, location);
        while(res.moveToNext()){
            byte[] img = res.getBlob(res.getColumnIndex("img"));
            int rating = res.getInt(res.getColumnIndex("rating"));
            String review = res.getString(res.getColumnIndex("review"));
            String name = res.getString(res.getColumnIndex("name"));
            String date = res.getString(res.getColumnIndex("latestDate"));
            arrayList.add(new Review(img, name, rating, date, review));
        }

        //Custom adapter - ReviewAdapter
        ReviewAdapter reviewAdapter = new ReviewAdapter(getContext(), R.layout.list_reviews, arrayList);

        listView.setAdapter(reviewAdapter);

        return view;
    }
}
