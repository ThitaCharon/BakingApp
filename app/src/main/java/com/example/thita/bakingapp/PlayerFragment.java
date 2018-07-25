package com.example.thita.bakingapp;

import android.media.session.MediaSession;
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

import java.util.List;

public class PlayerFragment extends Fragment {

    public List<Step> listOfSteps;
    public static String URL = "URL";
    public static String DESCRIPTION = "DESCRIPTION";
    private ExoPlayer mExoPlayer = null;
    private ExoPlayer mExoPlayerView = null;
    private MediaSession mediaSession = null;
    public int positon;
    public int currentWindow;
    private long playbackPosition;


    public PlayerFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player, container, false);

        // TODO get ingredientlist from bundle
        positon = getArguments().getInt(StepsActivity.POSITION);
        listOfSteps = getArguments().getParcelableArrayList(StepsActivity.STEPS_LIST);
        String videoURL = listOfSteps.get(positon).getVideoURL();
        String image = listOfSteps.get(positon).getThumbnailURL();

        SimpleExoPlayer player = ExoPlayerFactory
                .newSimpleInstance(getContext(),new DefaultTrackSelector());
        PlayerView playerView = (SimpleExoPlayerView) rootView.findViewById(R.id.player_thumbnail_pv);
        playerView.setPlayer(mExoPlayer);

//        MediaSource mediaSource = new ExtractorMediaSource(videoURL);

        return rootView;
    }



    //TODO Implement for ExoPlayer





            /*
//        RecyclerView recyclerView = rootView.findViewById(R.id.fragment_steps_rv);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(linearLayoutManager);
        //TODO create IngredientsAdapter
//        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(getActivity(), listOfSteps);
//        recyclerView.setAdapter(ingredientsAdapter);
//        ingredientsAdapter.notifyDataSetChanged();
*/

}
