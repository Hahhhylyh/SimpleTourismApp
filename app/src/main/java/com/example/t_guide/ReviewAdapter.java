package com.example.t_guide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewAdapter extends ArrayAdapter<Review> {
    private Context mContext;
    private int mResource;

    public ReviewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Review> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        CircleImageView circleImageView = convertView.findViewById(R.id.list_img);
        TextView tvName = convertView.findViewById(R.id.list_name);
        RatingBar rbRating = convertView.findViewById(R.id.list_rating);
        TextView tvDate = convertView.findViewById(R.id.list_date);
        TextView tvReview = convertView.findViewById(R.id.list_review);

        Bitmap picture = null;
        //byte[] img = cursor.getBlob(cursor.getColumnIndex("img"));
        byte[] img = getItem(position).getImg();
        if (img != null)
            picture = BitmapFactory.decodeByteArray(img, 0, img.length);

        //use default profile picture if not change yet
        if (picture == null)
            circleImageView.setImageResource(R.drawable.profile_panda1);
        else
            circleImageView.setImageBitmap(picture);

        tvName.setText(getItem(position).getName());
        rbRating.setRating(getItem(position).getRating());
        tvDate.setText(getItem(position).getDate());
        tvReview.setText(getItem(position).getReview());

        return convertView;
    }
}
