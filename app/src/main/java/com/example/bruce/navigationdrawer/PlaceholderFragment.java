package com.example.bruce.navigationdrawer;

/**
 * Created by N1007 on 2015/1/16.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        //实例化fragment的时候将选择的item位置传递过去
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        for(int i=0;i<10;i++){
            GifImageView gifImageView = new GifImageView(getActivity());
            GifDrawable gd = null;
            try {
                gd = new GifDrawable(getActivity().getAssets(), "1.gif");
            } catch (IOException e) {
                e.printStackTrace();
            }
            gifImageView.setImageDrawable(gd);
            list.add(gifImageView);
        }


        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        viewPager.setAdapter(new SimpleAdapter());
    /*    GifImageView gifImageView = (GifImageView) rootView.findViewById(R.id.giv);
        GifDrawable gd = null;
        try {
            gd = new GifDrawable(getActivity().getAssets(), "1.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }
        gifImageView.setImageDrawable(gd);*/

        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //fragment attach到activity的时候就获取点击的item位置
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }


    private List<GifImageView> list = new ArrayList<>();


    class SimpleAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView(list.get(position));
        }
    }
}