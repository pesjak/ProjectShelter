package shelter.project.com.projectshelter.listeners;

/**
 * Created by primo on 7. 11. 2017.
 */

public interface ValidationLoginListener {
    void validationSuccessEmail();
    void validationFailedEmail();
    void validationSuccessPassword();
    void validationFailedPassword();
}
