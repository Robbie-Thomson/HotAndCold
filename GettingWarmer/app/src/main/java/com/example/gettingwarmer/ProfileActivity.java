package com.example.gettingwarmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPreferences = getSharedPreferences(Keys.PREFERENCES, Context.MODE_PRIVATE);

        TextView name = (TextView) findViewById(R.id.activity_profile_tv_name);
        name.setText(getString(R.string.activity_profile_name_default) + " " + sharedPreferences.getString(Keys.STUDENTID, null));
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
}
