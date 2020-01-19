package com.lenovo.smarttraffic.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.activity.VideoActivity;


public class VideoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_video,null);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageView video1 = view.findViewById(R.id.item7_video_1);
        ImageView video2 = view.findViewById(R.id.item7_video_2);
        ImageView video3 = view.findViewById(R.id.item7_video_3);
        ImageView video4 = view.findViewById(R.id.item7_video_4);
        ImageView video5 = view.findViewById(R.id.item7_video_5);

        video1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), VideoActivity.class));
            }
        });
        video2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), VideoActivity.class));
            }
        });
        video3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), VideoActivity.class));
            }
        });
        video4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), VideoActivity.class));
            }
        });
        video5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), VideoActivity.class));
            }
        });

        return view;
    }
}
