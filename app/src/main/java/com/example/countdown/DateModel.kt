package com.example.countdown

import java.text.SimpleDateFormat
import java.util.*

data class DateModel (
    var startDate: String = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Calendar.getInstance().time),
    var startTime: String = "",
    var endDate: String = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Calendar.getInstance().time),
    var endTime: String = "",
    var title: String = ""
)
