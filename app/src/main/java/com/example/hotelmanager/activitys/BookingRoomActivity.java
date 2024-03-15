package com.example.hotelmanager.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

import com.example.hotelmanager.R;

public class BookingRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_room);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bg_splash));
    }
}