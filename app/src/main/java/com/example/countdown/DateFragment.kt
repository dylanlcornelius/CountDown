package com.example.countdown

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

private const val ARG_FRAGMENT_INDEX = "fragmentIndex"

class DateFragment : Fragment() {
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
        val root = inflater.inflate(R.layout.fragment_date, container, false)

        val mainActivity = activity as MainActivity
        if (fragmentIndex != null) {
            val i = fragmentIndex.toString()

            root.findViewById<TextView>(R.id.textViewTitle).text = DateService.dates[i]?.title.toString()
            val today = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Calendar.getInstance().time)

            root.findViewById<TextView>(R.id.textViewDate).text = calculateDays(today, DateService.dates[i]?.endDate.toString()).toString()

            val progressBar = root.findViewById<ProgressBar>(R.id.progressBar)
            progressBar.min = 0
            progressBar.max = calculateDays(DateService.dates[i]?.startDate.toString(), DateService.dates[i]?.endDate.toString()).toInt()

            progressBar.progress = calculateDays(DateService.dates[i]?.startDate.toString(), today).toInt()

            root.findViewById<Button>(R.id.buttonDeleteDate).setOnClickListener {
                val builder = AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert)
                builder.setTitle("Are you sure you want to delete this count down?")

                builder.setPositiveButton("OK") { _, _ ->
                    DateService.dates.remove(i)
                    DateService.savePreferences(mainActivity)
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
                editText.setText(DateService.dates[i]?.title)
                builder.setView(editText)

                builder.setPositiveButton("OK") { _, _ ->
                    DateService.dates[i]?.title = editText.text.toString()
                    root.findViewById<TextView>(R.id.textViewTitle).text = DateService.dates[i]?.title.toString()
                    DateService.savePreferences(mainActivity)
                    Toast.makeText(context, "Title saved", Toast.LENGTH_SHORT).show()
                }

                builder.setNegativeButton("Cancel") { _, _ -> }
                builder.create().show()
            }

            root.findViewById<Button>(R.id.buttonChooseDate).setOnClickListener {
                val cal = Calendar.getInstance()
                val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
                val dpd = DatePickerDialog(
                    activity!!,
                    android.R.style.Theme_Material_Dialog_Alert,
                    DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        cal.set(Calendar.YEAR, year)
                        cal.set(Calendar.MONTH, month)
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                        DateService.dates[i]?.startDate = formatter.format(Calendar.getInstance().time)
                        DateService.dates[i]?.endDate = formatter.format(cal.time)

                        root.findViewById<TextView>(R.id.textViewDate).text = calculateDays(DateService.dates[i]?.startDate.toString(), DateService.dates[i]?.endDate.toString()).toString()
                        DateService.savePreferences(mainActivity)
                        Toast.makeText(context, "Date saved", Toast.LENGTH_SHORT).show()
                    },
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                )

                // removing minimum date for now
                // dpd.datePicker.minDate = System.currentTimeMillis()
                val endCal = Calendar.getInstance()
                endCal.time = formatter.parse(DateService.dates[i]?.endDate)
                dpd.datePicker.updateDate(endCal.get(Calendar.YEAR), endCal.get(Calendar.MONTH), endCal.get(Calendar.DAY_OF_MONTH))
                dpd.show()
            }
        }

        return root
    }

    /**
     * calculates days between two dates
     */
    private fun calculateDays(startDate: String, endDate: String): Long {
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        val start = formatter.parse(startDate)
        val end = formatter.parse(endDate)

        return TimeUnit.DAYS.convert(end.time - start.time, TimeUnit.MILLISECONDS)
    }

    companion object {
        /**
         * @param fragmentIngex Parameter 1.
         * @return A new instance of fragment DateFragment.
         */
        @JvmStatic
        fun newInstance(fragmentIndex: String) =
            DateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_FRAGMENT_INDEX, fragmentIndex)
                }
            }
    }
}
