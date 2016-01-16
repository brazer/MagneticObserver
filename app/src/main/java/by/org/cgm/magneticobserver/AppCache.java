package by.org.cgm.magneticobserver;

import java.util.ArrayList;

import by.org.cgm.magneticobserver.model.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: Anatol Salanevich
 * Date: 13.08.2015
 */
public class AppCache {

    private static AppCache sInstance;

    @Setter @Getter
    private String[] levels;
    @Setter @Getter
    private String[] shortLevels;
    @Setter
    private ArrayList<Data> data;
    @Setter
    private ArrayList<Data> middle;
    @Setter @Getter
    private boolean neededToUpdate = true;

    public static void initialize() {
        sInstance = new AppCache();
    }

    public static AppCache getInstance() {
        return sInstance;
    }

    public ArrayList<Data> getMiddle() {
        return middle;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public boolean isDataEmpty() {
        return data == null || middle == null;
    }

}
