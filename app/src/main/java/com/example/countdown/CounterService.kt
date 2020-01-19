package com.example.countdown

import android.content.Context
import com.google.gson.Gson

object CounterService {
    var counts: MutableMap<String, CounterModel> = mutableMapOf()
    var countDowns: Int = 0

    /**
     * loads data from preferences
     */
    fun init(context: Context): MutableMap<String, CounterModel> {
        val sharedPreference = context.getSharedPreferences(context.getString(R.string.COUNT_PREFERENCE_KEY), Context.MODE_PRIVATE)
        val counterPreference = sharedPreference.getString(context.getString(R.string.COUNT_PREFERENCE_KEY), null)

        if (counterPreference == null) {
            counts["0"] = CounterModel()
            countDowns++
            return counts;
        }

        val counterJson = fromJson(counterPreference)
        if (counterJson.size == 0) {
            counts["0"] = CounterModel()
            countDowns++
            return counts;
        }

        for (i in 0 until counterJson.size) {
            counts[i.toString()] = counterJson[i]
            countDowns++
        }

        return counts;
    }

    private fun fromJson(counterPreference: String):ArrayList<CounterModel> {
        return Gson().fromJson(counterPreference, Array<CounterModel>::class.java).toCollection(ArrayList())
    }

    private fun toJson(counts: ArrayList<CounterModel>): String {
        return Gson().toJson(counts)
    }

    /**
     * saves data to preferences
     */
    fun savePreferences(context: Context) {
        val counterJson: ArrayList<CounterModel> = ArrayList()
        for ((_, value) in counts) {
            counterJson.add(value)
        }

        val sharedPreference = context.getSharedPreferences(context.getString(R.string.COUNT_PREFERENCE_KEY), Context.MODE_PRIVATE)
        sharedPreference.edit().putString(context.getString(R.string.COUNT_PREFERENCE_KEY), toJson(counterJson)).apply()
    }
}
