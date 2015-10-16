package by.org.cgm.magneticobserver.models;

import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import by.org.cgm.magneticobserver.AppCache;
import by.org.cgm.magneticobserver.R;
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
    @Getter private int value;

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
        switch (value) {
            case 0: return R.color.color_quiet;
            case 1: return R.color.color_quiet;
            case 2: return R.color.color_low_storm;
            case 3: return R.color.color_low_storm;
            case 4: return R.color.color_moderate_storm;
            case 5: return R.color.color_moderate_storm;
            case 6: return R.color.color_high_storm;
            case 7: return R.color.color_high_storm;
            case 8: return R.color.color_very_high_storm;
            case 9: return R.color.color_very_high_storm;
        }
        return R.color.white;
    }

}
