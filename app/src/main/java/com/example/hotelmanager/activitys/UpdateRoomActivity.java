package com.example.hotelmanager.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

import com.example.hotelmanager.R;
import com.example.hotelmanager.databinding.ActivityUpdateInfoBinding;
import com.example.hotelmanager.databinding.ActivityUpdateRoomBinding;

public class UpdateRoomActivity extends AppCompatActivity {
    private ActivityUpdateRoomBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bg_splash));

        binding.btnBack.setOnClickListener(v->{
            finish();
        });
    }
}