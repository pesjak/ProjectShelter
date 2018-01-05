package shelter.project.com.projectshelter.data;


import io.realm.RealmObject;

/**
 * Created by primo on 19. 07. 2016.
 */
public class RealmUser extends RealmObject {

    int something;

    public RealmUser() {
    }

    public int getSomething() {
        return something;
    }

    public void setSomething(int something) {
        this.something = something;
    }
}
