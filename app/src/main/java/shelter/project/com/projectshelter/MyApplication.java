package shelter.project.com.projectshelter;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import shelter.project.com.projectshelter.helpers.SharedPreferencesHelper;


public class MyApplication extends Application {
    final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .initialData(realm -> {
                    //todo
                })
                .build();
        Realm.setDefaultConfiguration(configuration);

/*
       *//*Realm*//*
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .initialData(realm -> {
                    realm.createObject(EstatesList.class);
                    realm.createObject(CodeRegisterList.class);
                })
                .build();
        //Realm.deleteRealm(configuration);
        Realm.setDefaultConfiguration(configuration);*/

        SharedPreferencesHelper.saveFirstTime(getSharedPreferences(getString(R.string.preference_file), MODE_PRIVATE), true);
    }

}
