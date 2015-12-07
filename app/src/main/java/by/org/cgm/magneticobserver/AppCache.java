package by.org.cgm.magneticobserver;

import java.util.ArrayList;

import by.org.cgm.magneticobserver.model.Data;
import io.realm.Realm;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: Anatol Salanevich
 * Date: 13.08.2015
 */
public class AppCache {

    private static AppCache sInstance;

    private Realm mRealm;

    @Setter @Getter
    private String[] levels;
    /*@Setter
    private ArrayList<Data> data;*/
    @Setter
    private ArrayList<Data> middle;
    @Setter @Getter
    private String[] shortLevels;

    private AppCache() {
        mRealm = Realm.getInstance(MagneticObserverApplication.getContext());
    }

    public static void initialize() {
        sInstance = new AppCache();
    }

    public static AppCache getInstance() {
        return sInstance;
    }

    public ArrayList<Data> getMiddle() {
        return middle;
    }

    /*public ArrayList<Data> getData() {
        return data;
    }*/

    public void setData2(ArrayList<Data> data) {
        mRealm.beginTransaction();
        mRealm.clear(Data.class);
        mRealm.copyToRealm(data);
        mRealm.commitTransaction();
    }

    public Data[] getData2() {
        return (Data[]) mRealm.allObjects(Data.class).toArray();
    }

    public void finish() {
        mRealm.close();
    }

}
