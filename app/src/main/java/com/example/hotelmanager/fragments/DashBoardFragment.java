package com.example.hotelmanager.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotelmanager.R;
import com.example.hotelmanager.activitys.AddCustomerActivity;
import com.example.hotelmanager.activitys.FeedBackActivity;
import com.example.hotelmanager.activitys.ListCustomerActivity;
import com.example.hotelmanager.activitys.MainActivity;
import com.example.hotelmanager.activitys.OrderRoomActivity;
import com.example.hotelmanager.databinding.FragmentDashBoardBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class DashBoardFragment extends Fragment {

  private FragmentDashBoardBinding binding;
  private FirebaseAuth auth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentDashBoardBinding.inflate(inflater,container,false);
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //init view
        iNit(view);
        //chuyen den cac man hinh
        gotoActivityAction();
    }

    private void gotoActivityAction() {
        binding.cAddCustomer.setOnClickListener(v->{
            startActivity(new Intent(requireActivity(), AddCustomerActivity.class));
        });
        binding.cBookRoom.setOnClickListener(v->{
            startActivity(new Intent(requireActivity(), OrderRoomActivity.class));
        });
        binding.cFeedbackCustomer.setOnClickListener(v->{
            startActivity(new Intent(requireActivity(), FeedBackActivity.class));
        });
        binding.cListCustomer.setOnClickListener(v->{
            startActivity(new Intent(requireActivity(), ListCustomerActivity.class));
        });

    }

    private void iNit(View view) {
        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            binding.tvCurrentUser.setText(currentUser.getEmail());
        }

    }
}