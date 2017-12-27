package shelter.project.com.projectshelter.helpers;

import android.content.SharedPreferences;

/**
 * Created by Primož on 27/12/2017.
 */

public class SharedPreferencesHelper {
    public static boolean isFirstTime(SharedPreferences sharedPreferences) {
        return sharedPreferences.getBoolean("firstStart", true);
    }

    public static void saveFirstTime(SharedPreferences sharedPreferences, boolean firstTime) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("firstStart", firstTime);
        editor.apply();
    }
}
