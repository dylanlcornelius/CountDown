package com.example.countdown

import java.text.SimpleDateFormat
import java.util.*

data class DateModel (
    val startDate: String = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Calendar.getInstance().time),
    val startTime: String = "",
    val endDate: String = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Calendar.getInstance().time),
    val endTime: String = "",
    val title: String = "days left"
)