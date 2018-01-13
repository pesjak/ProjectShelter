package shelter.project.com.projectshelter.data;


import io.realm.RealmObject;

/**
 * Created by primo on 19. 07. 2016.
 */
public class RealmUser extends RealmObject {

    String email;

    public RealmUser() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
