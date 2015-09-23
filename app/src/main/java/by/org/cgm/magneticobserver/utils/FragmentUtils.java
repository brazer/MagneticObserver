package by.org.cgm.magneticobserver.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Author: Anatol Salanevich
 * Date: 23.09.2015
 */
public class FragmentUtils {

    public static void replaceContent(final FragmentActivity activity,
                                      int content, final Fragment fragment,
                                      final String tag) {
        addFragment(activity, content, fragment, tag, false);
    }

    public static void addFragment(final FragmentActivity activity,
                                   int content, final Fragment fragment,
                                   final String tag, boolean addToBackStack) {
        final FragmentManager manager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(content, fragment, tag);
        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }

}
