package com.example.gettingwarmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ExploreActivity extends AppCompatActivity {

    private String building;
    private ImageView compass;
    private PopupWindow popupWindow;

    private FrameLayout frameLayout;
    private ViewGroup.MarginLayoutParams params;
    private ImageView pointer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        Intent intent = getIntent();
        building = intent.getStringExtra(Keys.BUILDING);
        TextView buildingTv = (TextView) findViewById(R.id.activity_explore_tv_building);
        buildingTv.setText(buildingTv.getText() + building);

        frameLayout = findViewById(R.id.activity_explore_fl);
        pointer = findViewById(R.id.activity_explore_img_pointer);
        params = (ViewGroup.MarginLayoutParams) pointer.getLayoutParams();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    public void switchQR(View view) {
        Intent intent = new Intent(this, QRActivity.class);
        startActivity(intent);
    }

    public void changeClose(View view) {
        frameLayout.setBackgroundColor(getResources().getColor(R.color.exploreClose));
        pointer.setImageDrawable(getResources().getDrawable(R.drawable.triangletop));
        params.setMargins(0, 0, 0, 500);
        pointer.setLayoutParams(params);
    }

    public void changeMedium(View view) {
        frameLayout.setBackgroundColor(getResources().getColor(R.color.exploreMedium));
        pointer.setImageDrawable(getResources().getDrawable(R.drawable.triangletopright));
        params.setMargins(290, 0, 0, 320);
        pointer.setLayoutParams(params);
    }

    public void changeFar(View view) {
        frameLayout.setBackgroundColor(getResources().getColor(R.color.exploreFar));
        pointer.setImageDrawable(getResources().getDrawable(R.drawable.trianglebottom));
        params.setMargins(0, 505, 0, 0);
        pointer.setLayoutParams(params);
    }

    public void infoPopup(View view) {
        LayoutInflater layoutInflater = (LayoutInflater) ExploreActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.info, null);

        Button closePopupBtn = (Button) customView.findViewById(R.id.popup_close);
        TextView infoText = (TextView) customView.findViewById(R.id.popup_text);
        infoText.setText(getString(R.string.popup_info_2));
        compass = (ImageView) findViewById(R.id.activity_explore_img_compass);

        //instantiate popup window
        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //display the popup window
        popupWindow.showAtLocation(compass, Gravity.CENTER, 0, 0);

        //close the popup window on button click
        closePopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}
