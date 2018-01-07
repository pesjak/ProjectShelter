package shelter.project.com.projectshelter.helpers;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import shelter.project.com.projectshelter.listeners.ValidationLoginListener;


/**
 * Created by primo on 7. 11. 2017.
 */

public class ValidationFunctions {

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword(String s) {
        return !s.isEmpty() && s.length() > 5;
    }


    public static boolean validateUserInput(EditText inputEmail, EditText inputPassword, ValidationLoginListener listenerLogin) {
        final boolean validPassword = ValidationFunctions.isValidPassword(inputPassword.getText().toString());
        final boolean validEmail = ValidationFunctions.isValidEmail(inputEmail.getText().toString());

        if (validEmail) {
            listenerLogin.validationSuccessEmail();
        } else {
            listenerLogin.validationFailedEmail();
        }
        if (validPassword) {
            listenerLogin.validationSuccessPassword();
        } else {
            listenerLogin.validationFailedPassword();
        }

        return !(!validPassword || !validEmail);
    }

}
