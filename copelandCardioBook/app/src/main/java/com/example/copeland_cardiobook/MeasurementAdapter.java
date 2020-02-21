package com.example.copeland_cardiobook;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/** MeasurementAdapter class is a custom
 * adapter class that enables us to populate a
 * list view with more flexibility.
 * class MeasurementAdapter handles the Entry objects
 * in measurement list*/
public class MeasurementAdapter extends ArrayAdapter<Entry> {

    private Context context;
    private List<Entry> measurementsList;

    /**provide adapter with data it will be using**/
    public MeasurementAdapter(@NonNull Context context, ArrayList<Entry> measurementsList){
        super(context, 0, measurementsList);
        this.context = context;
        this.measurementsList = measurementsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;

        /**if there is no list item, inflate rest of list**/
        if (listItem == null){
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        }

        /**getting the current city, and initializing a list item with its values**/
        Entry curEntry = measurementsList.get(position);


        /**if blood pressure is higher or lower than appropriate range, set text color to red in list view**/

        /**CITATION: StackOverflow post by Nanne https://stackoverflow.com/users/415865/vikas,
         *Answer https://stackoverflow.com/questions/4602902/how-to-set-the-text-color-of-textview-in-code**/
        TextView bloodPressure = (TextView) listItem.findViewById(R.id.textViewBloodPressure);
        if (curEntry.getSysPressure() > 140 || curEntry.getSysPressure() < 90 || curEntry.getDiaPressure() < 60 || curEntry.getDiaPressure() > 90) {
            bloodPressure.setText("Blood Pressure: " + String.valueOf(curEntry.getSysPressure()) + "/" + String.valueOf(curEntry.getDiaPressure()));
            bloodPressure.setTextColor(Color.parseColor("#F70606"));
        }else{
            bloodPressure.setText("Blood Pressure: " + String.valueOf(curEntry.getSysPressure()) + "/" + String.valueOf(curEntry.getDiaPressure()));
            bloodPressure.setTextColor(Color.parseColor("#FFFFFF"));
        }


        TextView heartRate = (TextView) listItem.findViewById(R.id.textViewHeartR);
        if (curEntry.getHeartRate() == 69) {
            heartRate.setText("Heart Rate: " + String.valueOf(curEntry.getHeartRate()) + " (nice)");
        }else{
            heartRate.setText("Heart Rate: " + String.valueOf(curEntry.getHeartRate()));
        }


        TextView date = (TextView) listItem.findViewById(R.id.textViewDate);
        date.setText("Date: " + String.valueOf(curEntry.getYear()) + "-" + String.valueOf(curEntry.getMonth()) + "-" + String.valueOf(curEntry.getDay()));

        TextView time = (TextView) listItem.findViewById(R.id.textViewTime);
        time.setText("Time: " + String.valueOf(curEntry.getHour()) +":"+ String.valueOf(curEntry.getMinute()));

        return listItem;

    }

}
