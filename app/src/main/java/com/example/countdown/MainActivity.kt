package com.example.countdown

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.widget.Button
import android.widget.ScrollView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference = getSharedPreferences(getString(R.string.DATE_PREFERENCE_KEY), Context.MODE_PRIVATE)
        val datePreference = sharedPreference.getString(getString(R.string.DATE_PREFERENCE_KEY), null)

        println(datePreference)
        if (datePreference != null) {
            val dates = fromJson(datePreference)
            println(dates)
            // for (date in dates) {
            for (i in 0..dates.size) {
                createDateFragment(i.toString(), dates[i])
            }
        }

        if (supportFragmentManager.findFragmentByTag("0") == null) {
            createDateFragment("0")
        }
    }

    private fun createDateFragment(tag: String, dateData: DateModel = DateModel()) {
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

    private fun fromJson(datePreference: String):Array<DateModel> {
        return Gson().fromJson(datePreference, Array<DateModel>::class.java)
    }
}
