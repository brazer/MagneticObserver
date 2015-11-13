package by.org.cgm.magneticobserver.data

import by.org.cgm.magneticobserver.model.Mark
import by.org.cgm.magneticobserver.util.ColorUtils
import by.org.cgm.magneticobserver.util.DateTimeUtils
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import java.util.*

/**
 * Author: Anatol Salanevich
 * Date: 13.11.2015
 */
class BarDataHelper(val marks: ArrayList<Mark>, val integerFormatter: ValueFormatter) {

    private val xVals = ArrayList<String>()
    private val yVals = ArrayList<BarEntry>()
    private val dataSets = ArrayList<BarDataSet>()

    init {
        val colors = ArrayList<Int>()
        for (i in marks.indices) {
            val xVal = DateTimeUtils.getDate(marks[i].begin) + "(" +
                    DateTimeUtils.getTime(marks[i].begin) + "-" +
                    DateTimeUtils.getTime(marks[i].end) + ")"
            xVals.add(xVal)
            val yVal = marks[i].level
            yVals.add(BarEntry(yVal.toFloat(), i))
            colors.add(ColorUtils.getColorRgb(marks[i].level))
        }
        val set = BarDataSet(yVals, "Ki")
        set.colors = colors
        set.valueFormatter = integerFormatter
        dataSets.add(set)
    }

    public fun getBarData() : BarData {
        val data = BarData(xVals, dataSets)
        data.setValueTextSize(10f)
        return data
    }

}