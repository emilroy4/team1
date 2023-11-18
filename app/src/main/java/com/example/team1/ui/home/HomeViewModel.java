package com.example.team1.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> locationText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();

        mText.setValue("Announcement:");

        locationText = new MutableLiveData<>();
        locationText.setValue("Location:");

    }


    public LiveData<String> getText() {
        return mText;
    }


    public LiveData<String> getLocationText() {
        return locationText;
    }

}