package com.example.thita.bakingapp;

import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thita.bakingapp.Model.Step;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.net.URL;
import java.util.List;

public class PlayerFragment extends Fragment {

    public List<Step> listOfSteps;

    public static String VEDIO_URL = "URL";
    public String vedioUrl;
    private ExoPlayer mExoPlayer = null;
    private ExoPlayer mExoPlayerView = null;
    private MediaSession mediaSession = null;
    public int currentWindow;
    private long playbackPosition;


    public PlayerFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player, container, false);

        vedioUrl = getArguments().getString(VEDIO_URL);

        PlayerView playerView = rootView.findViewById(R.id.player_thumbnail_pv);

        mExoPlayer = initializePlayer(vedioUrl,playerView);

        return rootView;
    }


    private ExoPlayer initializePlayer(String urlString, PlayerView playerView) {
        ExoPlayer player = null;

        if (urlString != null && !urlString.isEmpty()) {
            player = ExoPlayerFactory.newSimpleInstance(getContext(),
                    new DefaultTrackSelector());
            playerView.setPlayer(player);
            // player.addListener(this);
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


}
