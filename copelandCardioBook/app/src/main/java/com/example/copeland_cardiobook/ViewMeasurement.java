package com.example.copeland_cardiobook;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


/**View measurement class creates a dialog fragment in main activity. upon viewing
 * their entry, user may choose to delete, edit, or be done viewing**/
public class ViewMeasurement extends DialogFragment {

    private final Entry viewMe;
    private final int position;

    private TextView bpView;
    private TextView hrView;
    private TextView dateView;
    private TextView timeView;
    private TextView commentView;

    private OnFragmentInteractionListener listener;

    /**providing View measurement constructor with object, and position if user wants to edit**/
    public ViewMeasurement(Entry viewMe, int position) {
        this.viewMe = viewMe;
        this.position = position;
    }

    /**methods implemented  in main activity**/
    public interface OnFragmentInteractionListener {
        void onDeletePressed(int position);
        void onEditPressed(int position);
    }


    /**throw runtime exception if fragment interaction listener hasn't been implemented in class**/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**populate the fragment text fields with object values**/
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_entry_layout, null);

        bpView = view.findViewById(R.id.viewBP);
        hrView = view.findViewById(R.id.viewHR);
        dateView = view.findViewById(R.id.viewDate);
        timeView = view.findViewById(R.id.viewTime);
        commentView = view.findViewById(R.id.viewComment);

        bpView.setText("Blood Pressure: " + String.valueOf(viewMe.getSysPressure()) + "/" + String.valueOf(viewMe.getDiaPressure()) + "  [mmHg]");
        hrView.setText("Heart Rate: " + String.valueOf(viewMe.getHeartRate()) + "  [bpm]");
        dateView.setText("Date Measured: " + String.valueOf(viewMe.getYear()) + "-" + String.valueOf(viewMe.getMonth()) + "-" + String.valueOf(viewMe.getDay()));
        timeView.setText("Time Measured: " + String.valueOf(viewMe.getHour()) + ":" + viewMe.getMinute());
        commentView.setText("Comment: " + viewMe.getComment());

        /**dialog build + instructions upon button clicks**/
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Entry Details")
                .setNeutralButton("Done", null)
                .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onEditPressed(position);
                    }
                })
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onDeletePressed(position);
                    }}).create();
    }
}


