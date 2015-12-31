package by.org.cgm.magneticobserver.model;

import io.realm.RealmObject;

/**
 * Author: Anatol Salanevich
 * Date: 31.12.2015
 */
public class MiddleForRealm extends RealmObject {

    private String date;
    private int hour;
    private int minute;
    private double x;
    private double y;
    private double z;

    public MiddleForRealm() {}

    public MiddleForRealm(Data data) {
        date = data.getDate();
        hour = data.getHour();
        minute = data.getMinute();
        x = data.getX();
        y = data.getY();
        z = data.getZ();
    }

    public String getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public static MiddleForRealm convert(Data data) {
        return new MiddleForRealm(data);
    }

    public static Data convert(MiddleForRealm m) {
        Data d = new Data();
        d.setDate(m.getDate());
        d.setHour(m.getHour());
        d.setMinute(m.getMinute());
        d.setX(m.getX());
        d.setY(m.getY());
        d.setZ(m.getZ());
        return d;
    }

}
