package com.example.uselessmachine;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonSelfDestruct;
    private Switch switchUseless;
    private ConstraintLayout constraintLayoutBackground;

    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        setListeners();

    }

    private void setListeners() {
        // TODO self destruct button
        buttonSelfDestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSelfDestructSequence();

            }
        });

        switchUseless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    startSwitchOffTimer();
                    Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Off", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startSelfDestructSequence() {
        //disable the button
        buttonSelfDestruct.setEnabled(false);
        //start a 10 second countdown timer that updates the display every second
        startCountDownTimer();
        //want to display
        //destruct in 10...
        //destruct in 9...
        //at the end, close the activity
        //call the finish() method

    }

    private void startCountDownTimer() {
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Toast.makeText(MainActivity.this, "Destruct in " + millisUntilFinished/1000 + "...",
                        Toast.LENGTH_SHORT).show();
            }
            public void onFinish() {
                finish();
            }
        }.start();
    }

    private void startSwitchOffTimer() {
        new CountDownTimer(5000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (!switchUseless.isChecked()) {
                  //  Log.d(TAG, "onTick: canceling");
                    cancel();

                }
            }

            @Override
            public void onFinish() {
                switchUseless.setChecked(false);
                   // Log.d(TAG, "onTick: switch set to false");
            }
        }.start();
    }

    private void wireWidgets() {
        buttonSelfDestruct = findViewById(R.id.button_main_selfdestruct);
        switchUseless = findViewById(R.id.switch_main_useless);
        constraintLayoutBackground = findViewById(R.id.activity_main_background);
    }


}
