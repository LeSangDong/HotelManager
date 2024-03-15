package com.example.hotelmanager.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

import com.example.hotelmanager.R;
import com.example.hotelmanager.databinding.ActivityFeedBackBinding;

public class FeedBackActivity extends AppCompatActivity {
    private ActivityFeedBackBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedBackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bg_splash));

        binding.btnBack.setOnClickListener(v->{
            finish();
        });
    }
}