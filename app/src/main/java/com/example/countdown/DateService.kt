package com.example.countdown

import android.content.Context
import com.google.gson.Gson

object DateService {
    var dates: MutableMap<String, DateModel> = mutableMapOf()
    var countDowns: Int = 0

    /**
     * loads data from preferences
     */
    fun init(context: Context): MutableMap<String, DateModel> {
        val sharedPreference = context.getSharedPreferences(context.getString(R.string.DATE_PREFERENCE_KEY), Context.MODE_PRIVATE)
        val datePreference = sharedPreference.getString(context.getString(R.string.DATE_PREFERENCE_KEY), null)

        if (datePreference == null) {
            dates["0"] = DateModel()
            countDowns++
            return dates;
        }

        val dateJson = fromJson(datePreference)
        if (dateJson.size == 0) {
            dates["0"] = DateModel()
            countDowns++
            return dates;
        }

        for (i in 0 until dateJson.size) {
            dates[i.toString()] = dateJson[i]
            countDowns++
        }

        return dates;
    }

    private fun fromJson(datePreference: String):ArrayList<DateModel> {
        return Gson().fromJson(datePreference, Array<DateModel>::class.java).toCollection(ArrayList())
    }

    private fun toJson(dates: ArrayList<DateModel>): String {
        return Gson().toJson(dates)
    }

    /**
     * saves data to preferences
     */
    fun savePreferences(context: Context) {
        val dateJson: ArrayList<DateModel> = ArrayList()
        for ((_, value) in dates) {
            dateJson.add(value)
        }

        val sharedPreference = context.getSharedPreferences(context.getString(R.string.DATE_PREFERENCE_KEY), Context.MODE_PRIVATE)
        sharedPreference.edit().putString(context.getString(R.string.DATE_PREFERENCE_KEY), toJson(dateJson)).apply()
    }
}
