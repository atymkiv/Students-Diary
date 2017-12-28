package com.example.user.myapplication.fragments.Notes;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.myapplication.R;
import com.example.user.myapplication.fragments.Notes.data.NotesListContract;


public class FragmentNotes extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private String title;
    private int page;
    private NoteCursorAdapter cursorAdapter;
    public static FragmentNotes newInstance(int page, String title) {
        FragmentNotes fragmentNotes = new FragmentNotes();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentNotes.setArguments(args);
        return fragmentNotes;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

    }


    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_notes);
        //LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setHasFixedSize(true);
        cursorAdapter = new NoteCursorAdapter(null, getContext());
        mRecyclerView.setAdapter(cursorAdapter);
        FloatingActionButton actionButton = (FloatingActionButton) view.findViewById(R.id.floating_action_btn_notes);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNotesDialogFragment dialogFragment = new AddNotesDialogFragment();
                dialogFragment.show(getChildFragmentManager(), "addNote");

            }
        });
        getLoaderManager().initLoader(0,null,this);
        return view;

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), NotesListContract.NotesEntry.CONTENT_URI, null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
       cursorAdapter.swapCursor(null);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }



}