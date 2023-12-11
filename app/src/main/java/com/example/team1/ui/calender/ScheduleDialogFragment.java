package com.example.team1.ui.calender;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ScheduleDialogFragment extends DialogFragment {

    private boolean isFree; // Set this to true or false based on user's availability

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Create an AlertDialog with a custom message based on the user's availability
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Availability");

        if (isFree) {
            builder.setMessage("You are free on this date.");
        } else {
            builder.setMessage("You are not free on this date.");
        }

        // Add a button to dismiss the dialog
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        // Create and return the AlertDialog
        return builder.create();
    }

    // Setter method for isFree
    public void setIsFree(boolean isFree) {
        this.isFree = isFree;
    }

}


