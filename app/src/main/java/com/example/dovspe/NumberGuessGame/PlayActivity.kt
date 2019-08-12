package com.example.dovspe.NumberGuessGame

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_play.*
import java.util.Random

class PlayActivity : Activity() {

    val SUNKUMAS = "sunkumas"
    val ArLaimejo = "ArLaimejo"
    var spejimu_kiekis = 1
    var skaicius = 0 //sugeneruotas skaicius spejimui
    var sunkumas = 0 //sunkumo lygis
    var spejimu_liko = 0

    //WIN SCREEN, DOUMBAZE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        //Gauname sunkumo lygi
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        sunkumas = sharedPreferences.getString(SUNKUMAS, "").toInt()
        if(sunkumas == 4)
            spejimu_liko = 3
        if(sunkumas == 9)
            spejimu_liko = 5
        if(sunkumas == 99)
            spejimu_liko = 10

        textView12.setText("Sunkumas: "+sunkumas)

        //Sugeneruojame skaiciu
        GeneruotiSkaiciu()

        //Logika spejimams
        val btnShow = findViewById<Button>(R.id.button_guess)
        btnShow?.setOnClickListener { Spejimas() }
    }

    fun Spejimas(){
        //Gauname zaidejo ivesta skaiciu
        val spejimas0 = findViewById<EditText>(R.id.spejimo_laukas).text.toString()
        if(spejimas0 != "")
        {
            val spejimas = spejimas0.toInt()

            var sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            var dbHandle = DatabaseHandler(getApplicationContext())
            //dbHandle.createTable() //--------------------------------------------------------------------------------------------

            if(spejimu_liko > 0) {
                if (spejimas > sunkumas || spejimas < 0) {
                    textView10.setText("Skaičius turi būti didesnis už 0 ir mažesnis už " + (sunkumas + 1) + "!")
                } else {
                    //Didiname spejimu kieki
                    spejimu_kiekis = spejimu_kiekis + 1
                    textView11.setText("Dar turite bandymų spėti: " + spejimu_liko)
                    spejimu_liko = spejimu_liko - 1
                    if (spejimas > skaicius) {
                        textView10.setText("Sugeneruotas skaičius yra mažesnis už jūsų spėjimą.")
                    }
                    if (spejimas < skaicius) {
                        textView10.setText("Sugeneruotas skaičius yra didesnis už jūsų spėjimą.")
                    }
                    if (spejimas == skaicius) {
                        editor.putString(ArLaimejo, true.toString())
                        editor.apply()
                        val duom = Duomenys(0, sunkumas, skaicius, spejimu_kiekis, spejimu_liko)
                        dbHandle.add(duom)
                        val intent = Intent(this@PlayActivity, WinLoseActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
            else
            {
                editor.putString(ArLaimejo, false.toString())
                editor.apply()
                val duom = Duomenys(0, sunkumas, skaicius, spejimu_kiekis, spejimu_liko)
                dbHandle.add(duom)
                val intent = Intent(this@PlayActivity, WinLoseActivity::class.java)
                startActivity(intent)
                finish()
            }

            findViewById<EditText>(R.id.spejimo_laukas).setText("")
        }
        else
        {
            textView10.setText("Įveskite spėjimą.")
        }
    }

    fun GeneruotiSkaiciu(){
        skaicius = Random().nextInt(sunkumas+1)
    }
}
