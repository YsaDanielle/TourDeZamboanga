package com.example.tourdezamboanga;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private boolean isLaunching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        findViewById(R.id.main).setOnClickListener(v -> launchMain(v));
    }

    private void launchMain(View splashRoot) {
        if (isLaunching) {
            return;
        }

        isLaunching = true;
        splashRoot.animate()
                .alpha(0f)
                .setDuration(220)
                .withEndAction(() -> {
                    startActivity(new Intent(this, MainActivity.class));
                    overridePendingTransition(R.anim.fade_slide_in, R.anim.fade_slide_out);
                    finish();
                })
                .start();
    }
}
