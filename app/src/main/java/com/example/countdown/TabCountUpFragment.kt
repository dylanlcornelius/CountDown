package com.example.countdown

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class TabCountUpFragment : Fragment() {
    /**
     * creates counter fragments and button events
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_count_up, container, false)

        val counts = CounterService.init(activity!!)
        for (i in 0 until counts.size) {
            createCounterFragment(i.toString())
        }

        root.findViewById<Button>(R.id.buttonAddCounter).setOnClickListener {
            CounterService.counts[CounterService.countDowns.toString()] = CounterModel()
            createCounterFragment(CounterService.countDowns.toString())
            CounterService.countDowns++
            CounterService.savePreferences(activity!!)
        }

        return root
    }

    /**
     * makes a counter fragment and puts it in the list
     */
    private fun createCounterFragment(tag: String) {
        val counterFragment = CounterFragment.newInstance(tag)
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.add(R.id.counters_list, counterFragment, tag)
        transaction.commit()
    }
}
