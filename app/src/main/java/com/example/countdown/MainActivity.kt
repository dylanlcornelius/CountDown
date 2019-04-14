package com.example.countdown

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    var dates: MutableMap<String, DateModel> = mutableMapOf()
    var countDowns: Int = 0;
    // var dates: ArrayList<DateModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference = getSharedPreferences(getString(R.string.DATE_PREFERENCE_KEY), Context.MODE_PRIVATE)
        val datePreference = sharedPreference.getString(getString(R.string.DATE_PREFERENCE_KEY), null)

        println(datePreference)
        // sharedPreference.edit().clear().apply()
        if (datePreference != null) {
            // dates = fromJson(datePreference)
            val dateJson = fromJson(datePreference)
            println(dateJson)
            println(dateJson.size)
            // for (date in dates) {
            for (i in 0 until dateJson.size) {
                dates[i.toString()] = dateJson[i]
                createDateFragment(i.toString())
                countDowns++
            }
            println(dates)
        }

        if (supportFragmentManager.findFragmentByTag("0") == null) {
            dates["0"] = DateModel()
            createDateFragment("0")
            countDowns++
        }

        findViewById<Button>(R.id.buttonAddDate).setOnClickListener {
            dates[countDowns.toString()] = DateModel()
            createDateFragment(countDowns.toString())
            countDowns++
        }
    }

    private fun createDateFragment(tag: String) {
        val dateFragment = DateFragment.newInstance(tag)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.my_root, dateFragment, tag)
        transaction.commit()
        supportFragmentManager.executePendingTransactions()
    }

    private fun fromJson(datePreference: String):ArrayList<DateModel> {
        return Gson().fromJson(datePreference, Array<DateModel>::class.java).toCollection(ArrayList())
    }

    private fun toJson(dates: ArrayList<DateModel>): String {
        return Gson().toJson(dates)
    }

    fun savePreferences(dates: MutableMap<String, DateModel>) {
        val dateJson: ArrayList<DateModel> = ArrayList()
        for ((key, value) in dates) {
            dateJson.add(value)
        }

        val sharedPreference = getSharedPreferences(getString(R.string.DATE_PREFERENCE_KEY), Context.MODE_PRIVATE)
        sharedPreference.edit().putString(getString(R.string.DATE_PREFERENCE_KEY), toJson(dateJson)).apply()
    }
}
