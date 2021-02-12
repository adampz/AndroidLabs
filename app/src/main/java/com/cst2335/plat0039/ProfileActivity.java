package com.cst2335.plat0039;

import androidx.appcompat.app.AppCompatActivity;
import android.provider.MediaStore;
import android.content.Intent;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.util.Log;


public class ProfileActivity extends AppCompatActivity {

    private static final String ACTIVITY_NAME = "PROFILE";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private View PicBtn;
    private View ChatRoomButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(ACTIVITY_NAME, "processing: onCreate()");

        Intent Main = getIntent();
        EditText emailEditText = (EditText)findViewById(R.id.editText1) ;
        String receivedEmail= Main.getStringExtra("e-mail");
        emailEditText.setText(receivedEmail);

        ImageButton PicBtn = (ImageButton) findViewById(R.id.button);
        PicBtn.setOnClickListener(c -> {

            Intent picture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (picture.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(picture, REQUEST_IMAGE_CAPTURE);

            }
        });

        Button ChatRoomButton = (Button) findViewById(R.id.buttonz);
        ChatRoomButton.setOnClickListener(c -> {

            Intent openChat = new Intent(ProfileActivity.this, ChatRoomActivity.class);
            startActivity(openChat);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(ACTIVITY_NAME, "processing: onActivityResult()");
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageButton PicBtn = (ImageButton) findViewById(R.id.button);
            PicBtn.setImageBitmap(imageBitmap);
        }
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