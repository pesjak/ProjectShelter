package shelter.project.com.projectshelter;

import android.app.Application;

import shelter.project.com.projectshelter.helpers.SharedPreferencesHelper;


public class MyApplication extends Application {
    final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
/*
       *//*Realm*//*
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .initialData(realm -> {
                    realm.createObject(EstatesList.class);
                    realm.createObject(CodeRegisterList.class);
                })
                .build();
        //Realm.deleteRealm(configuration);
        Realm.setDefaultConfiguration(configuration);*/

        SharedPreferencesHelper.saveFirstTime(getSharedPreferences(getString(R.string.preference_file),MODE_PRIVATE),true);
    }

}
