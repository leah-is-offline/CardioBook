package com.example.copeland_cardiobook;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


/**edit measurement class displays the current objects
 * fields in the edit texts boxes. the user may change
 * the fields, but cannot leave required fields blank*/
public class EditMeasurement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_measurement_activity);

        /**get the intent that started the activity
        * get the extras from the intent (object and position***/
        Intent editData = getIntent();
        final Entry editMe = (Entry) editData.getExtras().getParcelable("EDITED_ENTRY");
        final Integer position = editData.getExtras().getInt("EDIT_POS");


        /**setting the text values to the objects current values
         * user may not want to edit all of the fields**/
        EditText sp = (EditText) findViewById(R.id.systolicEdit);
        sp.setText(String.valueOf(editMe.getSysPressure()));

        EditText dp = (EditText) findViewById(R.id.diastolicEdit);
        dp.setText(String.valueOf(editMe.getDiaPressure()));

        EditText hr = (EditText) findViewById(R.id.heartRateEdit);
        hr.setText(String.valueOf(editMe.getHeartRate()));

        EditText year = (EditText) findViewById(R.id.yearEdit);
        year.setText(String.valueOf(editMe.getYear()));

        EditText month = (EditText) findViewById(R.id.monthEdit);
        month.setText(String.valueOf(editMe.getMonth()));

        EditText day = (EditText) findViewById(R.id.dayEdit);
        day.setText(String.valueOf(editMe.getDay()));

        EditText hour = (EditText) findViewById(R.id.hourEdit);
        hour.setText(String.valueOf(editMe.getHour()));

        EditText minute = (EditText) findViewById(R.id.minuteEdit);
        minute.setText(String.valueOf(editMe.getMinute()));

        EditText comment = (EditText) findViewById(R.id.commentEdit);
        comment.setText(editMe.getComment());


        Button cancelEditButton = findViewById(R.id.cancelEditButton);
        cancelEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Button doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**get the values of the current variable and display them*/
                EditText mSystolic = (EditText) findViewById(R.id.systolicEdit);
                EditText mDiastolic = (EditText) findViewById(R.id.diastolicEdit);
                EditText mHeartR = (EditText) findViewById(R.id.heartRateEdit);
                EditText mYear = (EditText) findViewById(R.id.yearEdit);
                EditText mMonth = (EditText) findViewById(R.id.monthEdit);
                EditText mDay = (EditText) findViewById(R.id.dayEdit);
                EditText mHour = (EditText) findViewById(R.id.hourEdit);
                EditText mMinute = (EditText) findViewById(R.id.minuteEdit);
                EditText mComment = (EditText) findViewById(R.id.commentEdit);

                /**validate every editText box is filled in**/

                /**CITATION: StackOverflow post by Winona https://stackoverflow.com/users/769860/winona,
                 * Answer, https://stackoverflow.com/a/25156934/12678398 **/

                /**CITATION: StackOverflow post by Upvote https://stackoverflow.com/users/401025/upvote,
                 * Answer ,https://stackoverflow.com/questions/3500197/how-to-display-toast-in-android**/

                if (TextUtils.isEmpty(mSystolic.getText().toString())) {
                    Toast.makeText(EditMeasurement.this, "Please fill in the required fields!",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mDiastolic.getText().toString())) {
                    Toast.makeText(EditMeasurement.this, "Please fill in the required fields!",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mHeartR.getText().toString())) {
                    Toast.makeText(EditMeasurement.this, "Please fill in the required fields!",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mYear.getText().toString())) {
                    Toast.makeText(EditMeasurement.this, "Please fill in the required fields!",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mMonth.getText().toString())) {
                    Toast.makeText(EditMeasurement.this, "Please fill in the required fields!",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mDay.getText().toString())) {
                    Toast.makeText(EditMeasurement.this, "Please fill in the required fields!",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mHour.getText().toString())) {
                    Toast.makeText(EditMeasurement.this, "Please fill in the required fields!",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mMinute.getText().toString())) {
                    Toast.makeText(EditMeasurement.this, "Please fill in the required fields!",
                            Toast.LENGTH_SHORT).show();
                }else if (mComment.getText().toString().length() > 20){
                    Toast.makeText(EditMeasurement.this, "comment must be 20 characters or less!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    /**all of the required fields are filled in **/
                    int systolic = Integer.parseInt(mSystolic.getText().toString());
                    int diastolic = Integer.parseInt(mDiastolic.getText().toString());
                    int heartrate = Integer.parseInt(mHeartR.getText().toString());
                    int year = Integer.parseInt(mYear.getText().toString());
                    int month = Integer.parseInt(mMonth.getText().toString());
                    int day = Integer.parseInt(mDay.getText().toString());
                    int hour = Integer.parseInt(mHour.getText().toString());
                    int minute = Integer.parseInt(mMinute.getText().toString());
                    String comment = mComment.getText().toString();


                    /**initialize the edited Entry object with these values**/
                    Entry newEditMe = new Entry(systolic, diastolic, heartrate, year, month, day, hour, minute, comment);

                    /**send the new object back to main to replace the old object**/
                    Intent sendEditedData = new Intent();
                    sendEditedData.putExtra("EDITED_ENTRY", newEditMe);
                    sendEditedData.putExtra("EDIT_POS", position);
                    setResult(Activity.RESULT_OK, sendEditedData);
                    finish();
                }
            }
        });
    }
}
