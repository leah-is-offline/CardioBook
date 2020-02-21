package com.example.copeland_cardiobook;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;


/**class save measurement saves the list of Entry
 * objects that are seen in the listView in
 * main activity upon app exit. notably, this
 * save only occurs if the user chooses to save their
 * list from the spinner drop down in main activity*/
public class SaveMeasurements {

    private static String PREFERENCES = "measurement list";
    private SharedPreferences sharedPreferences;
    private ArrayList measurementsList;

    /**CITATION: StackOverflow post by Scott https://stackoverflow.com/users/3576831/scott,
     * Answer,  https://stackoverflow.com/questions/23351904/getting-cannot-resolve-method-error-when-trying-to-implement-getsharedpreferen**/

    public SaveMeasurements(){
        /**empty constructor method. Here's a comment to keep it company**/
    }

    /**CITATION: Youtube, Coding in Flow, How to Save an ArrayList of Custom Objects to SharedPreferences with Gson - Android Studio Tutorial,
     * published on November 6,2017, Standard License, https://www.youtube.com/watch?v=jcliHGR3CHo **/

    /**save array list of Entry objs to shared preferences**/
    public void saveData(ArrayList measurementsList, Context context){
        sharedPreferences = context.getSharedPreferences(PREFERENCES, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit() ;
        Gson gson = new Gson();

        /**contains our arraylist as Gson**/
        String json = gson.toJson(measurementsList);
        editor.putString("measurements list",json);
        editor.apply();
    }

    /**load arrayList from shared preferences**/
    public ArrayList loadData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES, context.MODE_PRIVATE);
        Gson gson = new Gson();

        /**default value is null, so if we didn't save an array list yet we wont get anything
         * back, and we will instantiate a new one**/
        String json = sharedPreferences.getString("measurements list", null);
        Type type = new TypeToken<ArrayList<Entry>>() {}.getType();
        measurementsList = gson.fromJson(json, type);

        if (measurementsList == null) {
            measurementsList = new ArrayList<>();
        }
        return measurementsList;
    }
}
