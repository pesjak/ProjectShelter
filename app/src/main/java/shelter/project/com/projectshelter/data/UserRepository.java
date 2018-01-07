package shelter.project.com.projectshelter.data;

import io.realm.Realm;
import shelter.project.com.projectshelter.retrofit.OnLogoutCompletedListener;

/**
 * Created by primo on 10. 11. 2017.
 */

public class UserRepository implements UserDataSource {

    private static UserRepository INSTANCE = null;

    private Realm realm;

    private UserRepository() {
        this.realm = Realm.getDefaultInstance();
    }

    /**
     * Returns instance of class, or creating it if necessary
     */
    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }
        return INSTANCE;
    }

    /**
     * Destroys Instance if needed
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public void saveNewUser() {
        //TODO
        realm.executeTransaction(realm -> {
            RealmUser user = realm.createObject(RealmUser.class);
        });
    }

    @Override
    public void getUser(GetLoggedInUserCallback callback) {
        RealmUser user = realm.where(RealmUser.class).findFirst();
        if (user != null) {
            callback.onUserLoggedIn(user);
        } else {
            callback.noUsers();
        }
    }

    @Override
    public boolean isUserLoggedIn() {
        return realm.where(RealmUser.class).findFirst() != null;
    }

    @Override
    public void updateUser(UpdateUserCallback callback) {
        //TODO
        realm.executeTransactionAsync(realm -> {
            RealmUser user = realm.where(RealmUser.class).equalTo("id", "id").findFirst();
        }, callback::onSuccess, error -> callback.onFailed(error.getMessage()));  //On Success, OnFailed callback
    }

    @Override
    public void logOutUser(OnLogoutCompletedListener onLogoutCompletedListener) {
        //TODO
        realm.executeTransactionAsync(realm -> {
            RealmUser user = realm.where(RealmUser.class).equalTo("logged_in", true).findFirst();
            /*if (user != null) {
                user.setLoggedIn(false);
            }*/
        }, onLogoutCompletedListener::onLogout);
    }
}
