package com.example.jonathan.bakingapp.Utility;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;

import com.example.jonathan.bakingapp.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class mExoPlayer {

    public mExoPlayer(Context context, PlayerView playerView, Uri mp4Uri) {

        // Create track selector
        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        // Create Player
        SimpleExoPlayer exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector);

        // Bind View
        playerView.setPlayer(exoPlayer);

        // Measure Bandwidth
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, context.getString(R.string.app_name)), null);
        // Media Source
        MediaSource videoSrc = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mp4Uri);
        // Prepare Player
        exoPlayer.prepare(videoSrc);
        exoPlayer.setPlayWhenReady(true);


    }
}
