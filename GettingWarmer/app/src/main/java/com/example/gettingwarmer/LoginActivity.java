package com.example.gettingwarmer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    private String loginType;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences(Keys.PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        final Calendar myCalendar = Calendar.getInstance();
        final EditText secondary = (EditText) findViewById(R.id.activity_login_et_secondary);

        Intent intent = getIntent();
        String message = intent.getStringExtra(Keys.LOGIN_TYPE);

        editor.clear();

        if (message.equalsIgnoreCase("student")) {
            setUpActivity("Student",
                    getString(R.string.activity_login_id),
                    getString(R.string.activity_login_password));

        } else if (message.equalsIgnoreCase("open_day")) {
            secondary.setFocusable(false);
            secondary.setInputType(InputType.TYPE_CLASS_TEXT);

            // https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel(secondary, myCalendar);
                }

            };

            secondary.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    new DatePickerDialog(LoginActivity.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });

            setUpActivity("Open Day",
                    getString(R.string.activity_login_code),
                    getString(R.string.activity_login_date));
        } else {
            showError("There was an internal error please contact the developers.");
        }
    }

    public void switchDestination(View view) {
        EditText primary = (EditText) findViewById(R.id.activity_login_et_primary);
        EditText secondary = (EditText) findViewById(R.id.activity_login_et_secondary);

        editor.putString(Keys.TYPE, loginType);
        if (loginType.equalsIgnoreCase("student")) {
            editor.putString(Keys.STUDENTID, primary.getText().toString());
            Intent intent = new Intent(this, DestinationActivity.class);
            intent.putExtra(Keys.LOGIN_TYPE, loginType);
            startActivity(intent);
        } else {
            editor.putString(Keys.DateID, secondary.getText().toString());
            Intent intent = new Intent(this, OpenDestinationActivity.class);
            startActivity(intent);
        }
        editor.commit();
    }

    private void updateLabel(EditText edittext, Calendar cal) {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        edittext.setText(sdf.format(cal.getTime()));
    }

    private void setUpActivity(String typeMsg, String primaryHint, String secondaryHint) {
        TextView type = (TextView) findViewById(R.id.activity_login_tv_type);
        EditText primary = (EditText) findViewById(R.id.activity_login_et_primary);
        EditText secondary = (EditText) findViewById(R.id.activity_login_et_secondary);

        loginType = typeMsg;

        type.setText(typeMsg);
        primary.setHint(primaryHint);
        secondary.setHint(secondaryHint);
    }

    private void showError(String errorMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder
                .setMessage( errorMsg )
                .setCancelable( false )
                .setNeutralButton( "Ok.", new DialogInterface.OnClickListener()
                {
                    public void onClick ( DialogInterface dialog, int which )
                    {
                        LoginActivity.this.finish();
                    }
                } );
        AlertDialog error = builder.create();
        error.show();
        return;
    }
}
