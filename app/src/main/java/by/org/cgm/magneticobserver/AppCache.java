package by.org.cgm.magneticobserver;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import by.org.cgm.magneticobserver.model.Data;
import by.org.cgm.magneticobserver.model.MiddleForRealm;
import io.realm.Realm;
import io.realm.RealmResults;
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
    @Setter @Getter
    private String[] shortLevels;
    @Setter @Getter
    private boolean neededToUpdate = true;

    private AppCache(Context context) {
        mRealm = Realm.getInstance(context); // without migration
    }

    public static void initialize(Context context) {
        sInstance = new AppCache(context);
    }

    public static AppCache getInstance() {
        return sInstance;
    }

    public void writeMiddle(ArrayList<Data> middle) {
        ArrayList<MiddleForRealm> castMiddle = new ArrayList<>();
        for (Data m : middle) {
            castMiddle.add(MiddleForRealm.convert(m));
        }
        mRealm.beginTransaction();
        mRealm.clear(MiddleForRealm.class);
        mRealm.copyToRealm(castMiddle);
        mRealm.commitTransaction();
    }

    public List<Data> readMiddle() {
        RealmResults<MiddleForRealm> middle = mRealm.allObjects(MiddleForRealm.class);
        if (middle.isEmpty()) {
            return null;
        }
        List<Data> data = new ArrayList<>();
        for (MiddleForRealm m : middle) {
            data.add(MiddleForRealm.convert(m));
        }
        return data;
    }

    public void writeData(ArrayList<Data> data) {
        mRealm.beginTransaction();
        mRealm.clear(Data.class);
        mRealm.copyToRealm(data);
        mRealm.commitTransaction();
    }

    public List<Data> readData() {
        RealmResults<Data> data = mRealm.allObjects(Data.class);
        if (data.isEmpty()) {
            return null;
        }
        return data.subList(0, data.size());
    }

    public boolean isDataEmpty() {
        return mRealm.allObjects(Data.class).isEmpty() || mRealm.allObjects(MiddleForRealm.class).isEmpty();
    }

    public void finish() {
        mRealm.close();
    }

}
