package com.example.hotelmanager.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotelmanager.R;
import com.example.hotelmanager.activitys.AddCustomerActivity;
import com.example.hotelmanager.activitys.FeedBackActivity;
import com.example.hotelmanager.activitys.ListCustomerActivity;
import com.example.hotelmanager.activitys.MainActivity;
import com.example.hotelmanager.activitys.OrderRoomActivity;
import com.example.hotelmanager.adapter.ViewpagerAdapter;
import com.example.hotelmanager.databinding.FragmentDashBoardBinding;
import com.example.hotelmanager.model.Banner;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


public class DashBoardFragment extends Fragment {

  private FragmentDashBoardBinding binding;
  private FirebaseAuth auth;
  private ViewPager2 viewPager2;
  private ViewpagerAdapter viewpagerAdapter;
  private List<Banner> mBanners;


    private Handler mHanler = new Handler(Looper.getMainLooper());
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (binding.viewpager2.getCurrentItem() == mBanners.size() - 1) {
                binding.viewpager2.setCurrentItem(0);
            } else {
                binding.viewpager2.setCurrentItem(binding.viewpager2.getCurrentItem() + 1);
            }


        }
    };

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

        binding.viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                mHanler.removeCallbacks(mRunnable);
                mHanler.postDelayed(mRunnable, 3000);
            }
        });


        setUpListBanner();
    }

    private void setUpListBanner() {
        mBanners = new ArrayList<>();
        mBanners.add(new Banner(R.drawable.banner1));
        mBanners.add(new Banner(R.drawable.banner2));
        mBanners.add(new Banner(R.drawable.pdoi_2));
        mBanners.add(new Banner(R.drawable.pdoi_3));
        mBanners.add(new Banner(R.drawable.pdoi_4));
        mBanners.add(new Banner(R.drawable.pdoi_5));
        mBanners.add(new Banner(R.drawable.pviewcity_1));
        mBanners.add(new Banner(R.drawable.viewcity_3));



        viewpagerAdapter = new ViewpagerAdapter(mBanners);
        binding.viewpager2.setAdapter(viewpagerAdapter);
        binding.indicator3.setViewPager(binding.viewpager2);

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

    @Override
    public void onPause() {
        super.onPause();
        mHanler.removeCallbacks(mRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        mHanler.postDelayed(mRunnable, 3000);
    }

}