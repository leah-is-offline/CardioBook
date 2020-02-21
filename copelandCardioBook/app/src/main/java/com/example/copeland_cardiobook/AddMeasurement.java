package com.example.copeland_cardiobook;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**add measurement class takes input from user
 * and creates a new object with this input. this objects
 * is sent back to main activity to be displayed in the list view
 */
public class AddMeasurement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_measurement);

        Button cancelAddButton = findViewById(R.id.cancelAddButton);
        cancelAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText mSys = (EditText) findViewById(R.id.systolicEntry);
                EditText mDia = (EditText) findViewById(R.id.diastolicEntry);
                EditText mHeartR = (EditText) findViewById(R.id.heartRateEntry);
                EditText mYear = (EditText) findViewById(R.id.yearEntry);
                EditText mMonth = (EditText) findViewById(R.id.monthEntry);
                EditText mDay = (EditText) findViewById(R.id.dayEntry);
                EditText mHour = (EditText) findViewById(R.id.hourEntry);
                EditText mMinute = (EditText) findViewById(R.id.minuteEntry);
                EditText mComment = (EditText) findViewById(R.id.commentEntry);

                /**validate every editText box is filled in**/

                /**CITATION: StackOverflow post by Winona https://stackoverflow.com/users/769860/winona,
                 * Answer, https://stackoverflow.com/a/25156934/12678398 **/

                /**CITATION: StackOverflow post by Upvote https://stackoverflow.com/users/401025/upvote,
                 * Answer ,https://stackoverflow.com/questions/3500197/how-to-display-toast-in-android**/

                if (TextUtils.isEmpty(mSys.getText().toString())) {
                    Toast.makeText(AddMeasurement.this, "Please fill in the required fields!",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mDia.getText().toString())) {
                    Toast.makeText(AddMeasurement.this, "Please fill in the required fields!",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mHeartR.getText().toString())) {
                    Toast.makeText(AddMeasurement.this, "Please fill in the required fields!",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mYear.getText().toString())) {
                    Toast.makeText(AddMeasurement.this, "Please fill in the required fields!",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mMonth.getText().toString())) {
                    Toast.makeText(AddMeasurement.this, "Please fill in the required fields!",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mMonth.getText().toString())) {
                    Toast.makeText(AddMeasurement.this, "Please fill in the required fields!",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mDay.getText().toString())) {
                    Toast.makeText(AddMeasurement.this, "Please fill in the required fields!",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mHour.getText().toString())) {
                    Toast.makeText(AddMeasurement.this, "Please fill in the required fields!",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mMinute.getText().toString())) {
                    Toast.makeText(AddMeasurement.this, "Please fill in the required fields!",
                            Toast.LENGTH_SHORT).show();
                }else if (mComment.getText().toString().length() > 20){
                    Toast.makeText(AddMeasurement.this, "comment must be 20 characters or less!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    /**all of the required fields are filled in **/
                    int sysPress = Integer.parseInt(mSys.getText().toString());
                    int diaPress = Integer.parseInt(mDia.getText().toString());
                    int heartrate = Integer.parseInt(mHeartR.getText().toString());
                    int year = Integer.parseInt(mYear.getText().toString());
                    int month = Integer.parseInt(mMonth.getText().toString());
                    int day = Integer.parseInt(mDay.getText().toString());
                    int hour = Integer.parseInt(mHour.getText().toString());
                    int minute = Integer.parseInt(mMinute.getText().toString());
                    String comment = mComment.getText().toString();

                    /**initialize a new Entry object with these values**/
                    Entry newEntry = new Entry(sysPress, diaPress, heartrate, year, month, day, hour, minute, comment);

                    /**send entry object to main to be displayed in list**/
                    Intent newEntryData = new Intent();
                    newEntryData.putExtra("NEW_ENTRY", newEntry);
                    setResult(Activity.RESULT_OK, newEntryData);
                    finish();
                }
            }
        });
    }
}