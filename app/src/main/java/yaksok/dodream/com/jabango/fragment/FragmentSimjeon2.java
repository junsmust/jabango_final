package yaksok.dodream.com.jabango.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.FileOutputStream;

import yaksok.dodream.com.jabango.R;


public class FragmentSimjeon2 extends Fragment {


    public FragmentSimjeon2() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentView view = inflater.inflate(R.layout.fragment_fragment_simjeon2,container,false);
               View view = inflater.inflate(R.layout.fragment_fragment_simjeon2,container,false);
               ImageView imageView = view.findViewById(R.id.room_img2);
               return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
