package com.abedafnan.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abedafnan.bakingapp.R;

public class StepDetailsFragment extends Fragment {

    private String mVideoURL;
    private String mThumbnailURL;
    private String mDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_details, container, false);

        Bundle args = getArguments();
        if (args != null) {
            mVideoURL = args.getString("video_url");
            mThumbnailURL = args.getString("thumbnail_url");
            mDescription = args.getString("description");
        }

        Log.d("StepDetailFragment", "Fragment Created");
        Log.d("StepDetailFragment", mVideoURL);
        Log.d("StepDetailFragment", mDescription);
        Log.d("StepDetailFragment", mThumbnailURL);

        return rootView;
    }
}
