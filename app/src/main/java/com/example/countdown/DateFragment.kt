package com.example.countdown

import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_date.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


private const val ARG_START_DATE = "startDate"
private const val ARG_START_TIME = "startTime"
private const val ARG_END_DATE = "endDate"
private const val ARG_END_TIME = "endTime"
private const val ARG_TITLE = "title"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DateFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DateFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DateFragment : Fragment() {
    private var startDate: String? = ""
    private var startTime: String? = ""
    private var endDate: String? = ""
    private var endTime: String? = ""
    private var title: String? = ""
//    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            startDate = it.getString(ARG_START_DATE)
            startTime = it.getString(ARG_START_TIME)
            endDate = it.getString(ARG_END_DATE)
            endTime = it.getString(ARG_END_TIME)
            title = it.getString(ARG_TITLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_date, container, false)

        val mainActivity = activity as MainActivity
        // println(mainActivity.dates[0])
        root.findViewById<TextView>(R.id.textViewDate).text = calculateDays(startDate.toString(), endDate.toString())
        root.findViewById<TextView>(R.id.textViewTitle).text = title
        root.findViewById<Button>(R.id.buttonChooseDate).setOnClickListener {
            // Toast.makeText(activity, "Sarah", Toast.LENGTH_SHORT).show()
            val cal = Calendar.getInstance()
            val dpd = DatePickerDialog(activity!!, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
                endDate = formatter.format(cal.time)
                root.findViewById<TextView>(R.id.textViewDate).text = calculateDays(startDate.toString(), endDate.toString())
            },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        return root
    }

    private fun calculateDays(startDate: String, endDate: String): String {
        println(startDate)
        println(endDate)
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        val start = formatter.parse(startDate)
        val end = formatter.parse(endDate)

        return TimeUnit.DAYS.convert(end.time - start.time, TimeUnit.MILLISECONDS).toString()
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
//    interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        fun onFragmentInteraction(uri: Uri)
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param startDate Parameter 1.
         * @param startTime Parameter 2.
         * @param endDate Parameter 3.
         * @param endTime Parameter 4.
         * @param title Parameter 5.
         * @return A new instance of fragment DateFragment.
         */
        @JvmStatic
        fun newInstance(startDate: String, startTime: String, endDate: String, endTime: String, title: String) =
            DateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_START_DATE, startDate)
                    putString(ARG_START_TIME, startTime)
                    putString(ARG_END_DATE, endDate)
                    putString(ARG_END_TIME, endTime)
                    putString(ARG_TITLE, title)
                }
            }
    }
}
