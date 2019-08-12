package com.example.dovspe.NumberGuessGame

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.content.Context
import android.view.LayoutInflater
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_stats.*

class StatsActivity : Activity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        button_stats_atgal.setOnClickListener {
            this.finish()
        }

        val listView = findViewById<ListView>(R.id.stats_listview)
        listView.adapter = MyCustomAdapter(this)
    }

    private class MyCustomAdapter(context: Context): BaseAdapter(){
        private val mContext: Context = context

        var dbHandle = DatabaseHandler(mContext)
        val duom = dbHandle.getAll()

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val row = layoutInflater.inflate(R.layout.stats_row, viewGroup, false)

            if (position != 0){
            val nrTextView = row.findViewById<TextView>(R.id.textView_stats_id)
            nrTextView.text = duom[position - 1].id.toString()

            val sunkumasTextView = row.findViewById<TextView>(R.id.textView_stats_sunkumas)
            sunkumasTextView.text = duom[position - 1].sunkumas.toString()

            val skTextView = row.findViewById<TextView>(R.id.textView_stats_sk)
            skTextView.text = duom[position - 1].sugeneruotas_sk.toString()

            val spejimuTextView = row.findViewById<TextView>(R.id.textView_stats_spejimu)
            spejimuTextView.text = duom[position - 1].spejimu.toString()

            val likoSpejimuTextView = row.findViewById<TextView>(R.id.textView_stats_likoSpejimu)
            likoSpejimuTextView.text = duom[position - 1].liko_spejimu.toString()
            }

            return row
            /*val textView = TextView(mContext)
            textView.text = "test"
            return textView*/
        }

        override fun getCount(): Int {
            return duom.size+1
        }

        override fun getItem(position: Int): Any {
            return "TEST"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }
    }
}
