package shelter.project.com.projectshelter.data;


import shelter.project.com.projectshelter.retrofit.OnLogoutCompletedListener;

/**
 * Created by primo on 10. 11. 2017.
 */

public interface UserDataSource {

    void logOutUser(OnLogoutCompletedListener onLogoutCompletedListener);

    interface UpdateUserCallback {
        void onSuccess();

        void onFailed(String message);
    }

    interface GetLoggedInUserCallback {
        void onUserLoggedIn(RealmUser user);

        void noUsers();
    }

    void saveNewUser(); //TODO

    void getUser(GetLoggedInUserCallback callback);

    void updateUser(UpdateUserCallback callback); //TODO


}
