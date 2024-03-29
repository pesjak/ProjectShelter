package shelter.project.com.projectshelter.intro;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.github.paolorotolo.appintro.AppIntro;

import shelter.project.com.projectshelter.R;
import shelter.project.com.projectshelter.main_activity.MainActivity;

/**
 * Created by primo on 19. 12. 2017.
 */

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(IntroFragment1.newInstance());
        addSlide(IntroFragment2.newInstance());
        addSlide(IntroFragment3.newInstance());


        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(ContextCompat.getColor(getBaseContext(), R.color.green_dark));
     //   setSeparatorColor(ContextCompat.getColor(getBaseContext(), R.color.orange_dark));


        showSkipButton(true);

/*
        // Hide Skip/Done button.
        setProgressButtonEnabled(false);
*/

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
/*        setVibrate(true);
        setVibrateIntensity(30);*/
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}