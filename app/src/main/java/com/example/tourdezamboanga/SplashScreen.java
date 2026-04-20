package com.example.tourdezamboanga;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable navigateToMain = this::launchMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        findViewById(android.R.id.content).setOnClickListener(v -> launchMain());
        handler.postDelayed(navigateToMain, 1800);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(navigateToMain);
        super.onDestroy();
    }

    private void launchMain() {
        handler.removeCallbacks(navigateToMain);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
