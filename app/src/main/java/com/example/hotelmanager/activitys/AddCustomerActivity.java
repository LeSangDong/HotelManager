package com.example.hotelmanager.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

import com.example.hotelmanager.R;
import com.example.hotelmanager.databinding.ActivityAddCustomerBinding;

public class AddCustomerActivity extends AppCompatActivity {
    private ActivityAddCustomerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bg_splash));

        //back btn
        binding.btnBack.setOnClickListener(v->{
            finish();
        });
    }
}