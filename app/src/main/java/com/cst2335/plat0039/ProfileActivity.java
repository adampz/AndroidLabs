package com.cst2335.plat0039;

import androidx.appcompat.app.AppCompatActivity;
import android.provider.MediaStore;
import android.content.Intent;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.widget.ImageButton;
import android.widget.EditText;
import android.util.Log;


public class ProfileActivity extends AppCompatActivity {

    private static final String ACTIVITY_NAME = "PROFILE";
    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        EditText emailEditText = (EditText)findViewById(R.id.editText1) ;

        Intent fromMain = getIntent();
        String receivedEmail= fromMain.getStringExtra("e-mail");
        emailEditText.setText(receivedEmail);


        ImageButton PicBtn = (ImageButton) findViewById(R.id.buttonz);
        PicBtn.setOnClickListener(c -> {


            Intent picture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (picture.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(picture, REQUEST_IMAGE_CAPTURE);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("Data");
            ImageButton PicBtn = (ImageButton) findViewById(R.id.buttonz);
            PicBtn.setImageBitmap(imageBitmap);
        }
        Log.d(ACTIVITY_NAME, "processing: onActivityResult()");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(ACTIVITY_NAME, "processing: onStart()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(ACTIVITY_NAME, "processing: onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(ACTIVITY_NAME, "processing: onResume()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(ACTIVITY_NAME, "processing: onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(ACTIVITY_NAME, "processing: onDestroy()");
    }
}