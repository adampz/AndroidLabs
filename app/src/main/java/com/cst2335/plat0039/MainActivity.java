package com.cst2335.plat0039;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText emailField = (EditText) findViewById(R.id.editView);
        preferences = getSharedPreferences("MyPrefers", Context.MODE_PRIVATE);
        String Strings = preferences.getString("email", "");
        emailField.setText(Strings);

        Button login = (Button) findViewById(R.id.buttonz);
        login.setOnClickListener(c -> {

            Intent profile = new Intent(MainActivity.this, ProfileActivity.class);
            profile.putExtra("e-mail", emailField.getText().toString().trim());
            startActivityForResult(profile, 345);
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        EditText emailField = (EditText) findViewById(R.id.editView);
        SharedPreferences.Editor editor = preferences.edit();
        String textEmailAddress=emailField.getText().toString().trim();
        editor.putString("email", textEmailAddress);
        editor.commit();
        editor.apply();
    }
}
