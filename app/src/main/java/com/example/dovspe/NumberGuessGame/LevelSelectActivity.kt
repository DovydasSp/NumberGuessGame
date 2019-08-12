package com.example.dovspe.NumberGuessGame

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_level_select.*

class LevelSelectActivity : Activity() {

    val SUNKUMAS = "sunkumas"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_select)

        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val intent = Intent(this@LevelSelectActivity, PlayActivity::class.java)

        button_easy.setOnClickListener {
            val sunkumas = 4
            editor.putString(SUNKUMAS, sunkumas.toString())
            editor.apply()
            startActivity(intent)
        }
        button_medium.setOnClickListener {
            val sunkumas = 9
            editor.putString(SUNKUMAS, sunkumas.toString())
            editor.apply()
            startActivity(intent)
        }
        button_hard.setOnClickListener {
            val sunkumas = 99
            editor.putString(SUNKUMAS, sunkumas.toString())
            editor.apply()
            startActivity(intent)
        }
        button_stats.setOnClickListener {
            val intent2 = Intent(this@LevelSelectActivity, StatsActivity::class.java)
            startActivity(intent2)
        }
        button_level_atgal.setOnClickListener {
            this.finish()
        }
    }
}
