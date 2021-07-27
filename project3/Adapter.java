package com.example.project3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

public class Adapter extends PagerAdapter {

    private int[] images = {R.drawable.banner01,R.drawable.banner02,R.drawable.banner03};
    private LayoutInflater inflater;
    private Context context;

    public Adapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public  Object instantiateItem(ViewGroup container, int position){
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.slider, container, false);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageview);
        imageView.setImageResource(images[position]);
        container.addView(v);


        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.invalidate();
    }
}
