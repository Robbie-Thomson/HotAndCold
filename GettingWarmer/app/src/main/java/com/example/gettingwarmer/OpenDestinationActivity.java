package com.example.gettingwarmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class OpenDestinationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_destination);
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

    public void switchExplore(View view) {
        String message;
        RadioGroup rg = (RadioGroup) findViewById(R.id.activity_open_destination_radiogroup);
        int id = rg.getCheckedRadioButtonId();

        if (id == -1) {
            return;
        } else if (id == findViewById(R.id.activity_open_destination_rb_1).getId()) {
            message = "Boyd Orr";
        } else if (id == findViewById(R.id.activity_open_destination_rb_2).getId()) {
            message = "Boyd Orr";
        } else if (id == findViewById(R.id.activity_open_destination_rb_3).getId()) {
            message = "Adam Smith Building";
        } else {
            message = "Alexander Stone Building";
        }

        Intent intent = new Intent(this, ExploreActivity.class);
        intent.putExtra(Keys.BUILDING, message);
        startActivity(intent);
    }
}
