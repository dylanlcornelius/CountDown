package com.example.countdown

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.widget.Button
import android.widget.ScrollView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    var dates: ArrayList<DateModel> = ArrayList()
    //= Array<DateModel>(1){DateModel()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference = getSharedPreferences(getString(R.string.DATE_PREFERENCE_KEY), Context.MODE_PRIVATE)
        val datePreference = sharedPreference.getString(getString(R.string.DATE_PREFERENCE_KEY), null)

        println(datePreference)
        if (datePreference != null) {
            dates = fromJson(datePreference)
            // println(dates)
            // for (date in dates) {
            for (i in 0..dates.size) {
                createDateFragment(i.toString(), dates[i])
            }
        }

        if (supportFragmentManager.findFragmentByTag("0") == null) {
            dates.add(DateModel())
            createDateFragment("0", DateModel())
        }

        findViewById<Button>(R.id.buttonAddDate).setOnClickListener {
            createDateFragment(dates.size.toString(), DateModel())
        }
    }

    private fun createDateFragment(tag: String, dateData: DateModel) {
        // println("tag $tag")
        val dateFragment = DateFragment.newInstance(
            dateData.startDate,
            dateData.startTime,
            dateData.endDate,
            dateData.endTime,
            dateData.title
        )
        // firstDateFragment.arguments = intent.extras;
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.my_root, dateFragment, tag)
        transaction.commit()
        supportFragmentManager.executePendingTransactions()

        // println(supportFragmentManager.findFragmentByTag(tag))
    }

    private fun fromJson(datePreference: String):ArrayList<DateModel> {
        return Gson().fromJson(datePreference, Array<DateModel>::class.java).toCollection(ArrayList())
    }

    private fun toJson(dates: Array<DateModel>): String {
        return Gson().toJson(dates)
    }
}
