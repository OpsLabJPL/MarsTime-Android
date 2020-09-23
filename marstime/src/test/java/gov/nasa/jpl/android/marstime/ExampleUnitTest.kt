package gov.nasa.jpl.android.marstime

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {
    val parser = SimpleDateFormat("yyyy-DDD HH:mm:ss.SSS", Locale.getDefault())
    val parser2 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    init {
        parser.timeZone = TimeZone.getTimeZone("UTC")
        parser2.timeZone = TimeZone.getTimeZone("UTC")
    }

    @Test
    fun convertPercyLanding() {
        val landing = parser.parse("2021-049 19:49:00.000")
        val times = MarsTime.getMarsTimeAndUTC(
            landing,
            MarsTime.PERSEVERANCE_WEST_LONGITUDE, MarsTime.PERSEVERANCE_FIRST_SOL_OFFSET
        )
        assertEquals(times.first, "Sol 00000M14:59:59")
        assertEquals(parser2.format(times.second), "2021-02-18 19:49:00")
    }

    @Test
    fun convertPercySol0() {
        val sol0 = parser.parse("2021-049 04:24:15.806")
        val times = MarsTime.getMarsTimeAndUTC(
            sol0,
            MarsTime.PERSEVERANCE_WEST_LONGITUDE, MarsTime.PERSEVERANCE_FIRST_SOL_OFFSET
        )
        assertEquals(times.first, "Sol 00000M00:00:00")
        assertEquals(parser2.format(times.second), "2021-02-18 04:24:15")
    }

    @Test
    fun convertPercySol1() {
        val sol1 = parser.parse("2021-050 05:03:51.050")
        val times = MarsTime.getMarsTimeAndUTC(
            sol1,
            MarsTime.PERSEVERANCE_WEST_LONGITUDE, MarsTime.PERSEVERANCE_FIRST_SOL_OFFSET
        )
        assertEquals(times.first, "Sol 00001M00:00:00")
        assertEquals(parser2.format(times.second), "2021-02-19 05:03:51")
    }

    @Test
    fun convertPercySol2() {
        val sol2 = parser.parse("2021-051 05:43:26.294")
        val times = MarsTime.getMarsTimeAndUTC(
            sol2,
            MarsTime.PERSEVERANCE_WEST_LONGITUDE, MarsTime.PERSEVERANCE_FIRST_SOL_OFFSET
        )
        assertEquals(times.first, "Sol 00002M00:00:00")
        assertEquals(parser2.format(times.second), "2021-02-20 05:43:26")
    }

    @Test
    fun convertCurioLanding() {
        val date = parser2.parse("2012-08-06 05:17:57")
        val times = MarsTime.getMarsTimeAndUTC(
            date,
            MarsTime.CURIOSITY_WEST_LONGITUDE,
            MarsTime.CURIOSITY_FIRST_SOL_OFFSET
        )
        assertEquals(times.first, "Sol 00000M20:43:52")
        assertEquals(parser2.format(times.second), "2012-08-06 05:17:57")
    }

}