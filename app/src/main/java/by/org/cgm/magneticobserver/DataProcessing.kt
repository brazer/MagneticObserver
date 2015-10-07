package by.org.cgm.magneticobserver

import by.org.cgm.magneticobserver.models.Data
import by.org.cgm.magneticobserver.models.Mark
import by.org.cgm.magneticobserver.utils.StringUtils
import java.util.*

/**
 * Author: Anatol Salanevich
 * Date: 06.10.2015
 */
val PERIOD_IN_MINUTES = 1 * 60

class DataProcessing() {

    var maxDifX = 0.0
    var maxDifY = 0.0
    var maxDifZ = 0.0
    var middleVals = ArrayList<Data>()
    var markX = 0
    var markY = 0
    var markZ = 0
    var marks = ArrayList<Mark>()

    fun getMagMarks(): ArrayList<Mark> {
        return marks
    }

    fun calculate() {
        middleVals = AppCache.getInstance().middle
        var data = AppCache.getInstance().data
        var count = 0
        var values = ArrayList<Data>()
        while (count < data!!.size()) {
            var mark = Mark()
            mark.begin = data[count].date + " " + StringUtils.toDoubleDigits(data[count].hour) + "" +
                    ":" + StringUtils.toDoubleDigits(data[count].minute)
            values.clear()
            for (j in 0..PERIOD_IN_MINUTES - 1) {
                if (count == data.size()) return
                values.add(data[count])
                count++
            }
            calculateMarks(values, count - PERIOD_IN_MINUTES)
            mark.end = data[count].date + " " + StringUtils.toDoubleDigits(data[count].hour) + "" +
                    ":" + StringUtils.toDoubleDigits(data[count].minute)
            mark.level = getMaxMark()
            marks.add(mark)
        }
    }

    private fun calculateMarks(values: ArrayList<Data>?, count: Int) {
        var difX : Double;
        var difY : Double;
        var difZ : Double;
        for (i in values!!.indices)
        {
            difX = Math.abs(middleVals[count + i]!!.x - values[i].x);
            if (difX > maxDifX) maxDifX = difX;
            difY = Math.abs(middleVals[count + i]!!.y - values[i].y);
            if (difY > maxDifY) maxDifY = difY;
            difZ = Math.abs(middleVals[count + i]!!.z - values[i].z);
            if (difZ > maxDifZ) maxDifZ = difZ;
        }
        setMarks();
    }

    private fun setMarks() {
        markX = getMark(maxDifX);
        markY = getMark(maxDifY);
        markZ = getMark(maxDifZ);
    }

    private fun getMark(dif: Double) : Int {
        if ((dif >= 0) and (dif < 5)) return 0
        if ((dif >= 5) and (dif < 10)) return 1
        if ((dif >= 10) and (dif < 20)) return 2
        if ((dif >= 20) and (dif < 40)) return 3
        if ((dif >= 40) and (dif < 70)) return 4
        if ((dif >= 70) and (dif < 120)) return 5
        if ((dif >= 120) and (dif < 200)) return 6
        if ((dif >= 200) and (dif < 330)) return 7
        if ((dif >= 330) and (dif < 550)) return 8
        if (dif >= 550) return 9
        return -1
    }

    private fun getMaxMark() : Int {
        return Math.max(markX, Math.max(markY, markZ))
    }

}