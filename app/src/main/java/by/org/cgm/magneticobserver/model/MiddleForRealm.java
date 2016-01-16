package by.org.cgm.magneticobserver.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: Anatol Salanevich
 * Date: 31.12.2015
 */
@Deprecated
public class MiddleForRealm implements Parcelable {

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

    protected MiddleForRealm(Parcel in) {
        date = in.readString();
        hour = in.readInt();
        minute = in.readInt();
        x = in.readDouble();
        y = in.readDouble();
        z = in.readDouble();
    }

    public static final Creator<MiddleForRealm> CREATOR = new Creator<MiddleForRealm>() {
        @Override
        public MiddleForRealm createFromParcel(Parcel in) {
            return new MiddleForRealm(in);
        }

        @Override
        public MiddleForRealm[] newArray(int size) {
            return new MiddleForRealm[size];
        }
    };

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
        //// TODO: 16.01.2016 add parcel
        Data d = new Data(null);
        d.setDate(m.getDate());
        d.setHour(m.getHour());
        d.setMinute(m.getMinute());
        d.setX(m.getX());
        d.setY(m.getY());
        d.setZ(m.getZ());
        return d;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(date);
        dest.writeInt(hour);
        dest.writeInt(minute);
        dest.writeDouble(x);
        dest.writeDouble(y);
        dest.writeDouble(z);
    }
}
