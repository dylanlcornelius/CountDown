package com.example.countdown

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class TabCountDownFragment : Fragment() {
    /**
     * creates date fragments and button events
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_count_down, container, false)

        val dates = DateService.init(activity!!)
        for (i in 0 until dates.size) {
            createDateFragment(i.toString())
        }

        root.findViewById<Button>(R.id.buttonAddDate).setOnClickListener {
            DateService.dates[DateService.countDowns.toString()] = DateModel()
            createDateFragment(DateService.countDowns.toString())
            DateService.countDowns++
            DateService.savePreferences(activity!!)
        }

        return root
    }

    /**
     * makes a date fragment and puts it in the list
     */
    private fun createDateFragment(tag: String) {
        val dateFragment = DateFragment.newInstance(tag)
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.add(R.id.dates_list, dateFragment, tag)
        transaction.commit()
    }
}
