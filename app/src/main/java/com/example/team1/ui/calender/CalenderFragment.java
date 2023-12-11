package com.example.team1.ui.calender;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.team1.ui.calender.Announcement;

import com.example.team1.databinding.FragmentCalenderBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalenderFragment extends Fragment {

    private FragmentCalenderBinding binding;
    private CalenderViewModel calenderViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCalenderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCalender;
        final CalendarView calendarView = binding.calendarView;

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

        // Set up a click listener for the CalendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Handle the date selection here
                showScheduleForDate(year, month, dayOfMonth);
            }
        });

        return root;
    }

    // Function to show the schedule for a selected date

    // Function to show the schedule for a selected date
    private void showScheduleForDate(int year, int month, int dayOfMonth) {
        // Retrieve the user's availability for the selected date
        boolean isFree = getUserAvailabilityForDate(year, month, dayOfMonth);

        // Create and display the ScheduleDialogFragment
        ScheduleDialogFragment scheduleDialog = new ScheduleDialogFragment();
        scheduleDialog.setIsFree(isFree); // Set the availability
        scheduleDialog.show(getChildFragmentManager(), "ScheduleDialog");
    }

    // Example function to get user's availability based on your logic
    private boolean getUserAvailabilityForDate(int year, int month, int dayOfMonth) {
        // Replace this with your logic to determine the user's availability for the selected date
        // You can fetch this data from your data source (e.g., a database)
        // For simplicity, we will return true if the date is even (user is free on even days)
        return dayOfMonth % 2 == 0;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}