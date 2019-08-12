package com.example.dovspe.NumberGuessGame

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_linear.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_linear)

        button_zaisti.setOnClickListener {
            val intent = Intent(this@MainActivity, LevelSelectActivity::class.java)
            startActivity(intent)
        }

        button_apie.setOnClickListener{
            val intent = Intent(this@MainActivity, ActivityAbout::class.java)
            startActivity(intent)
        }

        button_nustatymai.setOnClickListener{
            val intent2 = Intent(this@MainActivity, SettingActivity::class.java)
            startActivity(intent2)
        }
    }
}
