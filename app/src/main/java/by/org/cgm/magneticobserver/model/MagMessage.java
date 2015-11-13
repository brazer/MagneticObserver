package by.org.cgm.magneticobserver.model;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;

import by.org.cgm.magneticobserver.AppCache;
import by.org.cgm.magneticobserver.util.ColorUtils;
import lombok.Getter;

/**
 * Author: Anatol Salanevich
 * Date: 23.09.2015
 */
public class MagMessage implements Serializable {
// I know this is not really model, but I'm too lazy & the app is too small to correct it.
    public static final String TAG = "MagMessage";

    @Getter private String begin;
    @Getter private String end;
    @Getter private String date;
    @Getter private int value;

    public void convertToLocalTime() {
        begin = convert(begin);
        end = convert(end);
    }

    private String convert(String time) {
        DateTimeFormatter inputFormatter = DateTimeFormat.forPattern("HH:mm").withZoneUTC();
        DateTime parsed = inputFormatter.parseDateTime(time);
        DateTimeFormatter outputFormatter = DateTimeFormat.forPattern("HH:mm")
                .withZone(DateTimeZone.forID("Europe/Minsk"));
        return outputFormatter.print(parsed);
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
