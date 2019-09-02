package com.abedafnan.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.abedafnan.bakingapp.R;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepDetailsFragment extends Fragment {

    private String mVideoURL;
    private String mThumbnailURL;
    private String mDescription;

    @BindView(R.id.tv_instructions)
    TextView instructionsTextView;
    @BindView(R.id.thumbnailView)
    ImageView thumbnailImageView;
    @BindView(R.id.playerView)
    SimpleExoPlayerView simpleExoPlayerView;
    @BindView(R.id.media_frame)
    FrameLayout mediaFrameLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_details, container, false);
        ButterKnife.bind(this, rootView);

        Bundle args = getArguments();
        if (args != null) {
            mVideoURL = args.getString("video_url");
            mThumbnailURL = args.getString("thumbnail_url");
            mDescription = args.getString("description");
        }

        Log.d("StepDetailFragment", "Fragment Created");
        Log.d("StepDetailFragment", "VIDEO: " + mVideoURL + "");
        Log.d("StepDetailFragment", "IMAGE: " + mDescription + "");
        Log.d("StepDetailFragment", "DESC: " + mThumbnailURL + "");

        // Start the video if available
        if (TextUtils.isEmpty(mVideoURL)) {
            simpleExoPlayerView.setVisibility(View.GONE);
        } else {
            // TODO: Implement ExoPlayer and initialize it
        }

        // Set the step thumbnail image if available
        if (TextUtils.isEmpty(mThumbnailURL)) {
            if (TextUtils.isEmpty(mVideoURL)) {
                mediaFrameLayout.setVisibility(View.GONE);
            }
        } else {
            Picasso.get()
                    .load(mThumbnailURL)
                    .placeholder(R.drawable.placeholder)
                    .into(thumbnailImageView);
        }

        // Set the step instructions if available
        if (TextUtils.isEmpty(mDescription)) {
            instructionsTextView.setText(getString(R.string.msg_no_instructions));
        } else {
            instructionsTextView.setText(mDescription);
        }

        return rootView;
    }
}
