package com.example.countdown

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

private const val ARG_FRAGMENT_INDEX = "fragmentIndex"

class CounterFragment : Fragment() {
    private var fragmentIndex: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fragmentIndex = it.getString(ARG_FRAGMENT_INDEX)
        }
    }

    /**
     * sets UI and creates button events
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_counter, container, false)

        val mainActivity = activity as MainActivity
        if (fragmentIndex != null) {
            val i = fragmentIndex.toString()

            root.findViewById<TextView>(R.id.textViewCount).text = CounterService.counts[i]?.count.toString()
            root.findViewById<TextView>(R.id.textViewTitle).text = CounterService.counts[i]?.title.toString()

            root.findViewById<Button>(R.id.buttonDeleteCounter).setOnClickListener {
                val builder = AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert)
                builder.setTitle("Are you sure you want to delete this counter?")

                builder.setPositiveButton("OK") { _, _ ->
                    CounterService.counts.remove(i)
                    CounterService.savePreferences(mainActivity)
                    mainActivity.supportFragmentManager.beginTransaction().remove(this).commit()
                    mainActivity.supportFragmentManager.executePendingTransactions()
                }

                builder.setNegativeButton("Cancel") { _, _ -> }
                builder.create().show()
            }

            root.findViewById<Button>(R.id.buttonChooseTitle).setOnClickListener {
                val builder = AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert)
                builder.setTitle("Set title")

                val editText = EditText(context)
                editText.inputType = InputType.TYPE_CLASS_TEXT
                editText.setText(CounterService.counts[i]?.title)
                builder.setView(editText)

                builder.setPositiveButton("OK") { _, _ ->
                    CounterService.counts[i]?.title = editText.text.toString()
                    root.findViewById<TextView>(R.id.textViewTitle).text = CounterService.counts[i]?.title.toString()
                    CounterService.savePreferences(mainActivity)
                    Toast.makeText(context, "Title saved", Toast.LENGTH_SHORT).show()
                }

                builder.setNegativeButton("Cancel") { _, _ -> }
                builder.create().show()
            }

            root.findViewById<Button>(R.id.buttonDecrement).setOnClickListener {
                CounterService.counts[i]!!.count--;
                root.findViewById<TextView>(R.id.textViewCount).text = CounterService.counts[i]?.count.toString()
                CounterService.savePreferences(mainActivity)
                Toast.makeText(context, "Counter updated", Toast.LENGTH_SHORT).show()
            }

            root.findViewById<Button>(R.id.buttonIncrement).setOnClickListener {
                CounterService.counts[i]!!.count++
                root.findViewById<TextView>(R.id.textViewCount).text = CounterService.counts[i]?.count.toString()
                CounterService.savePreferences(mainActivity)
                Toast.makeText(context, "Counter updated", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }

    companion object {
        /**
         * @param fragmentIngex Parameter 1.
         * @return A new instance of fragment DateFragment.
         */
        @JvmStatic
        fun newInstance(fragmentIndex: String) =
            CounterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_FRAGMENT_INDEX, fragmentIndex)
                }
            }
    }
}
