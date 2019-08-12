package com.example.dovspe.NumberGuessGame

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_about.*

class ActivityAbout : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        button_apie_atgal.setOnClickListener {
            this.finish()
        }
    }
}
