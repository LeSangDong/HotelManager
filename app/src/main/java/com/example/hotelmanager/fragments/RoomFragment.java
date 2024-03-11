package com.example.hotelmanager.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hotelmanager.R;
import com.example.hotelmanager.adapter.ListRoomAdapter;
import com.example.hotelmanager.databinding.FragmentRoomBinding;
import com.example.hotelmanager.listener.IRoomLoadListener;
import com.example.hotelmanager.model.Room;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class RoomFragment extends Fragment implements IRoomLoadListener {

    private FragmentRoomBinding binding;
    IRoomLoadListener roomLoadListener;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentRoomBinding.inflate(inflater,container,false);
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iNit(view);
        loadListRoomFromFirebase();

        //Action cho nut back
        binding.btnBack.setOnClickListener(v->{
            navController.navigateUp();

        });
    }

    private void loadListRoomFromFirebase() {
        List<Room> rooms = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Rooms")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot roomSnapshot : snapshot.getChildren()){
                                Room room = roomSnapshot.getValue(Room.class);
                                rooms.add(room);

                            }
                            roomLoadListener.onRoomLoadSuccess(rooms);
                        }
                        else{
                            binding.progressBar.setVisibility(View.GONE);
                            roomLoadListener.onRoomLoadFailed("Không tồn tại phòng!");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        binding.progressBar.setVisibility(View.GONE);
                        roomLoadListener.onRoomLoadFailed(error.getMessage());

                    }
                });
    }

    private void iNit(View view) {
        binding.progressBar.setVisibility(View.VISIBLE);
        navController = Navigation.findNavController(view);
        roomLoadListener = this;
        binding.recyclerviewListRoom.setHasFixedSize(true);
        binding.recyclerviewListRoom.setLayoutManager(new LinearLayoutManager(requireContext()));

    }

    @Override
    public void onRoomLoadSuccess(List<Room> roomList) {
        binding.progressBar.setVisibility(View.GONE);
        ListRoomAdapter listRoomAdapter = new ListRoomAdapter(roomList,requireContext());
        binding.recyclerviewListRoom.setAdapter(listRoomAdapter);

    }

    @Override
    public void onRoomLoadFailed(String message) {
        binding.progressBar.setVisibility(View.GONE);
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show();

    }
}