package gov.nasa.jpl.android.marstime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import gov.nasa.jpl.android.marstime.MarsTime.CURIOSITY_WEST_LONGITUDE
import gov.nasa.jpl.android.marstime.MarsTime.EARTH_SECS_PER_MARS_SEC
import gov.nasa.jpl.android.marstime.MarsTime.canonicalValue24
import gov.nasa.jpl.android.marstime.MarsTime.getMarsTimes
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val EPOCH_FORMAT = "yyyyMMddhh:mm:sszzz"
    private val oppyEpoch: Date = SimpleDateFormat(EPOCH_FORMAT).parse("2004012415:08:59GMT")
    val earthTimeFormat = SimpleDateFormat("yyyy-DDD'T'hh:mm:ss' UTC'")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val today = Date()
        earthText?.text = earthTimeFormat.format(today)

        var timeDiff: Long = today.getTime() / 1000 - oppyEpoch.getTime() / 1000
        timeDiff = (timeDiff / EARTH_SECS_PER_MARS_SEC).toLong()
        var sol = (timeDiff / 86400).toInt()
        timeDiff -= sol * 86400.toLong()
        var hour = (timeDiff / 3600).toInt()
        timeDiff -= hour * 3600.toLong()
        var minute = (timeDiff / 60).toInt()
        var seconds = (timeDiff - minute * 60).toInt()
        sol += 1 //MER convention of landing day sol 1

        oppyText?.text = String.format("Sol %03d %02d:%02d:%02d", sol, hour, minute, seconds)

        val curiosityTime = Date()

        val times = MarsTime.getMarsTimeAndUTC(curiosityTime,
            MarsTime.CURIOSITY_WEST_LONGITUDE, MarsTime.CURIOSITY_FIRST_SOL_OFFSET)
        curioText?.text = times.first

        val percyTime = Date()
        val pTimes = MarsTime.getMarsTimeAndUTC(percyTime,
            MarsTime.PERSEVERANCE_WEST_LONGITUDE, MarsTime.PERSEVERANCE_FIRST_SOL_OFFSET)
        percyText?.text = pTimes.first
    }
}