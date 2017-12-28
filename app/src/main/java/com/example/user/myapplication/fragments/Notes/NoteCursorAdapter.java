package com.example.user.myapplication.fragments.Notes;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Paint;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.fragments.Notes.data.NotesListContract;

/**
 * Created by User on 28.12.2017.
 */

public class NoteCursorAdapter extends RecyclerView.Adapter<NoteCursorAdapter.ViewHolder>{
    private Cursor                  cursor;
   // private ToggleNodeCheckListener toggleCheckListener;

    public NoteCursorAdapter(Cursor c, Context context) {
        cursor = c;
        try {
            // Instantiate the ToggleNoteCheckListener so we can send events to the host
          //  toggleCheckListener = (ToggleNodeCheckListener) context;
        }catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString() + " must implement ToggleNoteCheckListener");
        }

    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.bindView(cursor);
    }

    @Override public int getItemCount() {
        return cursor != null ? cursor.getCount() : 0;
    }

    public void swapCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView noteDescTextView;
        private CheckBox noteTaskCheckBox;
        private int todoTaskID = -1;

        public ViewHolder(View view) {
            super(view);
            noteDescTextView = (TextView) view.findViewById(R.id.note_item_description);
            noteTaskCheckBox = (CheckBox) view.findViewById(R.id.note_item_checkbox);
        }

        private void bindView(Cursor cursor) {
            todoTaskID = cursor.getInt(cursor.getColumnIndex(NotesListContract.NotesEntry._ID));
           //boolean isTaskDone = cursor.getInt(cursor.getColumnIndex(NotesListContract.NotesEntry.COLUMN_DONE)) == 1;
            String description = cursor.getString(cursor.getColumnIndex(NotesListContract.NotesEntry.COLUMN_DESCRIPTION));
            noteDescTextView.setText(description);


           /* noteTaskCheckBox.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    toggleTask(b);
                    toggleCheckListener.onTodoItemChange(todoTaskID, b);
                }
            });*/
        }

        private void toggleTask(boolean done) {
            if (done) {
                noteDescTextView.setPaintFlags(noteDescTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }else {
                noteDescTextView.setPaintFlags(noteDescTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
//			toggleCheckListener.onTodoItemChange(todoTaskID, done);
        }
    }

    /* The activity that uses an instance of this adapter must
 * implement this interface in order to receive event callbacks. */
    /*public interface ToggleNodeCheckListener {
        void onNoteItemChange(int todoID, boolean done);
    }*/

}
