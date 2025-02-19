package iut.dam.powerhomme2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 1500; // 1.5 secondes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logo);

        // Chargement de l'animation
        Animation slideUpAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        logo.startAnimation(slideUpAnimation);

        // Transition vers MainActivity après la durée définie
        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish(); // Termine le SplashActivity
        }, SPLASH_DISPLAY_LENGTH);
    }
}
