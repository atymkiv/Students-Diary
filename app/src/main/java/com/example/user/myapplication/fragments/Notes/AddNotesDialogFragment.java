package com.example.user.myapplication.fragments.Notes;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.user.myapplication.R;
import com.example.user.myapplication.fragments.Notes.service.NotesListService;

/**
 * Created by User on 27.12.2017.
 */

public class AddNotesDialogFragment extends DialogFragment {

    public static AddNotesDialogFragment getInstance(String idNote){
        Bundle bundle = new Bundle();
        bundle.putString("ID NOTE", idNote);
        AddNotesDialogFragment fragment = new AddNotesDialogFragment();
        fragment.setArguments(bundle);
        return fragment;

    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Створює dialog і встановлює handler на нажаття кнопки
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Додати нову нотатку");
        builder.setView(R.layout.note_create_dialog);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getContext(),NotesListService.class);
                intent.putExtra(NotesListService.EXTRA_NOTE_DESCRIPTION, "");
                getActivity().startService(intent);

            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();

    }
}
