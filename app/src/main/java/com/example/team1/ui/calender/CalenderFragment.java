package com.example.team1.ui.calender;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.team1.ui.calender.Announcement;

import com.example.team1.databinding.FragmentCalenderBinding;

import java.util.List;

public class CalenderFragment extends Fragment {

    private FragmentCalenderBinding binding;
    private CalenderViewModel calenderViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCalenderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCalender;

        // Initialize the ViewModel
        calenderViewModel = new ViewModelProvider(this).get(CalenderViewModel.class);

        // Observe changes in the text
        calenderViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newText) {
                textView.setText(newText);
            }
        });

        // Observe changes in announcements and display them in a TextView
        final TextView announcementTextView = binding.announcetextView;
        calenderViewModel.getAnnouncements().observe(getViewLifecycleOwner(), new Observer<List<Announcement>>() {
            @Override
            public void onChanged(List<Announcement> announcements) {
                StringBuilder announcementsText = new StringBuilder();
                for (Announcement announcement : announcements) {
                    announcementsText.append(announcement.getMessage()).append("\n");
                }
                announcementTextView.setText(announcementsText.toString().trim());
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
