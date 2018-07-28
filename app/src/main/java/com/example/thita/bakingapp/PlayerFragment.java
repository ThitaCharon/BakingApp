package com.example.thita.bakingapp;

import android.content.Context;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thita.bakingapp.Model.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

public class PlayerFragment extends Fragment {

    public String vedioUrl;
    private ExoPlayer mExoPlayer = null;
    private ExoPlayer mExoImagePlayer = null;
    private MediaSession mediaSession = null;
    private TextView shortDescriptionTV;
    private TextView descriptionTV;
    private long currentPosition = 0;
    private PlayerView playerView;
    public String shortDescription;
    public String description;


    public PlayerFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player, container, false);
        playerView = rootView.findViewById(R.id.player_pv);
        shortDescriptionTV = rootView.findViewById(R.id.short_description_tv);
        descriptionTV = rootView.findViewById(R.id.description_tv);

        Bundle args = getArguments();
        if (args == null){throw new AssertionError();}
        vedioUrl = getArguments().getString(String.valueOf(R.string.KEY_VIDEO_URL));
        shortDescription = getArguments().getString(String.valueOf(R.string.KEY_SHORT_DESCRIPTION));
        description = getArguments().getString(String.valueOf(R.string.KEY_DESCRIPTION));

        if (saveInstanceState != null){
            currentPosition = saveInstanceState.getLong(String.valueOf(R.string.KEY_VIDEO_POSITION));
        }

        shortDescriptionTV.setText(shortDescription);
        descriptionTV.setText(description);
        mExoPlayer = initializePlayer(vedioUrl,playerView);

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);}


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null && currentPosition == 0 ){
            if (savedInstanceState.containsKey(String.valueOf(R.string.KEY_VIDEO_POSITION))){
                currentPosition = savedInstanceState.getLong(String.valueOf(R.string.KEY_VIDEO_POSITION));
            }else{
                currentPosition = 0;
            }
        }
        if (mExoPlayer != null){
            mExoPlayer.seekTo(currentPosition);
        }

    }

    private ExoPlayer initializePlayer(String urlString, PlayerView playerView) {

        ExoPlayer player = null;

        if (urlString != null && !urlString.isEmpty()) {
            player = ExoPlayerFactory.newSimpleInstance(getContext(),
                    new DefaultTrackSelector());
            playerView.setPlayer(player);
            playerView.setControllerShowTimeoutMs(0);
            playerView.setControllerHideOnTouch(false);

            ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(
                    new DefaultDataSourceFactory(getContext(),
                            Util.getUserAgent(getContext(),
                                    "com.example.nebo.bakingapp"))).
                    createMediaSource(Uri.parse(urlString).buildUpon().build());

            player.prepare(mediaSource);
            player.setPlayWhenReady(true);
        }

        return player;
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseImagePlayer();
        releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseImagePlayer();
        releasePlayer();
    }

    public void onPause() {
        super.onPause();
        if (mExoPlayer != null) {
            mExoPlayer.setPlayWhenReady(false);
            currentPosition = mExoPlayer.getCurrentPosition();
        }
        releaseImagePlayer();
        releasePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mExoPlayer == null && vedioUrl!= null ) {
//            mExoPlayer.seekTo(currentPosition);
            initializePlayer(vedioUrl,playerView);
        }

    }

    private void releaseImagePlayer() {
        if (mExoImagePlayer != null) {
            mExoImagePlayer.setPlayWhenReady(false);
            mExoImagePlayer.stop();
            mExoImagePlayer.release();
            mExoImagePlayer = null;
        }
    }

    private void releasePlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.setPlayWhenReady(false);
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }


    @Override
    public void onSaveInstanceState(Bundle currentState){
        super.onSaveInstanceState(currentState);
        currentState.putLong(String.valueOf(R.string.KEY_VIDEO_POSITION),currentPosition);
    }


}
