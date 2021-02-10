package com.cst2335.plat0039;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_relative);

        Button btn = findViewById((R.id.button));
        btn.setOnClickListener( clk -> {
            Toast.makeText(MainActivity.this,"Here is more information",Toast.LENGTH_LONG).show();
        });


        //Checked change listener for switch
        Switch switchz = findViewById((R.id.switch1));
        switchz.setOnCheckedChangeListener((cb, b) -> {
            Snackbar.make(cb, getResources().getString(R.string.snackBar)+ (b ? getResources().getString(R.string.on): " off"),Snackbar.LENGTH_INDEFINITE).setAction(
                    "undo", click -> cb.setChecked(!b)).show();
        });
    }

    private void onClick(View view) {
    }
}