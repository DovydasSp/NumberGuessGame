package com.example.dovspe.NumberGuessGame

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : Activity() {

    val VARDAS = "vardas"
    val AMZIUS = "amzius"
    val GARSAS = "garsas"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val vardas = sharedPreferences.getString(VARDAS, "")
        val amzius = sharedPreferences.getString(AMZIUS, "")
        val garsas = sharedPreferences.getBoolean(GARSAS, false)

        findViewById<EditText>(R.id.vardo_lauko_duom).setText(vardas)
        findViewById<EditText>(R.id.amziaus_lauko_duom).setText(amzius)
        findViewById<Switch>(R.id.garsu_switch_duom).setChecked(garsas)

        button_nustatymai_atgal.setOnClickListener {
            this.finish()
        }

        val btnShow = findViewById<Button>(R.id.issaugoti_nustatymus_mygtukas)
        btnShow?.setOnClickListener { IssaugotiNustatymus(sharedPreferences) }
    }

    fun IssaugotiNustatymus(sharedPreferences : SharedPreferences)
    {
        val amzius = findViewById<EditText>(R.id.amziaus_lauko_duom)
        val vardas = findViewById<EditText>(R.id.vardo_lauko_duom)
        val garsas = findViewById<Switch>(R.id.garsu_switch_duom)

        val editor = sharedPreferences.edit()
        editor.putString(VARDAS, vardas.text.toString())
        editor.putString(AMZIUS, amzius.text.toString())
        editor.putBoolean(GARSAS, garsas.isChecked)
        editor.apply()
        finish()
    }
}
