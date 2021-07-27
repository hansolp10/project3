package com.example.project3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SheetFragment extends Fragment {
    ImageView img_sheetf;

    int img[] = {R.drawable.song1, R.drawable.song2, R.drawable.song3, R.drawable.song4, R.drawable.song5
            , R.drawable.song6, R.drawable.song7};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_sheet, container, false);

        img_sheetf = fragment.findViewById(R.id.img_sheetf);

        img_sheetf.setImageResource(img[1]);

        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);

        String image = pref.getString("image", "-1");

        if (image.equals("0")) {
            img_sheetf.setImageResource(img[0]);
        } else if (image.equals("1")) {
            img_sheetf.setImageResource(img[1]);
        }else if (image.equals("2")) {
            img_sheetf.setImageResource(img[2]);
        }else if (image.equals("3")) {
            img_sheetf.setImageResource(img[3]);
        }else if (image.equals("4")) {
            img_sheetf.setImageResource(img[4]);
        }else if (image.equals("5")) {
            img_sheetf.setImageResource(img[5]);
        }else if (image.equals("6")) {
            img_sheetf.setImageResource(img[6]);
        }

        return fragment;
    }
}