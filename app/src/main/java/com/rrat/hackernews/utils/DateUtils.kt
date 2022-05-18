package com.rrat.hackernews.utils


import java.time.Duration
import java.time.Instant

class DateUtils {
    companion object{
            fun elapsedTime(datePost: String): String{

                val currentEpochTime = Instant.now()
                val postEpochTime = Instant.parse(datePost)
                val diff = Duration.between(postEpochTime, currentEpochTime)
                val sec = diff.seconds
                val min = diff.toMinutes()
                val hour = diff.toHours()
                val days = diff.toDays()

                if(sec < 60) return "$sec sec"
                if(min < 60) return "$min min"
                if(hour.toInt() == 1) return  "$hour hr"
                if(hour < 24) return "$hour hrs"
                if(days <= 2) return "yesterday"

            return "$days days"
        }
    }
}