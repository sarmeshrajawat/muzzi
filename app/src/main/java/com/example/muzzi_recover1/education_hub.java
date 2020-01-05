package com.example.muzzi_recover1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class education_hub extends AppCompatActivity {
    ImageView lecture_notes;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_hub);
        lecture_notes = (ImageView) findViewById(R.id.ivlecturenotes);
        firebaseAuth = FirebaseAuth.getInstance();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lecture_notes);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        lecture_notes.setImageDrawable(roundedBitmapDrawable);
        lecture_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(education_hub.this, yechno_hub.class));
            }
        });



    }
    private void Logout () {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(education_hub.this, MainActivity.class));
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        switch (item.getItemId()) {
            case R.id.view_profile:{
                Logout();
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
