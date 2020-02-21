package com.example.copeland_cardiobook;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

/**main activity displays a spinner drop down menu,
 * an app header, and the list view of measurements
 * main activity mostly interfaces with the custom adapter
 * class and send/ receives objects to alter the listView
 * content
 */
public class MainActivity extends AppCompatActivity implements ViewMeasurement.OnFragmentInteractionListener  {

    private static final int REQUEST_CODE_ADD = 420;
    private static final int REQUEST_CODE_EDIT = 69;
    private static final int OPTIONS = 0;
    private static final int ADD = 1;
    private static final int VIEW_EDIT = 2;
    private static final int SAVE = 3;

    ListView displayList;
    Spinner spinner;
    ArrayList<Entry> measurementsList;
    MeasurementAdapter mEntryAdapter;
    SaveMeasurements savemeasurements;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayList = findViewById(R.id.display_list);
        spinner =  findViewById(R.id.spinner1);

        /**load list if prior one has been saved, initialize a new one if not**/
        savemeasurements = new SaveMeasurements();
        measurementsList = savemeasurements.loadData(MainActivity.this);


        /**set the list view adapter (custom adapter class)**/
        mEntryAdapter = new MeasurementAdapter(this,measurementsList);
        displayList.setAdapter(mEntryAdapter);

        /**initialize a spinner and set its adapter, strings are in 'values'**/

        /**CITATION: Youtube, Coding Demos, Android Drop Down List, Tutorial,
         * published on August 4,2016 Standard License, https://www.youtube.com/watch?v=urQp7KsQhW8 **/
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.menu));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);


        /**calling methods based on the item in the spinner drop down menu that is clicked**/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == ADD){
                    addEntry();
                    spinner.setSelection(OPTIONS);
                }else if (position == VIEW_EDIT) {
                    viewEditEntry();
                    spinner.setSelection(OPTIONS);
                }else if (position == SAVE){
                    savemeasurements.saveData(measurementsList, MainActivity.this);
                    spinner.setSelection(OPTIONS);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner.setSelection(OPTIONS);
            }
        });
    }

    /**start add activity when chosen from spinner**/
    public void addEntry(){
        final Intent addIntent = new Intent(MainActivity.this, AddMeasurement.class);
        startActivityForResult(addIntent, REQUEST_CODE_ADD);
    }

    /**upon single click on list item, start view measurement activity, which is a fragment
     * set click listener only after view/edit is pressed for the firs time**/
    public void viewEditEntry(){
        /**CITATION: StackOverflow post by Upvote https://stackoverflow.com/users/401025/upvote,
         * Answer ,https://stackoverflow.com/questions/3500197/how-to-display-toast-in-android**/
        Toast toast = Toast.makeText(getApplicationContext(),"Tap on an Item to View/Edit it!",Toast.LENGTH_LONG);
        toast.show();
        displayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new ViewMeasurement(measurementsList.get(position),position).show(getSupportFragmentManager(), "VIEW_ENTRY");
            }});
    }

    /**instructions upon receiving a new or edited entry object**/

    /**CITATION: StackOverflow post by Jacob Jones https://stackoverflow.com/users/3735346/jacob-jones,
     * Answer https://stackoverflow.com/questions/26703691/android-return-object-as-a-activity-result/26703817 **/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD){
            if(resultCode == Activity.RESULT_OK) {
                Entry newObj = (Entry) data.getExtras().getParcelable("NEW_ENTRY");
                measurementsList.add(newObj);
                mEntryAdapter.notifyDataSetChanged();
            }
        }
        else if (requestCode == REQUEST_CODE_EDIT){
            if(resultCode == Activity.RESULT_OK){
                Entry editedObj = (Entry) data.getExtras().getParcelable("EDITED_ENTRY");
                Integer position = data.getExtras().getInt("POSITION");
               measurementsList.set(position,editedObj);
               mEntryAdapter.notifyDataSetChanged();
            }
        }
    }

    /**overriden methods from ViewMeasurement (fragment) class**/
    @Override
    public void onDeletePressed(int position) {
        mEntryAdapter.remove(measurementsList.get(position));
    }

    @Override
    public void onEditPressed(int position) {
        final Intent editIntent = new Intent(MainActivity.this, EditMeasurement.class);
        editIntent.putExtra("POSITION",position);
        editIntent.putExtra("EDITED_ENTRY", measurementsList.get(position));
        startActivityForResult(editIntent, REQUEST_CODE_EDIT);
    }


}