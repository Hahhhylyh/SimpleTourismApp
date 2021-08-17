package com.example.t_guide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    private DatabaseHelper myDb;
    SharedPreferences pref;

    private CircleImageView img;
    private TextView tvName, tvDate, tvLogout;
    private Switch bgm;

    private static final int PICK_IMAGE = 100;

    String uid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        myDb = new DatabaseHelper(view.getContext());
        pref = this.getActivity().getSharedPreferences("user_details", 0);

        img = view.findViewById(R.id.profile_image);
        tvName = view.findViewById(R.id.tv_username);
        tvDate = view.findViewById(R.id.tv_registeredDate);
        tvLogout = view.findViewById(R.id.tv_logout);

        //get data from session
        uid = pref.getString("uid", null);
        String username = pref.getString("username", null);
        String registerDate = "2021-01-15";

        //get User data based on user id store in session
        Cursor res = myDb.getUserData(uid);
        if(res.moveToNext()){
            registerDate = res.getString(res.getColumnIndex("registeredDate"));
        }

        tvName.setText(username.toUpperCase());
        tvDate.setText("Since " + registerDate);

        //change profile picture with implicit intent (gallery)
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://media/internal/images/media"));
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        Bitmap picture = myDb.getImage(uid);
        if (picture == null)
            img.setImageResource(R.drawable.profile_panda1);
        else
            img.setImageBitmap(picture);

        //switch button to control bgm
        bgm = view.findViewById(R.id.s_bgm);
        bgm.setChecked(false);
        bgm.setTextOn("ON");
        bgm.setTextOff("OFF");
        bgm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getActivity().startService(new Intent(getActivity(), BgmService.class));
                } else {
                    getActivity().stopService(new Intent(getActivity(), BgmService.class));
                }
            }
        });
        
        //Logout will clear session and direct to login page
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

    //Get location of images
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == getActivity().RESULT_OK && requestCode == PICK_IMAGE) {
            Uri uri = data.getData();
            String x = getPath(uri);

            if(myDb.updateImage(x, Integer.parseInt(uid))) {
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
            }
            else {
                Toast.makeText(getActivity(), "Please enable the storage permission in your app setting.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getPath(Uri uri) {
        if(uri == null) return null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        if(cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }


}