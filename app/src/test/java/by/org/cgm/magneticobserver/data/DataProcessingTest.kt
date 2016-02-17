package by.org.cgm.magneticobserver.data

import by.org.cgm.magneticobserver.AppCache
import by.org.cgm.magneticobserver.model.Data
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

/**
 * Author: Anatol Salanevich
 * Date: 15.01.2016
 */
class DataProcessingTest {

    @Before
    fun setUp() {
        val jsonMiddle = readJson("assets/middle.json")
        val jsonData = readJson("assets/data.json")
        var middleVals : ArrayList<Data>
        var data : ArrayList<Data>
        try {
            val objectMapper = ObjectMapper()
            middleVals = objectMapper.readValue<ArrayList<Data>>(jsonMiddle,
                    objectMapper.typeFactory.constructCollectionType(ArrayList::class.java, Data::class.java))
            data = objectMapper.readValue<ArrayList<Data>>(jsonData,
                    objectMapper.typeFactory.constructCollectionType(ArrayList::class.java, Data::class.java))
        } catch (e: IOException) {
            throw e
        }
        AppCache.initialize()
        AppCache.getInstance().middle = middleVals
        AppCache.getInstance().data = data
    }

    fun readJson(file : String) : String {
        val stream : InputStream = this.javaClass.classLoader.getResourceAsStream(file)
        val r = BufferedReader(InputStreamReader(stream))
        val text = r.readText()
        return text
    }

    @Test
    fun testCalculate() {
        assertEquals(364, AppCache.getInstance().middle.size)
        assertEquals(17461.5, AppCache.getInstance().middle[0].x, 0.0)
        assertEquals(420, AppCache.getInstance().data.size)
        assertEquals(2568.4, AppCache.getInstance().data[0].y, 0.0)
        val p = DataProcessing()
        p.calculate()
        assertEquals(2, p.getMagMarks().size)
        assertEquals(3, p.getMagMarks()[0].level)
        assertEquals(2, p.getMagMarks()[1].level)
    }

}
