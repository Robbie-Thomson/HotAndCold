package com.example.gettingwarmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getApplicationContext().getSharedPreferences(Keys.PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
    }

    public void switchStudent(View view) {
        // Switch to login view in student mode
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(Keys.LOGIN_TYPE, "student");
        startActivity(intent);

    }

    public void switchOpenday(View view) {
        // Switch to login view in open day
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(Keys.LOGIN_TYPE, "open_day");
        startActivity(intent);

    }

    public void switchVisitor(View view) {
        // Switch straight to exploration activity
        editor.putString(Keys.TYPE, "visitor");
        editor.commit();
        Intent intent = new Intent(this, DestinationActivity.class);
        intent.putExtra(Keys.LOGIN_TYPE, "visitor");
        startActivity(intent);
    }
}
