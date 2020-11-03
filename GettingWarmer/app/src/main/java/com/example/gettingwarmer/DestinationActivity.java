package com.example.gettingwarmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class DestinationActivity extends AppCompatActivity {

    private String loginType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        Intent intent = getIntent();
        loginType = intent.getStringExtra(Keys.LOGIN_TYPE);

        Button home = (Button) findViewById(R.id.activity_destination_btn_home);

        if (loginType.equalsIgnoreCase("student")) {
            home.setText(getString(R.string.activity_destination_profile));
        }
    }

    public void switchHomeHandler(View view) {
        if (loginType.equalsIgnoreCase("student")) {
            switchProfile(view);
        } else {
            switchHome(view);
        }
    }

    public void switchHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void switchQR(View view) {
        Intent intent = new Intent(this, QRActivity.class);
        startActivity(intent);
    }

    public void switchSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void switchProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void switchBoyd(View view) {
        Intent intent = new Intent(this, ExploreActivity.class);
        intent.putExtra(Keys.BUILDING, getString(R.string.activity_destination_btn1));
        startActivity(intent);
    }

    public void switchFraser(View view) {
        Intent intent = new Intent(this, ExploreActivity.class);
        intent.putExtra(Keys.BUILDING, getString(R.string.activity_destination_btn2));
        startActivity(intent);
    }

    public void switchLibrary(View view) {
        Intent intent = new Intent(this, ExploreActivity.class);
        intent.putExtra(Keys.BUILDING, getString(R.string.activity_destination_btn3));
        startActivity(intent);
    }

    public void switchMain(View view) {
        Intent intent = new Intent(this, ExploreActivity.class);
        intent.putExtra(Keys.BUILDING, getString(R.string.activity_destination_btn4));
        startActivity(intent);
    }

    public void switchQMU(View view) {
        Intent intent = new Intent(this, ExploreActivity.class);
        intent.putExtra(Keys.BUILDING, getString(R.string.activity_destination_btn6));
        startActivity(intent);
    }

    public void switchGUU(View view) {
        Intent intent = new Intent(this, ExploreActivity.class);
        intent.putExtra(Keys.BUILDING, getString(R.string.activity_destination_btn5));
        startActivity(intent);
    }
}
