package com.example.mediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import com.example.mediaplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mediaPlayer: MediaPlayer
     var handler:Handler? = null
    val httpURL = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-9.mp3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(httpURL)
        mediaPlayer.setOnPreparedListener {
           binding.seekBar.max = mediaPlayer.duration
            mediaPlayer.start()
            handler = Handler(Looper.getMainLooper())
            handler?.postDelayed(runnable,1000)
        }
        mediaPlayer.prepareAsync()




        binding.apply {
            btnPlay.setOnClickListener { mediaPlayer.start() }
            btnPause.setOnClickListener { mediaPlayer.pause() }
            btnStop.setOnClickListener { mediaPlayer.stop() }

            seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    if(p2)
                    mediaPlayer.seekTo(p1)
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {

                }


            })
        }

    }

    val runnable = object :Runnable{
        override fun run() {
            binding.seekBar.progress = mediaPlayer.currentPosition
            handler?.postDelayed(this,1000)
        }
    }



}