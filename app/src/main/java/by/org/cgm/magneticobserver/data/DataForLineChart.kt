package by.org.cgm.magneticobserver.data

import android.graphics.Color
import by.org.cgm.magneticobserver.model.Data
import by.org.cgm.magneticobserver.util.StringUtils
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.util.*

/**
 * Author: Anatol Salanevich
 * Date: 13.11.2015
 */
class AverageXYZ(val data : ArrayList<Data>) {

    private val x = DoubleArray(data.size())
    private val y = DoubleArray(data.size())
    private val z = DoubleArray(data.size())
    var avrX = 0.0
        private set
    var avrY = 0.0
        private set
    var avrZ = 0.0
        private set

    init {
        for (i in data.indices) {
            x[i] = data[i].x
            y[i] = data[i].y
            z[i] = data[i].z
        }
        avrX = getAverage(x)
        avrY = getAverage(y)
        avrZ = getAverage(z)
    }

}

fun getAverage(array: DoubleArray) : Double {
    var sum = 0.0
    for (item in array)
        sum += item
    return sum/array.size()
}

class LineDataHelper {

    private val xVals = ArrayList<String>()
    private val y1Vals = ArrayList<Entry>()
    private val y2Vals = ArrayList<Entry>()
    private val y3Vals = ArrayList<Entry>()
    private val dataSets = ArrayList<LineDataSet>()

    constructor(data: ArrayList<Data>, mX: String) {
        val rmsVals = getRMSVals(data)
        val avr = getAverage(rmsVals)
        for (i in data.indices) {
            xVals.add(
                    data[i].date + " "
                            + StringUtils.toDoubleDigits(data[i].hour) + ":"
                            + StringUtils.toDoubleDigits(data[i].minute)
            )
            y1Vals.add(Entry((rmsVals[i] - avr).toFloat(), i))
        }
        dataSets.add(getDataSet(y1Vals, StringUtils.formatDecimals(mX, avr.toFloat()), Color.RED))
    }

    private fun getRMSVals(data: ArrayList<Data>) : DoubleArray {
        val rmsVals = DoubleArray(data.size())
        for (i in data.indices) {
            rmsVals[i] = (Math.sqrt(data[i].x*data[i].x + data[i].z*data[i].z))
        }
        return rmsVals
    }

    constructor(data: ArrayList<Data>, mX: String, mY: String, mZ: String) {
        val avr = AverageXYZ(data)
        val avrX = avr.avrX
        val avrY = avr.avrY
        val avrZ = avr.avrZ
        for (i in data.indices) {
            xVals.add(
                    data[i].date + " "
                            + StringUtils.toDoubleDigits(data[i].hour) + ":"
                            + StringUtils.toDoubleDigits(data[i].minute)
            )
            y1Vals.add(Entry((data[i].x - avrX).toFloat(), i))
            y2Vals.add(Entry((data[i].y - avrY).toFloat(), i))
            y3Vals.add(Entry((data[i].z - avrZ).toFloat(), i))
        }
        dataSets.add(getDataSet(y1Vals, StringUtils.formatDecimals(mX, avrX.toFloat()), Color.RED))
        dataSets.add(getDataSet(y2Vals, StringUtils.formatDecimals(mY, avrY.toFloat()), Color.BLUE))
        dataSets.add(getDataSet(y3Vals, StringUtils.formatDecimals(mZ, avrZ.toFloat()), Color.GREEN))
    }

    private fun getDataSet(yVals: ArrayList<Entry>, name: String, color: Int) : LineDataSet {
        val set = LineDataSet(yVals, name)
        set.disableDashedLine()
        set.color = color
        set.lineWidth = 1f
        set.setDrawCircles(false)
        set.setDrawCircleHole(false)
        set.setDrawValues(false)
        set.fillAlpha = 65
        set.fillColor = Color.BLACK
        return set
    }

    public fun getLineData() : LineData {
        return LineData(xVals, dataSets)
    }

}