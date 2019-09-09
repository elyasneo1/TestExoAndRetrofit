package com.neo.testexoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.app.Activity
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ads.AdsMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.app.ComponentActivity
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import com.neo.testexoplayer.Samples.*
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.android.exoplayer2.Player


class MainActivity : AppCompatActivity() {
    private lateinit var dataSourceFactory: DefaultDataSourceFactory
    val TAG = "MainActivity"
    private var playerView: PlayerView? = null
    private var player: SimpleExoPlayer? = null
    lateinit var mediaSource: ExtractorMediaSource
    var pos: Long = 0
    var isPlaying: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate() called ")
        playerView = findViewById(R.id.player_view)
        dataSourceFactory = DefaultDataSourceFactory(
            this,
            Util.getUserAgent(this, "appName")
        )
        mediaSource = ExtractorMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MP4_URI)

        button.setOnClickListener {
            //            mediaSource = ExtractorMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(MP4_URI_1)
//            player?.prepare(mediaSource)
            if (isPlaying)
                pausePlayer()
            else
                startPlayer()
        }
        player_view.setOnClickListener {
            if (isPlaying)
                pausePlayer()
            else
                startPlayer()
        }
    }

    private fun pausePlayer() {
        player?.playWhenReady = false
        isPlaying = false
        Log.d(TAG, "playerState: ${player?.playbackState} ")

    }

    private fun startPlayer() {
        player?.playWhenReady = true
        isPlaying = true
        Log.d(TAG, "playerState: ${player?.playbackState} ")
    }

    override fun onStart() {
        super.onStart()
        player = ExoPlayerFactory.newSimpleInstance(this, DefaultTrackSelector())
        playerView?.player = player
        player?.prepare(mediaSource)
        player?.playWhenReady = true
        Log.d(TAG, "playerState: ${player?.playbackState} ")
        player?.seekTo(pos)
        isPlaying = true
    }

    override fun onStop() {
        pos = player?.currentPosition ?: 0
        player?.stop()
        playerView?.player = null
        player?.release()
        player = null
        super.onStop()
    }

    override fun onDestroy() {


        super.onDestroy()
    }

}
