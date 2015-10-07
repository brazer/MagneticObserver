package by.org.cgm.magneticobserver.models;

import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import by.org.cgm.magneticobserver.AppCache;
import by.org.cgm.magneticobserver.utils.ColorUtils;
import lombok.Getter;
import lombok.SneakyThrows;

/**
 * Author: Anatol Salanevich
 * Date: 23.09.2015
 */
public class MagMessage implements Serializable {

    public static final String TAG = "MagMessage";

    @Getter private String begin;
    @Getter private String end;
    @Getter private String date;
    private int value;

    @SneakyThrows(ParseException.class)
    public void convertToLocalTime() {
        DateFormat format = new SimpleDateFormat("HH:mm");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        begin = convert(new Time(format.parse(begin).getTime()));
        end = convert(new Time(format.parse(end).getTime()));
    }

    private String convert(Time time) {
        DateFormat mskFormat = new SimpleDateFormat("HH:mm");
        return mskFormat.format(time);
    }

    public String toMessage(String template) {
        return String.format(template, getStormLevel());
    }

    public String getStormLevel() {
        String levels[] = AppCache.getInstance().getLevels();
        switch (value) {
            case 0: return levels[0];
            case 1: return levels[0];
            case 2: return levels[1];
            case 3: return levels[1];
            case 4: return levels[2];
            case 5: return levels[2];
            case 6: return levels[3];
            case 7: return levels[3];
            case 8: return levels[4];
            case 9: return levels[4];
        }
        return "";
    }

    public int getColorId() {
        return ColorUtils.getColorId(value);
    }

}
