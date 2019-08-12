package com.example.dovspe.NumberGuessGame

import android.content.Intent
import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_win_lose.*

class WinLoseActivity : Activity() {
    val ArLaimejo = "ArLaimejo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win_lose)

        val intent = Intent(this@WinLoseActivity, LevelSelectActivity::class.java)

        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val winLose = sharedPreferences.getString(ArLaimejo, "").toBoolean()
        var ring = MediaPlayer.create(applicationContext, R.raw.win_audio)

        if(winLose == false) {
            textViev_WinLose.setText("Atsiprašome, pralaimėjote...")
            imageView4.setImageResource(R.drawable.ic_sad)
            val screen = findViewById(R.id.back) as View
            screen.setBackgroundResource(R.drawable.background_red)
            ring = MediaPlayer.create(applicationContext, R.raw.lose_audio)
        }

        ring.start()

        val btnShow = findViewById<Button>(R.id.button_back)
        btnShow?.setOnClickListener {
            finish()
        }
    }
}
